import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	static int N;
	static int[][] map;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++)
				map[i][j] = str.charAt(j) - '0';
		}

		int cnt = 0;
		PriorityQueue<Integer> que = new PriorityQueue<Integer>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 1) {
					que.offer(findNum(i, j));
					cnt++;
				}
			}
		}

		sb.append(cnt).append("\n");
		while (!que.isEmpty())
			sb.append(que.poll()).append("\n");
		System.out.println(sb);
	}

	public static int findNum(int r, int c) {
		int result = 0;
		Queue<int[]> que = new ArrayDeque<int[]>();
		que.add(new int[] { r, c });
		while (!que.isEmpty()) {
			int[] cur = que.poll();
			if (map[cur[0]][cur[1]] != 1)
				continue;
			result++;
			map[cur[0]][cur[1]] = 2;
			for (int d = 0; d < 4; d++) {
				if (cur[0] + dx[d] < 0 || cur[0] + dx[d] >= N || cur[1] + dy[d] < 0 || cur[1] + dy[d] >= N)
					continue;
				que.offer(new int[] { cur[0] + dx[d], cur[1] + dy[d] });
			}
		}
		return result;
	}

}
