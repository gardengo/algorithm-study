import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 20000000;
    static int N;
    static int[][] map, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        // 어느 한 지점에서 출발해서 모든 지점을 방문하고 되돌아옴
        // bfs로 시도했으나 메모리초과
        // dfs + dp로 풀어야함
        dp = new int[N][1 << N];
        for (int i = 0; i < N; i++)
            Arrays.fill(dp[i], -1);

        System.out.println(dfs(0, 1));
    }

    static int dfs(int now, int visit) {

        // 모든 도시를 지난 경우
        if (visit == (1 << N) - 1) {
            // now -> 0(출발도시)로 가는 경로가 존재해야 돌아갈 수 있음
            if (map[now][0] == 0)
                return INF;
            return map[now][0];
        }

        if (dp[now][visit] != -1)
            return dp[now][visit];
        dp[now][visit] = INF;

        for (int i = 0; i < N; i++) {
            if ((visit & (1 << i)) == 0 && map[now][i] != 0) {
                dp[now][visit] = Math.min(dfs(i, visit | (1 << i)) + map[now][i], dp[now][visit]);   // 최소비용 갱신
            }
        }
        return dp[now][visit];
    }
}
