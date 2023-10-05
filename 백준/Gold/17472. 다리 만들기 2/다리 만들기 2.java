import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map, graph;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		// bfs를 이용해서 섬을 2~7의 숫자로 바꾸자
		int island = 0; // 섬의 개수
		Queue<int[]> islandQ = new ArrayDeque<int[]>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 1) {
					islandQ.offer(new int[] { i, j });
					island++;
				}

				while (!islandQ.isEmpty()) {
					int[] cur = islandQ.poll();
					if (map[cur[0]][cur[1]] != 1)
						continue;
					map[cur[0]][cur[1]] += island;

					for (int d = 0; d < 4; d++) {
						if (cur[0] + dx[d] < 0 || cur[0] + dx[d] >= N || cur[1] + dy[d] < 0 || cur[1] + dy[d] >= M)
							continue;
						if (map[cur[0] + dx[d]][cur[1] + dy[d]] == 1)
							islandQ.offer(new int[] { cur[0] + dx[d], cur[1] + dy[d] });
					}
				}
			}
		}

		// dfs로 각 섬에 만들 수 있는 다리를 구해보자
		graph = new int[island + 2][island + 2];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0) {
					for (int d = 0; d < 4; d++)
						dfs(i, j, map[i][j], d, 0);
				}
			}
		}

		// 섬을 연결시키자(MST)
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> Integer.compare(a[2], b[2]));
		Set<Integer> connected = new HashSet<Integer>();
		int sum = 0;

		for (int i = 2; i <= island + 1; i++) {
			if (graph[2][i] != 0)
				pq.offer(new int[] { 2, i, graph[2][i] });
		}
		connected.add(2);

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			if (connected.contains(cur[1]))
				continue;
			connected.add(cur[1]);
			sum += cur[2];

			for (int i = 2; i <= island + 1; i++) {
				if (connected.contains(i))
					continue;
				if (graph[cur[1]][i] != 0)
					pq.offer(new int[] { cur[1], i, graph[cur[1]][i] });
			}
		}

//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++)
//				System.out.print(map[i][j] + " ");
//			System.out.println();
//		}
//		System.out.println();
//		for (int i = 2; i <= island + 1; i++) {
//			for (int j = 2; j <= island + 1; j++)
//				System.out.print(graph[i][j] + " ");
//			System.out.println();
//		}
//		System.out.println();
//		System.out.println(connected);

		if (connected.size() == island)
			System.out.println(sum);
		else
			System.out.println(-1);
	}

	public static void dfs(int x, int y, int num, int d, int cnt) {
		if (x + dx[d] < 0 || x + dx[d] >= N || y + dy[d] < 0 || y + dy[d] >= M)
			return;
		if (map[x + dx[d]][y + dy[d]] == 0) {
			dfs(x + dx[d], y + dy[d], num, d, cnt + 1);
		} else {
			if (num == map[x + dx[d]][y + dy[d]])
				return;
			if (cnt >= 2) {
				if (graph[num][map[x + dx[d]][y + dy[d]]] == 0)
					graph[num][map[x + dx[d]][y + dy[d]]] = cnt;
				else
					graph[num][map[x + dx[d]][y + dy[d]]] = cnt < graph[num][map[x + dx[d]][y + dy[d]]] ? cnt
							: graph[num][map[x + dx[d]][y + dy[d]]];
			}
			return;
		}
	}

}
