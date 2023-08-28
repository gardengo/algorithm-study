import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dy = { 0, 0, -1, 1 };
	static boolean[][] map;
	static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken()); // 가로길이
			N = Integer.parseInt(st.nextToken()); // 세로길이
			int K = Integer.parseInt(st.nextToken()); // 배추 위치의 개수

			map = new boolean[N][M];
			Queue<int[]> que = new ArrayDeque<int[]>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[y][x] = true;
				que.add(new int[] { y, x });
			}

			int answer = 0;

			while (!que.isEmpty()) {
				int[] cur = que.poll();
				int y = cur[0];
				int x = cur[1];

				if (map[y][x]) {
					answer++;
					map[y][x] = false;
				}
				dfs(y, x);
			}
			sb.append(answer).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int y, int x) {
		for (int d = 0; d < 4; d++) {
			if (x + dx[d] < 0 || x + dx[d] >= M || y + dy[d] < 0 || y + dy[d] >= N)
				continue;
			if (map[y + dy[d]][x + dx[d]]) {
				map[y + dy[d]][x + dx[d]] = false;
				dfs(y + dy[d], x + dx[d]);
			}
		}
	}

}
