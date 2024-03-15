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

        int[][] water = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                int h = str.charAt(j) - '0';
                water[i][j] = h;
            }
        }

        Queue<int[]> que = new ArrayDeque<>();
        Queue<int[]> list = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        boolean overflow = false;
        int answer = 0;

        for (int k = 2; k <= 9; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (water[i][j] != k - 1)
                        continue;
                    que.offer(new int[]{i, j, water[i][j]});
                    list.offer(new int[]{i, j});
                    visited[i][j] = true;
                    overflow = false;

                    bfs:
                    while (!que.isEmpty()) {
                        int[] cur = que.poll();
                        int cx = cur[0];
                        int cy = cur[1];

                        for (int d = 0; d < 4; d++) {
                            int nx = cx + dx[d];
                            int ny = cy + dy[d];

                            if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                                overflow = true;
                                break bfs;
                            }

                            if (water[nx][ny] > k - 1)
                                continue;

                            if (!visited[nx][ny]) {
                                visited[nx][ny] = true;
                                que.offer(new int[]{nx, ny, water[nx][ny]});
                                list.offer(new int[]{nx, ny});
                            }
                        }
                    }

                    if (!overflow) {
                        while (!list.isEmpty()) {
                            int[] cur = list.poll();
                            water[cur[0]][cur[1]] = k;
                            answer++;
                        }
                    }

                    que.clear();
                    list.clear();
                    for (int t = 0; t < N; t++)
                        Arrays.fill(visited[t], false);
                }
            }
        }

        System.out.println(answer);
    }
}
