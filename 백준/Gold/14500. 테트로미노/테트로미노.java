import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, answer;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		boolean[][] visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 1, map[i][j], visited);
			}
		}

		System.out.println(answer);
	}

	static void dfs(int x, int y, int cnt, int sum, boolean[][] visited) {
		if (cnt == 4) {
			answer = sum > answer ? sum : answer;
			return;
		} else if (cnt > 4)
			return;

		for (int i = 0; i < 4; i++) {
			if (x + dx[i] >= 0 && x + dx[i] < N && y + dy[i] >= 0 && y + dy[i] < M) {
				if (visited[x + dx[i]][y + dy[i]])
					continue;
				visited[x + dx[i]][y + dy[i]] = true;
				dfs(x + dx[i], y + dy[i], cnt + 1, sum + map[x + dx[i]][y + dy[i]], visited);
				dfs(x, y, cnt + 1, sum + map[x + dx[i]][y + dy[i]], visited);
				visited[x + dx[i]][y + dy[i]] = false;
			}
		}

	}
}