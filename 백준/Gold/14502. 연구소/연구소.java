import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N, M, safety, answer;
	static int[][] map;
	static List<int[]> birus;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N + 2][M + 2]; // IndexOutOfBounds를 벽으로 처리
		safety = 0; // 최초에 주어진 안전영역
		birus = new ArrayList<int[]>();

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
				if (map[i][j] == 2)
					birus.add(new int[] { i, j });
			}
		}

		visited = new boolean[N + 2][M + 2];
		answer = 0;
		comb(3);
		System.out.println(answer);
	}

	private static void comb(int cnt) {
		if (cnt == 0) { // 3개 조합 완료
			bfs();
			return;
		}

		for (int a = 1; a <= N; a++)
			for (int b = 1; b <= M; b++)
				if (map[a][b] == 0) {
					if (visited[a][b])
						continue;
					visited[a][b] = true;
					map[a][b] = 1;
					safety--;

					comb(cnt - 1);
					visited[a][b] = false;
					map[a][b] = 0;
					safety++;
				}

	}

	private static void bfs() {
		int[][] copyMap = new int[N + 2][M + 2];
		for (int i = 0; i < N + 2; i++)
			for (int j = 0; j < M + 2; j++)
				copyMap[i][j] = map[i][j];

		int result = safety;
		Queue<int[]> que = new ArrayDeque<int[]>();
		for (int i = 0; i < birus.size(); i++)
			que.offer(birus.get(i));

		int cnt = 0;
		while (!que.isEmpty()) {
			int[] cur = que.poll();
			cnt++;

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
