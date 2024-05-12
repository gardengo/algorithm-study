import java.io.*;
import java.util.*;

public class Main {
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        boolean[][] visited1 = new boolean[n][m];
        boolean[][] visited2 = new boolean[n][m];
        PriorityQueue<int[]> que = new PriorityQueue<>((a, b) -> {
            if (a[2] == b[2]) {
                return a[3] - b[3];
            } else {
                return a[2] - b[2];
            }
        });

        int answer = Integer.MAX_VALUE;
        visited1[0][0] = true;
        que.add(new int[]{0, 0, map[0][0], 0});
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            if (cur[0] == n - 1 && cur[1] == m - 1)
                answer = Math.min(answer, cur[2]);

            if (cur[3] == 0) {
                for (int d = 0; d < 4; d++) {
                    int nx = cur[0] + dx[d];
                    int ny = cur[1] + dy[d];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                        continue;
                    if (visited1[nx][ny])
                        continue;
                    visited1[nx][ny] = true;
                    que.add(new int[]{nx, ny, Math.max(cur[2], map[nx][ny]), 0});
                }
                for (int d = 0; d < 4; d++) {
                    int nx = cur[0] + dx[d] * 2;
                    int ny = cur[1] + dy[d] * 2;
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                        continue;
                    if (visited1[nx][ny] || visited2[nx][ny])
                        continue;
                    visited2[nx][ny] = true;
                    que.add(new int[]{nx, ny, Math.max(cur[2], map[nx][ny]), 1});
                }
            } else {
                for (int d = 0; d < 4; d++) {
                    int nx = cur[0] + dx[d];
                    int ny = cur[1] + dy[d];
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m)
                        continue;
                    if (visited1[nx][ny] || visited2[nx][ny])
                        continue;
                    visited2[nx][ny] = true;
                    que.add(new int[]{nx, ny, Math.max(cur[2], map[nx][ny]), 1});
                }
            }
        }

        System.out.println(answer);
    }
}
