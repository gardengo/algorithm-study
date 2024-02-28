import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] A = new int[N];
        int[] c = new int[N];
        int costSum = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            c[i] = Integer.parseInt(st.nextToken());
            costSum += c[i];
        }

        int[] dp = new int[costSum + 1];
        for (int i = 0; i < N; i++) {
            int cost = c[i];
            for (int j = costSum - cost; j >=0 ; j--) {
                dp[j + cost] = Math.max(dp[j + cost], dp[j] + A[i]);
            }
        }
        
        for (int i = 0; i <= costSum; i++) {
            if (M <= dp[i]) {
                System.out.println(i);
                break;
            }
        }
    }

}
