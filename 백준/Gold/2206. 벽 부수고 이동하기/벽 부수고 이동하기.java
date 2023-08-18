import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, answer;
	static char[][] map;
	static int[] dx = { 1, 0, -1, 0 }; // 하우상좌
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N + 2][M + 2]; // 경계선을 2로 채워 경계선 처리를 하자
		for (int i = 1; i <= N; i++) { // 맵 채우기
			String str = br.readLine();
			for (int j = 1; j <= M; j++)
				map[i][j] = str.charAt(j - 1);
		}
		for (int i = 0; i < M + 2; i++) { // 상,하 -로 채우기
			map[0][i] = '-';
			map[N + 1][i] = '-';
		}
		for (int i = 0; i < N + 2; i++) { // 좌,우 -로 채우기
			map[i][0] = '-';
			map[i][M + 1] = '-';
		}

		answer = 1000000; // 최대값을 초기값으로
		boolean[][] visited = new boolean[N + 2][M + 2]; // 이미 방문한 곳에 다시 돌아가지 않기
		boolean[][] broken_visited = new boolean[N + 2][M + 2]; // 벽을 부수고 방문한 visited
		search(1, 1, true, 1, visited, broken_visited); // (1,1)에서 출발해서 (N,M)까지 벽을 1번 부술 수 있다
		if (answer == 1000000) // answer가 바뀌지 않았으면 도착 x
			System.out.println(-1);
		else
			System.out.println(answer);
	}

	static void search(int nx, int ny, boolean brk, int move, boolean[][] visited, boolean[][] broken_visited) {
		Queue<Step> que = new ArrayDeque<Step>(); // bfs 탐색을 위한 큐
		que.offer(new Step(nx, ny, brk, move));
		visited[nx][ny] = true; // 시작 위치를 true로

		while (!que.isEmpty()) {
			Step now = que.poll();

			if (now.nx == N && now.ny == M) { // (N,M)에 도착하면 끝난다
				answer = now.move;
				return;
			}
			for (int d = 0; d < 4; d++) { // 하우상좌로 움직인다
				if (now.brk) { // 벽을 부술 수 있으면
					if (map[now.nx + dx[d]][now.ny + dy[d]] == '0' && !visited[now.nx + dx[d]][now.ny + dy[d]]) {
						visited[now.nx + dx[d]][now.ny + dy[d]] = true; // 다음 위치를 방문하고
						que.offer(new Step(now.nx + dx[d], now.ny + dy[d], true, now.move + 1)); // 큐에 넣는다
					} else if (map[now.nx + dx[d]][now.ny + dy[d]] == '1') { // 벽인 경우
						broken_visited[now.nx + dx[d]][now.ny + dy[d]] = true; // 벽을 부수고 방문하고
						que.offer(new Step(now.nx + dx[d], now.ny + dy[d], false, now.move + 1)); // 큐에 넣는다
					} else { // 경계선인 경우
						continue;
					}
				} else { // 이미 벽을 부쉈으면
					if (map[now.nx + dx[d]][now.ny + dy[d]] == '0' && !visited[now.nx + dx[d]][now.ny + dy[d]]
							&& !broken_visited[now.nx + dx[d]][now.ny + dy[d]]) { // 갈 수 있으면 이동한다
						broken_visited[now.nx + dx[d]][now.ny + dy[d]] = true; // 다음 위치를 방문하고
						que.offer(new Step(now.nx + dx[d], now.ny + dy[d], false, now.move + 1)); // 큐에 넣는다
					} else { // 벽 또는 경계선
						continue;
					}
				}
			}
		}
	}

	static class Step {
		int nx, ny, move;
		boolean brk;

		public Step(int nx, int ny, boolean brk, int move) {
			this.nx = nx;
			this.ny = ny;
			this.brk = brk;
			this.move = move;
		}
	}

}
