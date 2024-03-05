import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        int[] plus = new int[N + 1];
        dp[1] = 3;
        plus[1] = 2;

        for (int i = 2; i <= N; i++) {
            dp[i] = (dp[i - 1] + 2 * plus[i - 1]) % 9901;
            plus[i] = (plus[i - 1] + dp[i - 1]) % 9901;
        }

        System.out.println(dp[N]);

        // 3
        // 3 + 2 + 2 = 7
        // 7 + 1+3+1 + 5 = 17
        // 17 + 1+5+5+1 + 12 = 41
        // 41 + 1+7+13+7+1 + 29 = 99
        // 2, 5, 12, 29, 70
    }
}
