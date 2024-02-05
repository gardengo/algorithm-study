import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int[] arr = new int[T];
        int max = 0;

        for (int i = 0; i < T; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, arr[i]);
        }

        int[][] dp = new int[max + 1][4];
        dp[1][1] = 1;
        dp[2][1] = 1;
        dp[2][2] = 1;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        for (int i = 4; i <= max; i++) {
            dp[i][1] = dp[i - 1][1]; // 1 더하기
            dp[i][2] = dp[i - 2][1] + dp[i - 2][2]; // 2 더하기
            dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3]; // 3 더하기
        }

        for (int n : arr) {
            sb.append(dp[n][1] + dp[n][2] + dp[n][3]).append("\n");
        }

        System.out.println(sb);
    }
}
