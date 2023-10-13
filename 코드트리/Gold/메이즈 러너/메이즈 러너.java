import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int N, M, exitX, exitY, moving;
	static int[][] maze;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		maze = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++)
				maze[i][j] = Integer.parseInt(st.nextToken());
		}

		// 참가자 좌표 입력 (1명당 10)
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int px = Integer.parseInt(st.nextToken());
			int py = Integer.parseInt(st.nextToken());
			maze[px][py] += 10;
		}

		// 출구 좌표 입력 (-1)
		st = new StringTokenizer(br.readLine());
		exitX = Integer.parseInt(st.nextToken());
		exitY = Integer.parseInt(st.nextToken());
		maze[exitX][exitY] = -1;

		moving = 0;
		for (int i = 0; i < K; i++) {
			// 움직인다
			move();

			// 사각형 찾기
			int minSize = 10;
			for (int r = 1; r <= N; r++) {
				for (int c = 1; c <= N; c++) {
					if (maze[r][c] >= 10) {
						int size = findSize(r, c, exitX, exitY);
						if (size < minSize)
							minSize = size;
					}
				}
			}
			if (minSize == 10)
				break;
			int[] square = findSquare(minSize);

			// 돌린다
			rotate(square);
		}
		System.out.println(moving);
		System.out.println(exitX + " " + exitY);
	}

	public static void move() {
		int[][] after = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (maze[i][j] < 10)
					continue;

				for (int d = 0; d < 4; d++) {
					if (i + dx[d] < 1 || i + dx[d] > N || j + dy[d] < 1 || j + dy[d] > N)
						continue;
					if (0 < maze[i + dx[d]][j + dy[d]] && maze[i + dx[d]][j + dy[d]] < 10)
						continue;
					// 이동가능
					if (dist(i, j, exitX, exitY) > dist(i + dx[d], j + dy[d], exitX, exitY)) {
						if (maze[i + dx[d]][j + dy[d]] != -1)
							after[i + dx[d]][j + dy[d]] += maze[i][j];
						moving += maze[i][j] / 10;
						maze[i][j] = 0;
						break;
					}
				}
			}
		}

		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= N; j++)
				if (after[i][j] > 0)
					maze[i][j] += after[i][j];
	}

	public static int[] findSquare(int size) {
		int[] square = new int[4];
		boolean existP = false;
		boolean existE = false;
		int cnt = 0;

		root: while (cnt <= N - size) {
			for (int k = 0; k <= N - size; k++) {
				existP = false;
				existE = false;
				for (int i = 1 + cnt; i <= size + cnt; i++) {
					for (int j = 1 + k; j <= size + k; j++) {
						if (maze[i][j] >= 10)
							existP = true;
						if (maze[i][j] == -1)
							existE = true;
					}
				}
				if (existP && existE) {
					square[0] = 1 + cnt;
					square[1] = 1 + k;
					square[2] = square[0] + size - 1;
					square[3] = square[1] + size - 1;
					break root;
				}
			}
			cnt++;
		}
		return square;
	}

	public static void rotate(int[] square) {
		int sx = square[0];
		int sy = square[1];
		int ex = square[2];
		int ey = square[3];

		// 벽 돌리기
		int cnt = 0;
		for (int k = ex - sx; k >= 0; k -= 2) {
			if (k == 0) {
				if (0 < maze[sx + cnt][sy + cnt] && maze[sx + cnt][sy + cnt] < 10)
					maze[sx + cnt][sy + cnt]--;
				break;
			}

			LinkedList<Integer> list = new LinkedList<Integer>();
			for (int i = 0; i < k; i++)
				list.add(maze[sx + cnt][sy + cnt + i]);
			for (int i = 0; i < k; i++)
				list.add(maze[sx + cnt + i][ey - cnt]);
			for (int i = 0; i < k; i++)
				list.add(maze[ex - cnt][ey - cnt - i]);
			for (int i = 0; i < k; i++)
				list.add(maze[ex - cnt - i][sy + cnt]);

			for (int i = 1; i <= k; i++)
				list.addFirst(list.removeLast());
			for (int i = 0; i < k; i++) {
				maze[sx + cnt][sy + cnt + i] = list.removeFirst();
				if (0 < maze[sx + cnt][sy + cnt + i] && maze[sx + cnt][sy + cnt + i] < 10)
					maze[sx + cnt][sy + cnt + i]--;
			}
			for (int i = 0; i < k; i++) {
				maze[sx + cnt + i][ey - cnt] = list.removeFirst();
				if (0 < maze[sx + cnt + i][ey - cnt] && maze[sx + cnt + i][ey - cnt] < 10)
					maze[sx + cnt + i][ey - cnt]--;
			}
			for (int i = 0; i < k; i++) {
				maze[ex - cnt][ey - cnt - i] = list.removeFirst();
				if (0 < maze[ex - cnt][ey - cnt - i] && maze[ex - cnt][ey - cnt - i] < 10)
					maze[ex - cnt][ey - cnt - i]--;
			}
			for (int i = 0; i < k; i++) {
				maze[ex - cnt - i][sy + cnt] = list.removeFirst();
				if (0 < maze[ex - cnt - i][sy + cnt] && maze[ex - cnt - i][sy + cnt] < 10)
					maze[ex - cnt - i][sy + cnt]--;
			}
			cnt++;
		}

		// 출구 좌표 최신화
		for (int i = sx; i <= ex; i++)
			for (int j = sy; j <= ey; j++)
				if (maze[i][j] == -1) {
					exitX = i;
					exitY = j;
				}
	}

	public static int dist(int px, int py, int ex, int ey) {
		return (int) Math.abs(px - ex) + Math.abs(py - ey);
	}

	public static int findSize(int px, int py, int ex, int ey) {
		return Math.max((int) Math.abs(px - ex), (int) Math.abs(py - ey)) + 1;
	}

}
