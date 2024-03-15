import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        // 가장 긴 증가하는 부분수열을 구하고
        // 그 수열의 마지막에서 감소하는 부분 수열을 구해서 더해준다
        // 이렇게 구한 바이토닉 수열 중 가장 긴게 답

        int[] dp1 = new int[N];
        Arrays.fill(dp1, 1);
        for (int i = 0; i < N; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (A[i] > A[j])
                    dp1[i] = Math.max(dp1[j] + 1, dp1[i]);
            }
        }

        int[] dp2 = new int[N];
        Arrays.fill(dp2, 1);
        for (int i = N - 1; i >= 0; i--) {
            for (int j = i + 1; j < N; j++) {
                if (A[i] > A[j])
                    dp2[i] = Math.max(dp2[j] + 1, dp2[i]);
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++)
            answer = Math.max(answer, dp1[i] + dp2[i] - 1);
        System.out.println(answer);
    }
}
