import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main { // 클래스 시작

	static class Node { // 노드 클래스
		int no, weight; // 다음 정점의 번호, 가중치

		public Node(int no, int weight) {
			this.no = no;
			this.weight = weight;
		}
	}

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader((new InputStreamReader(System.in))); // 입력을 받기 위한 BufferedReader 객체 생성
		StringBuilder sb = new StringBuilder(); // 출력을 위한 StringBuilder 객체 생성
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫 줄을 입력받아 공백으로 나눈다
		int V = Integer.parseInt(st.nextToken()); // 정점의 개수 V
		int E = Integer.parseInt(st.nextToken()); // 간선의 개수 E
		int K = Integer.parseInt(br.readLine()); // 시작 정점의 번호 K

		ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>(); // 간선의 정보 그래프
		for (int i = 0; i <= V; i++) // V까지 사용할 것이므로 V+1만큼 만든다
			graph.add(new ArrayList<Node>()); // 그래프 초기 세팅

		for (int i = 0; i < E; i++) { // E개의 줄에 걸쳐 간선의 정보를 받는다
			st = new StringTokenizer(br.readLine()); // 간선의 정보를 공백으로 나눈다
			int front = Integer.parseInt(st.nextToken()); // 시작 정점
			int back = Integer.parseInt(st.nextToken()); // 도착 정점
			int weight = Integer.parseInt(st.nextToken()); // 가중치
			graph.get(front).add(new Node(back, weight)); // 시작 정점에서 도착 정점까지 가중치로 연결
		}

		boolean[] visited = new boolean[V + 1]; // 중복 방문 방지
		int[] weight = new int[V + 1]; // 최단 경로를 저장하는 배열
		Arrays.fill(weight, Integer.MAX_VALUE); // max로 초기 설정

		// 가중치로 정렬하는 pq 생성
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a, b) -> Integer.compare(a.weight, b.weight));
		pq.add(new Node(K, 0)); // 시작점 K, 가중치 0
		weight[K] = 0; // 시작지점 가중치 0으로 변경

		while (!pq.isEmpty()) { // pq가 비어있을 때까지 반복
			Node cur = pq.poll(); // 가중치가 최소인 현재 노드
			if (visited[cur.no]) // 방문한 적 있으면
				continue; // 지나간다
			visited[cur.no] = true; // 방문 처리

			for (int i = 0; i < graph.get(cur.no).size(); i++) { // 현재 노드와 연결된 만큼 반복
				Node next = graph.get(cur.no).get(i); // 연결된 1개의 노드
				if (cur.weight + next.weight < weight[next.no]) { // 현재까지의 가중치 + 다음 노드로의 가중치가 기존 저장된 가중치보다 작으면
					weight[next.no] = cur.weight + next.weight; // 가중치 배열에 저장한다
					pq.add(new Node(next.no, weight[next.no])); // pq에 추가
				}
			} // 다음 노드로
		} // 모든 가중치 업데이트 끝
		for (int i = 1; i <= V; i++) { // 1부터 V까지
			if (weight[i] == Integer.MAX_VALUE) // 가중치가 바뀌지 않았으면
				sb.append("INF\n"); // INF 출력
			else // 가중치가 바뀌었다면 현재 저장된 값이 최소값이므로
				sb.append(weight[i]).append("\n"); // 가중치 출력
		}
		System.out.println(sb); // sb 출력
	} // 메인 종료

} // 클래스 종료
