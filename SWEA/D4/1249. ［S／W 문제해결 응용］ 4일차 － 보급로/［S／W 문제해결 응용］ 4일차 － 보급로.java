import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	private static class Node {
		private int x, y, cost;

		public Node(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine().trim());
			int[][] map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++)
					map[i][j] = str.charAt(j) - '0';
			}

			sb.append("#").append(test_case).append(" ");

			int[] dx = { 1, 0, -1, 0 };
			int[] dy = { 0, 1, 0, -1 };
			int[][] dist = new int[N][N];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					dist[i][j] = Integer.MAX_VALUE;
			dist[0][0] = 0;

			PriorityQueue<Node> pq = new PriorityQueue<Node>((a, b) -> Integer.compare(a.cost, b.cost));
			pq.offer(new Node(0, 0, 0));

			while (!pq.isEmpty()) {
				Node cur = pq.poll();

				if (cur.x == N - 1 && cur.y == N - 1) {
					sb.append(dist[N - 1][N - 1]).append("\n");
					break;
				}

				if (dist[cur.x][cur.y] < cur.cost)
					continue;

				for (int i = 0; i < 4; i++) {
					if (cur.x + dx[i] >= 0 && cur.x + dx[i] < N && cur.y + dy[i] >= 0 && cur.y + dy[i] < N) {
						Node next = new Node(cur.x + dx[i], cur.y + dy[i], map[cur.x + dx[i]][cur.y + dy[i]]);
						if (dist[next.x][next.y] > cur.cost + next.cost) {
							dist[next.x][next.y] = cur.cost + next.cost;
							pq.offer(new Node(next.x, next.y, dist[next.x][next.y]));
						}
					}
				}
			}
		}
		System.out.println(sb);
	}

}
