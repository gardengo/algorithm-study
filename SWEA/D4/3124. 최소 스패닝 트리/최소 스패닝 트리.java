import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution { // 클래스 시작

	static class Node {
		int no, cost;

		public Node(int no, int cost) {
			super();
			this.no = no;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader((new InputStreamReader(System.in))); // 입력을 받기 위한 BufferedReader 객체 생성
		StringBuilder sb = new StringBuilder(); // 출력을 위한 StringBuilder 객체 생성
		int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스의 수 T

		for (int test_case = 1; test_case <= T; test_case++) { // 테스트 케이스 시작
			StringTokenizer st = new StringTokenizer(br.readLine().trim()); // 한 줄을 받아 공백으로 나눈다
			int V = Integer.parseInt(st.nextToken()); // 정점의 개수 V
			int E = Integer.parseInt(st.nextToken()); // 간선의 개수 E

			ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>(); // 간선의 정보를 담은 그래프
			for (int i = 0; i <= V; i++) // 1부터 V까지
				graph.add(new ArrayList<Node>()); // 정점 추가

			for (int i = 0; i < E; i++) { // E개의 줄에서 간선의 정보를 받는다
				st = new StringTokenizer(br.readLine().trim()); // 간선의 정보를 공백으로 나눈다
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				graph.get(a).add(new Node(b, cost));
				graph.get(b).add(new Node(a, cost));
			}

			boolean[] visited = new boolean[V + 1];
			int[] weight = new int[V + 1];
			Arrays.fill(weight, Integer.MAX_VALUE);
			long result = 0;

			PriorityQueue<Node> pq = new PriorityQueue<Node>((a, b) -> Integer.compare(a.cost, b.cost)); // 비용으로 정렬하는 pq
			pq.offer(new Node(1, 0)); // 임의의 초기 정점 설정(1)
			weight[1] = 0;

			while (!pq.isEmpty()) {
				Node cur = pq.poll();
				if (visited[cur.no])
					continue;
				visited[cur.no] = true;
				result += cur.cost;

				for (int i = 0; i < graph.get(cur.no).size(); i++) {
					Node next = graph.get(cur.no).get(i);
					if (!visited[next.no] && next.cost < weight[next.no]) {
						weight[next.no] = next.cost;
						pq.offer(next);
					}
				}
			}
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	} // 메인 종료

} // 클래스 종료
