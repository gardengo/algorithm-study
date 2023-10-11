import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static int[] cleaner;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static Queue<Integer> que;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());
		int[][] map = new int[R][C];
		cleaner = new int[2];
		que = new ArrayDeque<Integer>();

		int idx = 0;
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] == -1) {
					cleaner[idx] = r;
					idx++;
				}
			}
		}

		for (int i = 0; i < T; i++) {
			map = spreadDust(map);
			map = moveDust(map);
		}

		int answer = 0;
		for (int i = 0; i < R; i++)
			for (int j = 0; j < C; j++) {
				if (map[i][j] == -1)
					continue;
				answer += map[i][j];
			}
		System.out.println(answer);

	}

	public static int[][] spreadDust(int[][] map) {
		int[][] result = new int[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					int cnt = 0;
					int dust = map[i][j] / 5;
					for (int d = 0; d < 4; d++) {
						if (i + dx[d] < 0 || i + dx[d] >= R || j + dy[d] < 0 || j + dy[d] >= C)
							continue;
						if (map[i + dx[d]][j + dy[d]] == -1)
							continue;
						result[i + dx[d]][j + dy[d]] += dust;
						cnt++;
					}
					result[i][j] += map[i][j] - dust * cnt;
				} else if (map[i][j] == -1) {
					result[i][j] = -1;
				}
			}
		}
		return result;
	}

	public static int[][] moveDust(int[][] map) {
		// 위쪽 순환
		int x = cleaner[0];
		int y = 0;
		while (x > 0)
			que.offer(map[--x][y]);
		while (y < C - 1)
			que.offer(map[x][++y]);
		while (x < cleaner[0])
			que.offer(map[++x][y]);
		while (y > 1)
			que.offer(map[x][--y]);
		que.poll();
		que.offer(0);
		y--;
		while (!que.isEmpty()) {
			while (x > 0)
				map[--x][y] = que.poll();
			while (y < C - 1)
				map[x][++y] = que.poll();
			while (x < cleaner[0])
				map[++x][y] = que.poll();
			while (y > 1)
				map[x][--y] = que.poll();
		}

		// 아래쪽 순환
		x = cleaner[1];
		y = 0;
		while (x < R - 1)
			que.offer(map[++x][y]);
		while (y < C - 1)
			que.offer(map[x][++y]);
		while (x > cleaner[1])
			que.offer(map[--x][y]);
		while (y > 1)
			que.offer(map[x][--y]);
		que.poll();
		que.offer(0);
		y--;
		while (!que.isEmpty()) {
			while (x < R - 1)
				map[++x][y] = que.poll();
			while (y < C - 1)
				map[x][++y] = que.poll();
			while (x > cleaner[1])
				map[--x][y] = que.poll();
			while (y > 1)
				map[x][--y] = que.poll();
		}

		return map;
	}

}
