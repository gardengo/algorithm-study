import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n + 1]; // 말단 노드에서의 최대값

		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = i; j >= 1; j--) { // n번째 줄에는 n개의 입력이 있다
				int x = Integer.parseInt(st.nextToken());
				if (j == 1)
					dp[j] = dp[j] + x;
				else
					dp[j] = Math.max(dp[j - 1] + x, dp[j] + x);
			}
		}
		
		int answer = 0;
		for (int i = 1; i <= n; i++)
			answer = dp[i] > answer ? dp[i] : answer;
		System.out.println(answer);
	}

}
