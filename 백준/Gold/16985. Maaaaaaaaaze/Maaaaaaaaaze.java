import java.io.*;
import java.util.*;

public class Main {
    private static ArrayList<int[][]> list;
    private static int answer;
    private static int[] dx = {-1, 1, 0, 0, 0, 0};
    private static int[] dy = {0, 0, -1, 1, 0, 0};
    private static int[] dz = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new int[5][5]);
            for (int j = 0; j < 5; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 5; k++)
                    list.get(i)[j][k] = Integer.parseInt(st.nextToken());
            }
        }

        // 돌리는 방법 4*4*4*4*4
        // 판의 조합 5*4*3*2*1
        // 탈출 bfs
        answer = 200;
        recursion(0);

        if (answer == 200)
            answer = -1;
        System.out.println(answer);
    }

    private static void recursion(int depth) {
        if (answer == 12)
            return;

        if (depth == 5) {
            boolean[] visited = new boolean[5];
            int[][][] cube = new int[5][5][5];
            permutation(0, cube, visited);
            return;
        }

        for (int i = 0; i < 4; i++) {
            recursion(depth + 1);
            int[][] pan = rotate(list.get(depth));
            list.remove(depth);
            list.add(depth, pan);
        }
    }

    private static int[][] rotate(int[][] pan) {
        int[][] result = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++)
                result[i][j] = pan[4 - j][i];
        }
        return result;
    }

    private static void permutation(int depth, int[][][] cube, boolean[] visited) {
        if (answer == 12)
            return;

        if (depth == 5) {
            boolean[][][] visited2 = new boolean[5][5][5];
            simulation(cube, visited2);
            return;
        }

        for (int i = 0; i < 5; i++) {
            if (visited[i])
                continue;

            int[][] pan = list.get(i);
            for (int j = 0; j < 5; j++)
                cube[depth][j] = Arrays.copyOf(pan[j], 5);

            visited[i] = true;
            permutation(depth + 1, cube, visited);
            visited[i] = false;
        }
    }

    private static void simulation(int[][][] cube, boolean[][][] visited) {
        if (cube[0][0][0] == 0 || cube[4][4][4] == 0)
            return;

        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{0, 0, 0, 0});

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            if (cur[3] >= answer)
                break;

            if (visited[cur[0]][cur[1]][cur[2]])
                continue;
            visited[cur[0]][cur[1]][cur[2]] = true;

            if (cur[0] == 4 && cur[1] == 4 && cur[2] == 4) {
                answer = Math.min(answer, cur[3]);
                break;
            }


            for (int d = 0; d < 6; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];
                int nz = cur[2] + dz[d];
                if (nx < 0 || nx > 4 || ny < 0 || ny > 4 || nz < 0 || nz > 4)
                    continue;
                if (cube[nx][ny][nz] == 0)
                    continue;
                if (visited[nx][ny][nz])
                    continue;

                que.add(new int[]{nx, ny, nz, cur[3] + 1});
            }
        }
    }
}
