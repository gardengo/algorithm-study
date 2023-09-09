import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		boolean[] visited = new boolean[N];

		dfs(0, arr, visited);
		System.out.println(answer);
	}

	private static void dfs(int depth, int[] arr, boolean[] visited) {
		if (depth == N) {
			answer++;
			return;
		}

		// 순열 -> index의 차이와 번호의 차이가 같으면 안됨
		root: for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			if (depth > 0) { // depth가 1이상 이면
				for (int j = 0; j < depth; j++) {
					if (Math.abs(arr[j] - i) == depth - j)
						continue root;
				}
			}
			arr[depth] = i;
			visited[i] = true;
			dfs(depth + 1, arr, visited);
			visited[i] = false;
		}
	}

}
