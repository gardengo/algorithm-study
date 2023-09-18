import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		int[] dp = new int[n];
		dp[0] = arr[0];
		for (int i = 1; i < n; i++)
			dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);

		int max = -100000000;
		for (int i = 0; i < n; i++)
			max = dp[i] > max ? dp[i] : max;
		System.out.println(max);
	}

}
