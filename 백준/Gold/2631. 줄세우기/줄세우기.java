import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(br.readLine());
            int cnt = 1;
            for (int j = 0; j < i; j++) {
                if (dp[j][1] < num) {
                    cnt = Math.max(cnt, dp[j][0] + 1);
                }
            }
            dp[i][0] = cnt;
            dp[i][1] = num;
        }
        int answer = 0;
        for (int i = 1; i <= N; i++)
            answer = Math.max(answer, dp[i][0]);
        System.out.println(N - answer);
    }
}
