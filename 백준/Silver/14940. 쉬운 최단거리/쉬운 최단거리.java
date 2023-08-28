import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken()); // 2 <= n <= 1000
		int m = Integer.parseInt(st.nextToken()); // 2 <= m <= 1000

		int[][] map = new int[n + 2][m + 2]; // 경계선을 0으로 채워서 예외처리하자
		int[][] dist = new int[n + 2][m + 2];
		Queue<int[]> que = new ArrayDeque<int[]>(); // bfs로 최단거리 구하기

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) // 목표지점을 큐에 넣어준다
					que.add(new int[] { i, j, 0 });
			}
		}

		boolean[][] visited = new boolean[n + 2][m + 2]; // 재방문 방지
		while (!que.isEmpty()) {
			int[] cur = que.poll();
			int x = cur[0];
			int y = cur[1];
			int distance = cur[2];

			if (visited[x][y])
				continue;

			dist[x][y] = distance;
			visited[x][y] = true;

			for (int d = 0; d < 4; d++) {
				if (map[x + dx[d]][y + dy[d]] == 1) {
					que.add(new int[] { x + dx[d], y + dy[d], distance + 1 });
				}
			}
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (map[i][j] == 1 && !visited[i][j])
					sb.append(-1).append(" ");
				else
					sb.append(dist[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
