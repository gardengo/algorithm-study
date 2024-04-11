import java.io.*;
import java.util.*;

public class Main {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int M, N, answer;
    private static int[][] map, dp;
    private static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        dp = new int[M][N];
        dp[M - 1][N - 1] = 1;
        visited = new boolean[M][N];
        answer = 0;

        dfs(0, 0, map[0][0]);
        System.out.println(answer);
    }

    private static boolean dfs(int x, int y, int h) {
        if (x == M - 1 && y == N - 1) {
            answer++;
            return true;
        }

        if (visited[x][y]) {
            answer += dp[x][y];
            return true;
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            if (nx < 0 || nx >= M || ny < 0 || ny >= N)
                continue;

            if (map[nx][ny] < h) {
                boolean result = dfs(nx, ny, map[nx][ny]);
                if (result)
                    dp[x][y] += dp[nx][ny];
            }
        }

        visited[x][y] = true;
        return true;
    }
}