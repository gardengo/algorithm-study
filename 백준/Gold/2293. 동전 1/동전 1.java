import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = Integer.parseInt(br.readLine());

        int[] dp = new int[k + 1];
        for (int i = 0; i < n; i++) {
            if (arr[i] > k)
                continue;

            dp[arr[i]]++;
            for (int j = arr[i]; j <= k; j++)
                dp[j] += dp[j - arr[i]];
        }

        System.out.println(dp[k]);
    }
}