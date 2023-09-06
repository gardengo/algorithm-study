import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static long[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // N자리
		dp = new long[N + 1];
		func(N);
		System.out.println(dp[N]);
	}

	private static void func(int n) {
		for (int i = 1; i <= n; i++) {
			if (i == 1 || i == 2)
				dp[i] = 1;
			else
				dp[i] = dp[i - 1] + dp[i - 2];
		}
	}
}
