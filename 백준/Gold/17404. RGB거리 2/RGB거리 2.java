import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++)
                arr[i][j] = Integer.parseInt(st.nextToken());
        }

        int INF = 1000000;
        int answer = INF;
        int[][] dp = new int[N][3];

        for (int k = 0; k < 3; k++) {
            for (int i = 0; i < 3; i++) {
                if (i == k) {
                    dp[0][i] = arr[0][i];
                } else {
                    dp[0][i] = INF;
                }
            }

            for (int i = 1; i < N; i++) {
                for (int j = 0; j < 3; j++)
                    dp[i][j] = arr[i][j] + Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]);
            }

            answer = Math.min(answer, Math.min(dp[N - 1][(k + 1) % 3], dp[N - 1][(k + 2) % 3]));
        }

        System.out.println(answer);
    }
}