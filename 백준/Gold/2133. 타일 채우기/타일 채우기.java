import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // 짝수인 경우만 가능

        if (N % 2 == 0) {
            int k = N / 2;
            int[] dp = new int[k + 1];
            dp[1] = 3;
            for (int i = 2; i <= k; i++) {
                dp[i] = dp[i - 1] * 3 + 2;
                for (int j = i - 2; j > 0; j--)
                    dp[i] += dp[j] * 2;
            }

            System.out.println(dp[k]);
            return;
        }

        System.out.println(0);
    }
}