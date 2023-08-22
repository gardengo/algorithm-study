import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 메모리 123,844kb 실행시간 658ms
public class Solution {

	static class Node {
		int idx;
		long cost;

		public Node(int idx, long cost) {
			this.idx = idx;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			int N = Integer.parseInt(st.nextToken()); // 정점의 개수 N
			int M = Integer.parseInt(st.nextToken()); // 간선의 개수 M
			int start = Integer.parseInt(st.nextToken()); // 출발 정점의 번호
			int finish = Integer.parseInt(st.nextToken()); // 도착 정점의 번호

			ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
			for (int i = 0; i <= N; i++)
				graph.add(new ArrayList<Node>());

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()); // 시작점
				int b = Integer.parseInt(st.nextToken()); // 끝점
				int cost = Integer.parseInt(st.nextToken()); // 가중치
				graph.get(a).add(new Node(b, cost));
				graph.get(b).add(new Node(a, cost));
			}

			long answer = 0;
			long[] dist = new long[N + 1];
			for (int i = 0; i < N + 1; i++)
				dist[i] = Long.MAX_VALUE;

			PriorityQueue<Node> pq = new PriorityQueue<Node>((a, b) -> Long.compare(a.cost, b.cost));
			pq.offer(new Node(start, 0));
			dist[start] = 0;
			while (!pq.isEmpty()) {
				Node cur = pq.poll();

				if (cur.idx == finish) {
					answer = dist[cur.idx];
					break;
				}

				if (dist[cur.idx] < cur.cost)
					continue;

				for (int i = 0; i < graph.get(cur.idx).size(); i++) {
					Node next = graph.get(cur.idx).get(i);
					if (dist[next.idx] > cur.cost + next.cost) {
						dist[next.idx] = cur.cost + next.cost;
						pq.offer(new Node(next.idx, dist[next.idx]));
					}
				}
			}
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

}
