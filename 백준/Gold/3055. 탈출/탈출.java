import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R + 2][C + 2];
		int[] start = new int[3];
		List<int[]> water = new ArrayList<int[]>();

		for (int i = 0; i < R + 2; i++) {
			map[i][0] = 'X';
			map[i][C + 1] = 'X';
		}
		for (int j = 0; j < C + 2; j++) {
			map[0][j] = 'X';
			map[R + 1][j] = 'X';
		}

		for (int i = 1; i <= R; i++) {
			String str = br.readLine();
			for (int j = 1; j <= C; j++) {
				map[i][j] = str.charAt(j - 1);
				if (map[i][j] == 'S') {
					start[0] = i;
					start[1] = j;
					start[2] = 0;
				} else if (map[i][j] == '*') {
					water.add(new int[] { i, j, -1 });
				}
			}
		}

		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };
		Queue<int[]> que = new ArrayDeque<int[]>();
		boolean[][] visited = new boolean[R + 2][C + 2];

		// 다음 시간에 물이 찰 예정인 칸으로 이동하지 못하므로 물을 한 타임 먼저 퍼트리자
		for (int i = 0; i < water.size(); i++)
			que.offer(water.get(i));
		que.offer(start);

		while (!que.isEmpty()) {
			int[] cur = que.poll();

			if (cur[2] != -1) { // 고슴도치면
				if (visited[cur[0]][cur[1]])
					continue;
				visited[cur[0]][cur[1]] = true;
			}

			if (map[cur[0]][cur[1]] == 'D') {
				System.out.println(cur[2]);
				return;
			}

			for (int d = 0; d < 4; d++) {
				if (map[cur[0] + dx[d]][cur[1] + dy[d]] == 'X')
					continue;
				if (map[cur[0] + dx[d]][cur[1] + dy[d]] == '*')
					continue;
				if (cur[2] == -1) { // 물은 cnt:-1로 설정
					if (map[cur[0] + dx[d]][cur[1] + dy[d]] == 'D')
						continue;
					map[cur[0] + dx[d]][cur[1] + dy[d]] = '*';
					que.offer(new int[] { cur[0] + dx[d], cur[1] + dy[d], -1 });
				} else { // 고슴도치
					if (visited[cur[0] + dx[d]][cur[1] + dy[d]])
						continue;
					que.offer(new int[] { cur[0] + dx[d], cur[1] + dy[d], cur[2] + 1 });
				}
			}
		}

		System.out.println("KAKTUS");
	}

}
