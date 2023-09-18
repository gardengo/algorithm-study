import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		int[] dp = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			dp[i] = dp[i - 1] + 1;
			for (int j = 2; j <= Math.sqrt(i); j++) {
				dp[i] = dp[i - (int) Math.pow(j, 2)] + 1 < dp[i] ? dp[i - (int) Math.pow(j, 2)] + 1 : dp[i];
			}
		}
		System.out.println(dp[N]);
	}

}
