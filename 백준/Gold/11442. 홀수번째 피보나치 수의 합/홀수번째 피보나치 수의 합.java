import java.io.*;
import java.util.*;

public class Main {
    private static final int MOD = 1000000007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        // n(홀수)까지의 합은 n+1번째 피보나치 수랑 같다
        // 분할정복을 이용해 n+1번째 피보나치 수를 구하자
        long[][] pibo = new long[][]{{1, 1}, {1, 0}};
        long cnt = 0;
        if (n % 2 == 0)
            cnt = n - 1;
        else
            cnt = n;

        long[][] result = calc(pibo, cnt);
        System.out.println(result[0][0]);
    }

    private static long[][] calc(long[][] pibo, long cnt) {
        if (cnt == 0 || cnt == 1)
            return pibo;

        long[][] result = calc(pibo, cnt / 2);
        result = multiple(result, result);

        if (cnt % 2 == 1)
            result = multiple(result, pibo);

        return result;
    }

    private static long[][] multiple(long[][] A, long[][] B) {
        long[][] result = new long[2][2];
        result[0][0] = (A[0][0] * B[0][0] % MOD + A[0][1] * B[1][0] % MOD) % MOD;
        result[0][1] = (A[0][0] * B[0][1] % MOD + A[0][1] * B[1][1] % MOD) % MOD;
        result[1][0] = (A[1][0] * B[0][0] % MOD + A[1][1] * B[1][0] % MOD) % MOD;
        result[1][1] = (A[1][0] * B[0][1] % MOD + A[1][1] * B[1][1] % MOD) % MOD;
        return result;
    }
}