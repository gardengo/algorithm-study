import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[] result = new int[41];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		result[0] = 1;
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			fibonacci(N);
			if (N == 0)
				sb.append(result[0]).append(" ").append(0).append("\n");
			else if (N == 1)
				sb.append(0).append(" ").append(result[1]).append("\n");
			else
				sb.append(result[N - 1]).append(" ").append(result[N]).append("\n");
		}
		System.out.println(sb);
	}

	private static int fibonacci(int n) {
		if (n == 0) {
			return 0;
		} else if (n == 1) {
			return result[1] = 1;
		} else {
			if (result[n] == 0 || result[n - 1] == 0)
				return result[n] = fibonacci(n - 1) + fibonacci(n - 2);
			return result[n];
		}
	}

}
