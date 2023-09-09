import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		dp = new int[N];

		func();
		int answer = 0;
		for (int i = 0; i < N; i++)
			answer = dp[i] > answer ? dp[i] : answer;
			
		System.out.println(answer);
	}

	static void func() {
		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i])
					dp[i] = dp[j] + 1 > dp[i] ? dp[j] + 1 : dp[i];
			}
		}
	}
}
