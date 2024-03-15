import java.io.*;
import java.util.*;

public class Main {
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] ground = new int[N][M];
        int[][] water = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                int h = str.charAt(j) - '0';
                ground[i][j] = h;
                water[i][j] = h;
            }
        }

        Queue<int[]> que = new ArrayDeque<>();
        Queue<int[]> list = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        boolean finish = true;
        int startH = 0;
        int maxH = 10;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                que.add(new int[]{i, j, water[i][j]});
                for (int k = 0; k < N; k++)
                    Arrays.fill(visited[k], false);
                finish = true;
                startH = water[i][j];
                maxH = 10;

                bfs:
                while (!que.isEmpty()) {
                    int[] cur = que.poll();
                    int cx = cur[0];
                    int cy = cur[1];

                    if (cur[2] <= startH) {
                        list.offer(new int[]{cx, cy});
                    } else {
                        maxH = Math.min(maxH, cur[2]);
                        continue;
                    }

                    for (int d = 0; d < 4; d++) {
                        int nx = cx + dx[d];
                        int ny = cy + dy[d];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                            finish = false;
                            break bfs;
                        }

                        if (!visited[nx][ny]) {
                            visited[nx][ny] = true;
                            que.offer(new int[]{nx, ny, water[nx][ny]});
                        }
                    }
                }

                if (!finish) {
                    que.clear();
                    list.clear();
                }

                while (!list.isEmpty()) {
                    int[] cur = list.poll();
                    int cx = cur[0];
                    int cy = cur[1];

                    water[cx][cy] = maxH;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                answer += water[i][j] - ground[i][j];
            }
        }
        System.out.println(answer);
    }
}
