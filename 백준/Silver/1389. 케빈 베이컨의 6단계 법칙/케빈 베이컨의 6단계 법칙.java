import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] graph = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(graph[i], 100);
			graph[i][i] = 0;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a][b] = 1;
			graph[b][a] = 1;
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;
				for (int k = 1; k <= N; k++) {
					if (i == k || j == k)
						continue;
					graph[j][k] = Math.min(graph[j][k], graph[j][i] + graph[i][k]);
				}
			}
		}

		int answer = 0;
		int sum = Integer.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			int tmp = 0;
			for (int j = 1; j <= N; j++)
				tmp += graph[i][j];
			if (tmp < sum) {
				sum = tmp;
				answer = i;
			}
		}
		System.out.println(answer);
	}

}
