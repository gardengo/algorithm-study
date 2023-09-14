import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = { -1, 0, 0, 1 }; // 상 좌 우 하
	static int[] dy = { 0, -1, 1, 0 };

	static class Shark {
		int x, y, size, eat;

		public Shark(int x, int y, int eat) {
			this.x = x;
			this.y = y;
			this.size = 2;
			this.eat = eat;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] map = new int[N][N];
		Shark shark = null;
		List<int[]> fish = new LinkedList<int[]>();
		int time = 0;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9)
					shark = new Shark(i, j, 2);
				if (1 <= map[i][j] && map[i][j] <= 6)
					fish.add(new int[] { i, j });
			}
		}

		root: while (!fish.isEmpty()) {
			PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> {
				if (a[2] == b[2]) {
					if (a[0] == b[0])
						return Integer.compare(a[1], b[1]);
					return Integer.compare(a[0], b[0]);
				}
				return Integer.compare(a[2], b[2]);
			});
			pq.offer(new int[] { shark.x, shark.y, 0 });
			boolean[][] visited = new boolean[N][N];
			map[shark.x][shark.y] = 0;

			while (!pq.isEmpty()) {
				int[] now = pq.poll();
				if (0 < map[now[0]][now[1]] && map[now[0]][now[1]] < shark.size) {
					int nx = now[0];
					int ny = now[1];
					int cost = now[2];

					map[nx][ny] = 0;
					time += cost;
					fish.remove(new int[] { nx, ny });

					// 상어 위치 바꾸고 크기 키워야함
					shark.x = nx;
					shark.y = ny;
					if (--shark.eat == 0) {
						shark.size++;
						shark.eat = shark.size;
					}

					continue root;
				}

				if (visited[now[0]][now[1]])
					continue;
				visited[now[0]][now[1]] = true;

				for (int d = 0; d < 4; d++) {
					if (now[0] + dx[d] >= 0 && now[0] + dx[d] < N && now[1] + dy[d] >= 0 && now[1] + dy[d] < N) {
						if (!visited[now[0] + dx[d]][now[1] + dy[d]]
								&& map[now[0] + dx[d]][now[1] + dy[d]] <= shark.size) {
							pq.offer(new int[] { now[0] + dx[d], now[1] + dy[d], now[2] + 1 });
						}
					}
				}
			}
			// 여기까지 오면 더 이상 먹을 물고기가 없는 것
			break;
		}

		System.out.println(time);
	}

}
