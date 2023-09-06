import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int[][] graph = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++)
			Arrays.fill(graph[i], 10000000);

		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph[start][end] = cost < graph[start][end] ? cost : graph[start][end];
		}

		for (int i = 1; i <= n; i++) { // 중간 도시
			start: for (int j = 1; j <= n; j++) { // 시작 도시
				for (int k = 1; k <= n; k++) { // 도착 도시
					if (j == k || i == k)
						continue;
					if (i == j)
						continue start;

					if (graph[j][k] > graph[j][i] + graph[i][k])
						graph[j][k] = graph[j][i] + graph[i][k];
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (graph[i][j] == 10000000)
					sb.append(0).append(" ");
				else
					sb.append(graph[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
