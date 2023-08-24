import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution { // 클래스 시작
	static int parents[];

	private static void make(int n) { // 집합을 만드는 메소드
		parents = new int[n + 1]; // n크기의 집합을 만든다
		for (int i = 1; i <= n; i++) // 초기값 설정
			parents[i] = i; // 자기 자신을 가르키게 설정한다
	}

	private static int find(int n) { // 원소를 찾는 메소드
		if (parents[n] == n) // 부모와 자신이 같으면
			return n; // 거기가 루트이다
		return parents[n] = find(parents[n]); // 루트를 찾을때까지 find 호출
	}

	private static boolean union(int a, int b) { // 집합을 합치는 메소드
		int aRoot = find(a); // a루트를 찾고
		int bRoot = find(b); // b루트를 찾는다

		if (aRoot == bRoot) { // a루트와 b루트가 다르면
			return false; // false를 리턴한다
		} else { // a루트와 b루트가 같으면
			parents[bRoot] = aRoot; // b루트를 a루트로 바꾼다
			return true; // true를 리턴한다
		}
	}

	static class Edge { // 엣지 클래스
		int start, end, cost; // 시작정점, 종료정점, 비용

		public Edge(int start, int end, int cost) {
			super();
			this.start = start;
			this.end = end;
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

			PriorityQueue<Edge> pq = new PriorityQueue<Edge>((a, b) -> Integer.compare(a.cost, b.cost)); // 비용으로 정렬하는 pq
			for (int i = 0; i < E; i++) { // E개의 줄에서 간선의 정보를 받는다
				st = new StringTokenizer(br.readLine().trim()); // 간선의 정보를 공백으로 나눈다
				int a = Integer.parseInt(st.nextToken()); // 시작 정점
				int b = Integer.parseInt(st.nextToken()); // 종료 정점
				int cost = Integer.parseInt(st.nextToken()); // 연결하는 비용
				pq.offer(new Edge(a, b, cost)); // a와 b의 간선 큐에 추가
			}

			make(V);
			long result = 0; // 전체 가중치의 합

			while (!pq.isEmpty()) { // 큐가 비어있을 때까지 반복
				Edge cur = pq.poll(); // 현재 노드
				if (union(cur.start, cur.end)) {
					result += cur.cost;
				}
			}
			sb.append("#").append(test_case).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	} // 메인 종료

} // 클래스 종료
