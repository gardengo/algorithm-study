import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[] dp, arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dp = new int[n];
        for (int i = 0; i < n; i++)
            Arrays.fill(dp, -1);

        arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(br.readLine());

        System.out.println(recur(0));
    }

    private static int recur(int idx) {
        if (dp[idx] != -1)
            return dp[idx];
        dp[idx] = Integer.MAX_VALUE;

        int sum = -1;
        for (int i = idx; i < n; i++) {
            sum += arr[i] + 1;
            if (sum > m)
                break;

            if (i == n - 1) {
                dp[idx] = 0;
            } else {
                int blank = m - sum;
                dp[idx] = Math.min(dp[idx], blank * blank + recur(i + 1));
            }
        }

        return dp[idx];
    }
}
