import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] arr;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		boolean[] visited = new boolean[N];
		int[] result = new int[M];

		permutation(0, result, visited);
		System.out.println(sb);
	}

	private static void permutation(int x, int[] result, boolean[] visited) {
		if (x == M) {
			for (int i = 0; i < M; i++)
				sb.append(result[i]).append(" ");
			sb.append("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			result[x] = arr[i];
			permutation(x + 1, result, visited);
			visited[i] = false;
		}
	}
}
