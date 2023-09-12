import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int test = 1; test <= T; test++) {
			int n = Integer.parseInt(br.readLine()); // 최대 10만
			int[][] arr = new int[2][n];
			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++)
					arr[i][j] = Integer.parseInt(st.nextToken());
			}

			int[][] dp = new int[2][n];
			dp[0][0] = arr[0][0];
			dp[1][0] = arr[1][0];

			for (int i = 1; i < n; i++) {
				dp[0][i] = dp[1][i - 1] + arr[0][i] > dp[0][i - 1] ? dp[1][i - 1] + arr[0][i] : dp[0][i - 1];
				dp[1][i] = dp[0][i - 1] + arr[1][i] > dp[1][i - 1] ? dp[0][i - 1] + arr[1][i] : dp[1][i - 1];
			}

			sb.append(Math.max(dp[0][n - 1], dp[1][n - 1])).append("\n");
		}
		System.out.println(sb);
	}

}
