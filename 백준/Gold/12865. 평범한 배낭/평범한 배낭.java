import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] dp = new int[K + 1];

		int[][] things = new int[N][2]; // 무게, 가치
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			things[i][0] = Integer.parseInt(st.nextToken());
			things[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(things, (a, b) -> Integer.compare(a[0], b[0])); // 무게로 정렬
		for (int i = 0; i < N; i++) {
			int w = things[i][0];
			int v = things[i][1];

			for (int j = K - w; j >= 0; j--) {
				if (dp[j + w] < dp[j] + v)
					dp[j + w] = dp[j] + v;
			}
		}

		int max = 0;
		for (int i = 0; i <= K; i++)
			max = dp[i] > max ? dp[i] : max;
		System.out.println(max);
	}

}
