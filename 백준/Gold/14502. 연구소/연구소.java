import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N, M, safety, answer;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 2][M + 2]; // IndexOutOfBounds를 벽으로 처리
		safety = 0; // 최초에 주어진 안전영역

		for (int i = 0; i < N + 2; i++) {
			map[i][0] = 1;
			map[i][M + 1] = 1;
		}
		for (int j = 0; j < M + 2; j++) {
			map[0][j] = 1;
			map[N + 1][j] = 1;
		}
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0)
					safety++;
			}
		}

		answer = 0;
		comb(3);
		System.out.println(answer - 3);
	}

	private static void comb(int cnt) {
		if (cnt == 0) { // 3개 조합 완료
			bfs();
			return;
		}

		for (int a = 1; a <= N; a++)
			for (int b = 1; b <= M; b++)
				if (map[a][b] == 0) {
					map[a][b] = 1;
					comb(cnt - 1);
					map[a][b] = 0;
				}
	}

	private static void bfs() {
		int result = safety;
		Queue<int[]> que = new ArrayDeque<int[]>();
		int[][] copyMap = new int[N + 2][M + 2];
		for (int i = 0; i < N + 2; i++)
			for (int j = 0; j < M + 2; j++) {
				copyMap[i][j] = map[i][j];
				if (map[i][j] == 2)
					que.offer(new int[] { i, j });
			}

		while (!que.isEmpty()) {
			int[] cur = que.poll();

			for (int d = 0; d < 4; d++) {
				if (copyMap[cur[0] + dx[d]][cur[1] + dy[d]] == 0) {
					copyMap[cur[0] + dx[d]][cur[1] + dy[d]] = 2;
					result--;
					if (result <= answer)
						return;
					que.offer(new int[] { cur[0] + dx[d], cur[1] + dy[d] });
				}
			}
		}

		answer = result > answer ? result : answer;
	}

}
