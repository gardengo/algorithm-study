import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static int N, M, answer;
	private static char[][] maze;
	private static int[] dx = { 1, 0, -1, 0 }; // 하우상좌
	private static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maze = new char[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++)
				maze[i][j] = str.charAt(j);
		} // 입력 종료

		boolean[][] visited = new boolean[N][M];
		answer = N * M;
		bfs(0, 0, 1, visited);
		System.out.println(answer);

	}

	private static void bfs(int n, int m, int move, boolean[][] visited) {
		Queue<int[]> que = new ArrayDeque<int[]>(); // n, m, move를 갖는 큐
		que.offer(new int[] { n, m, move });
		visited[n][m] = true;

		while (!que.isEmpty()) {
			int[] now = que.poll();
			if (now[0] == N - 1 && now[1] == M - 1) {
				answer = now[2];
				return;
			}

			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];
				if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
					if (maze[nx][ny] == '1' && !visited[nx][ny]) {
						visited[nx][ny] = true;
						que.offer(new int[] { nx, ny, now[2] + 1 });
					}
				}
			}
		}
	}

}
