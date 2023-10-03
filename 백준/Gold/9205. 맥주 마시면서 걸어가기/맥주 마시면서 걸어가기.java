import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());

		test: for (int test = 1; test <= t; test++) {
			int n = Integer.parseInt(br.readLine());
			int[] home = new int[2];
			int[][] store = new int[n][2];
			int[] festival = new int[2];

			StringTokenizer st = new StringTokenizer(br.readLine());
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				store[i][0] = Integer.parseInt(st.nextToken());
				store[i][1] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			festival[0] = Integer.parseInt(st.nextToken());
			festival[1] = Integer.parseInt(st.nextToken());

			Queue<int[]> que = new ArrayDeque<int[]>();
			boolean[] visited = new boolean[n];
			que.offer(home);
			while (!que.isEmpty()) {
				int[] cur = que.poll();
				for (int i = 0; i < n; i++) {
					if (visited[i])
						continue;
					if (canMove(cur, store[i])) {
						que.offer(store[i]);
						visited[i] = true;
					}
				}
				if (canMove(cur, festival)) {
					sb.append("happy\n");
					continue test;
				}
			}
			sb.append("sad\n");
		}
		System.out.println(sb);
	}

	public static boolean canMove(int[] cur, int[] next) {
		if (Math.abs(cur[0] - next[0]) + Math.abs(cur[1] - next[1]) <= 1000)
			return true;
		else
			return false;
	}

}
