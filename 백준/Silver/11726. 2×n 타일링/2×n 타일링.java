import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine()); // 최대 1000
		dp = new int[n + 1];
		func(n);
		System.out.println(dp[n]);
	}

	private static void func(int n) {
		for (int i = 1; i <= n; i++) {
			if (i == 1)
				dp[i] = 1;
			else if (i == 2)
				dp[i] = 2;
			else
				dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
		}
	}

}
