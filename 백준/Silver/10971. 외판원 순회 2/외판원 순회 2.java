import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, answer;
	static int[][] graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 도시의 수 (2 <= N <= 10)
		graph = new int[N + 1][N + 1]; // 비용 행렬
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++)
				graph[i][j] = Integer.parseInt(st.nextToken());
		}
		boolean[] visited = new boolean[N + 1];
		answer = 10000000;
		visited[1] = true;
		dfs(1, 0, 0, visited);
		System.out.println(answer);
	}

	private static void dfs(int num, int depth, int sum, boolean[] visited) {
		if (depth == N - 1) {
			if (graph[num][1] == 0)
				return;
			sum += graph[num][1];
			answer = sum < answer ? sum : answer;
			return;
		}

		for (int i = 1; i <= N; i++) {
			if (visited[i] || graph[num][i] == 0)
				continue;
			visited[i] = true;
			dfs(i, depth + 1, sum + graph[num][i], visited);
			visited[i] = false;
		}
	}
}
