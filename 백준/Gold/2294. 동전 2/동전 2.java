import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] dp = new int[k + 1];

        for (int i = 0; i < n; i++) {
            int token = Integer.parseInt(br.readLine());
            if (token <= k)
                dp[token] = 1;

            for (int j = 1; j <= k - token; j++) {
                if (dp[j] == 0)
                    continue;

                if (dp[j + token] == 0) {
                    dp[j + token] = dp[j] + 1;
                } else {
                    dp[j + token] = Math.min(dp[j] + 1, dp[j + token]);
                }
            }
        }

        if (dp[k] == 0)
            dp[k] = -1;
        System.out.println(dp[k]);
    }

}