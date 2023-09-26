import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static class Minsik {
		int x, y, cnt;

		public Minsik(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		char[][] maze = new char[N + 2][M + 2]; // 예외 처리를 위해 +2로 생성
		boolean[][][] visited = new boolean[1 << 7][N + 2][M + 2]; // 열쇠를 가질 때 마다 분리해서 판단
		int[] start = new int[4]; // 시작 위치

		// 미로의 가장자리를 벽으로 채운다
		for (int i = 0; i < N + 2; i++) {
			maze[i][0] = '#';
			maze[i][M + 1] = '#';
		}
		for (int j = 0; j < M + 2; j++) {
			maze[0][j] = '#';
			maze[N + 1][j] = '#';
		}

		// 미로를 입력받는다
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1; j <= M; j++) {
				maze[i][j] = str.charAt(j - 1);
				if (maze[i][j] == '0') {
					start[0] = i;
					start[1] = j;
					start[2] = 0; // 카운트
					start[3] = 1; // 가진 열쇠 비트마스킹
				}
			}
		}

		// bfs로 최단 경로 탐색
		Queue<int[]> que = new ArrayDeque<int[]>();
		que.offer(start);
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		while (!que.isEmpty()) {
			int[] cur = que.poll();
			if (visited[cur[3]][cur[0]][cur[1]])
				continue;
			visited[cur[3]][cur[0]][cur[1]] = true;

			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				if (visited[cur[3]][nx][ny])
					continue;
				if (maze[nx][ny] == '#') {
					continue;
				} else if (maze[nx][ny] >= 'a' && maze[nx][ny] <= 'f') {
					if ((cur[3] & (1 << (maze[nx][ny] - 'a' + 1))) == 0) {
						que.offer(new int[] { nx, ny, cur[2] + 1, cur[3] + (1 << (maze[nx][ny] - 'a' + 1)) });
					} else {
						que.offer(new int[] { nx, ny, cur[2] + 1, cur[3] });
					}
				} else if (maze[nx][ny] >= 'A' && maze[nx][ny] <= 'F') {
					if ((cur[3] & (1 << (maze[nx][ny] - 'A' + 1))) == (1 << (maze[nx][ny] - 'A' + 1)))
						que.offer(new int[] { nx, ny, cur[2] + 1, cur[3] });
				} else if (maze[nx][ny] == '1') {
					System.out.println(cur[2] + 1);
					return;
				} else {
					que.offer(new int[] { nx, ny, cur[2] + 1, cur[3] });
				}
			}
		}
		System.out.println(-1);
	}

}
