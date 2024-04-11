import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] cost = new int[N + 1];
        int[] dp = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            dp[i] = cost[i];
        }

        for (int i = 1; i <= N; i++) {
            for (int j = i; j <= N; j++)
                dp[j] = Math.max(dp[j], dp[j - i] + cost[i]);
        }

        System.out.println(dp[N]);
    }
}