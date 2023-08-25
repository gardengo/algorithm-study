import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		dp = new int[N + 1];
		calc(N);
		System.out.println(dp[N]);
	}

	private static void calc(int N) {
		dp[1] = 0;
		for (int i = 1; i < N; i++) {
			if (i * 3 <= N)
				dp[i * 3] = dp[i] + 1;
			if (i * 2 <= N) {
				if (dp[i * 2] == 0)
					dp[i * 2] = dp[i] + 1;
				else
					dp[i * 2] = dp[i] + 1 < dp[i * 2] ? dp[i] + 1 : dp[i * 2];
			}
			if (dp[i + 1] == 0)
				dp[i + 1] = dp[i] + 1;
			else
				dp[i + 1] = dp[i] + 1 < dp[i + 1] ? dp[i] + 1 : dp[i + 1];
		}
	}
}
