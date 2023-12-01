import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static final int MOD = 1000000007;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int[][] dp = new int[10001][16];
		// 비트 마스킹을 이용하여 ABCD 표시

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			String manager = br.readLine(); // 해당일 담당자
			for (int i = 0; i < manager.length(); i++) {
				for (int j = 1; j < 16; j++) {
					dp[i][j] = 0;
				}
			}

			// 1일차 값 넣어주기
			for (int i = 1; i < 16; i++) {
				// A 필참
				if ((i & 1) == 0)
					continue;
				// 매니저 필참
				if ((i & (1 << (manager.charAt(0) - 'A'))) == 0)
					continue;
				dp[0][i] = 1; // 나머지는 +1
			}

			// 이후 값 넣어주기
			for (int i = 1; i < manager.length(); i++) {
				for (int j = 1; j < 16; j++) { // 오늘
					for (int k = 1; k < 16; k++) { // 전날
						// 매니저 필참
						if ((j & (1 << (manager.charAt(i) - 'A'))) == 0)
							continue;
						// 이전 날과 공통된 사람이 있어야함
						if ((j & k) == 0)
							continue;
						dp[i][j] = (dp[i][j] + dp[i - 1][k]) % MOD;
					}
				}
			}

			// 마지막 날의 값을 모두 더하면 그게 답
			int answer = 0;
			for (int i = 1; i < 16; i++)
				answer = (answer + dp[manager.length() - 1][i]) % MOD;

			sb.append(answer);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}