import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int test = 1; test <= T; test++) {
			int N = Integer.parseInt(br.readLine());
			int M = Integer.parseInt(br.readLine());
			boolean[][] graph = new boolean[N + 1][N + 1];

			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int first = Integer.parseInt(st.nextToken());
				int second = Integer.parseInt(st.nextToken());
				graph[first][second] = true;
			}

			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i == j)
						continue;
					for (int k = 1; k <= N; k++) {
						if (i == k || j == k)
							continue;
						if (graph[j][k])
							continue;
						if (graph[j][i] && graph[i][k])
							graph[j][k] = true;
					}
				}
			}

			int answer = 0;
			for (int i = 1; i <= N; i++) {
				int cnt = 0;
				for (int j = 1; j <= N; j++) {
					if (i == j)
						continue;
					if (graph[i][j])
						cnt++;
					if (graph[j][i])
						cnt++;
				}
				if (cnt == N - 1)
					answer++;
			}
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}

		System.out.println(sb);
	}

}
