import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] val;
    static int[][] check;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++)
                map[i][j] = str.charAt(j) - '0';
        }

        createValue();
        System.out.println(makeAnswer());
    }

    private static void createValue() {
        val = new int[N][M];
        check = new int[N][M];
        int num = 1;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && val[i][j] == 0) {
                    calcValue(i, j, num);
                    num++;
                }
            }
        }
    }

    private static void calcValue(int i, int j, int num) {
        Set<Integer> set = new HashSet<>();
        Queue<Integer> que = new ArrayDeque<>();
        que.offer(i * 10000 + j);

        while (!que.isEmpty()) {
            int cur = que.poll();

            if (set.contains(cur))
                continue;
            set.add(cur);

            int cx = cur / 10000;
            int cy = cur % 10000;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                    continue;

                if (map[nx][ny] == 0) {
                    int next = nx * 10000 + ny;
                    if (!set.contains(next)) {
                        que.offer(next);
                    }
                }
            }
        }

        int value = set.size();
        for (int pos : set) {
            int x = pos / 10000;
            int y = pos % 10000;
            val[x][y] = value;
            check[x][y] = num;
        }
    }

    private static String makeAnswer() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    sb.append(calcAnswer(i, j));
                } else {
                    sb.append(0);
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    private static int calcAnswer(int i, int j) {
        int result = 1;
        Set<Integer> set = new HashSet<>();

        for (int d = 0; d < 4; d++) {
            int nx = i + dx[d];
            int ny = j + dy[d];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M)
                continue;
            if (set.contains(check[nx][ny]))
                continue;

            result += val[nx][ny];
            set.add(check[nx][ny]);
        }

        return result % 10;
    }
}