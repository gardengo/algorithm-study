import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = 0;
		int[][] map = new int[125][125];
		boolean[][] visited = new boolean[125][125];
		PriorityQueue<int[]> que = new PriorityQueue<int[]>((a, b) -> Integer.compare(a[2], b[2]));

		while (true) {
			tc++;
			int N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					visited[i][j] = false;
				}
			}

			que.clear();
			que.offer(new int[] { 0, 0, map[0][0] });
			int[] dx = { -1, 1, 0, 0 };
			int[] dy = { 0, 0, -1, 1 };

			while (!que.isEmpty()) {
				int[] cur = que.poll();
				if (visited[cur[0]][cur[1]])
					continue;

				visited[cur[0]][cur[1]] = true;
				map[cur[0]][cur[1]] = cur[2];
				if (cur[0] == N - 1 && cur[1] == N - 1)
					break;

				for (int d = 0; d < 4; d++) {
					int nx = cur[0] + dx[d];
					int ny = cur[1] + dy[d];
					if (nx < 0 || nx >= N || ny < 0 || ny >= N)
						continue;
					if (visited[nx][ny])
						continue;
					que.offer(new int[] { nx, ny, map[nx][ny] + cur[2] });
				}
			}

			sb.append("Problem ").append(tc).append(": ").append(map[N - 1][N - 1]).append("\n");
		}
		System.out.println(sb);
	}

}
