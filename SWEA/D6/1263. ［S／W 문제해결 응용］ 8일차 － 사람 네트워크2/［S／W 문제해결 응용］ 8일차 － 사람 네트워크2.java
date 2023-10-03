import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int test = 1; test <= T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[][] graph = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++)
				for (int j = 1; j <= N; j++)
					graph[i][j] = Integer.parseInt(st.nextToken());

			for (int i = 1; i <= N; i++) { // 중간
				for (int j = 1; j <= N; j++) { // 출발
					if (i == j)
						continue;
					if (graph[j][i] == 0)
						continue;
					for (int k = 1; k <= N; k++) { // 도착
						if (i == k || j == k)
							continue;
						if (graph[i][k] == 0)
							continue;
						if (graph[j][k] == 0)
							graph[j][k] = graph[j][i] + graph[i][k];
						else
							graph[j][k] = graph[j][i] + graph[i][k] < graph[j][k] ? graph[j][i] + graph[i][k]
									: graph[j][k];
					}
				}
			}

			int min = Integer.MAX_VALUE;
			for (int i = 1; i <= N; i++) {
				int sum = 0;
				for (int j = 1; j <= N; j++)
					sum += graph[i][j];
				min = sum < min ? sum : min;
			}
			sb.append("#").append(test).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}

}
