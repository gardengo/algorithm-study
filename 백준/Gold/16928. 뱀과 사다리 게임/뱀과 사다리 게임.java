import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] ladderNsnail;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		ladderNsnail = new int[N + M][2];
		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			ladderNsnail[i][0] = Integer.parseInt(st.nextToken());
			ladderNsnail[i][1] = Integer.parseInt(st.nextToken());
		}

		boolean[] visited = new boolean[101];
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> {
			if (a[1] == b[1])
				return Integer.compare(b[0], a[0]);
			return Integer.compare(a[1], b[1]);

		});

		for (int i = 2; i <= 7; i++) {
			int n = move(i);
			pq.offer(new int[] { n, 1 });
		}
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			if (visited[cur[0]])
				continue;
			visited[cur[0]] = true;
			if (94 <= cur[0]) {
				System.out.println(cur[1] + 1);
				return;
			}
			for (int i = 1; i <= 6; i++) {
				int n = move(cur[0] + i);
				if (visited[n])
					continue;
				pq.offer(new int[] { n, cur[1] + 1 });
			}
		}
	}

	public static int move(int n) {
		for (int i = 0; i < N + M; i++) {
			if (ladderNsnail[i][0] == n) {
				n = ladderNsnail[i][1];
				i = -1;
			}
		}
		return n;
	}

}
