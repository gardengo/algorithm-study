import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean[][] graph = new boolean[N + 1][N + 1];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a][b] = true;
		}

		for (int j = 1; j <= N; j++) { // 중간
			for (int i = 1; i <= N; i++) { // 시작
				if (i == j)
					continue;
				if (graph[i][j]) {
					for (int k = 1; k <= N; k++) {
						if (graph[j][k]) {
							if (graph[i][k])
								continue;
							graph[i][k] = true;
						}
					}
				}
			}
		}

		int answer = 0;
		for (int i = 1; i <= N; i++) {
			int cnt = 0;
			for (int j = 1; j <= N; j++) {
				if (graph[i][j])
					cnt++;
				if (graph[j][i])
					cnt++;
			}
			if (cnt == N - 1)
				answer++;
		}
		System.out.println(answer);
	}

}
