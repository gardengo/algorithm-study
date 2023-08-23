import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution { // 클래스 시작
	static int parents[];

	private static void make(int n) { // 집합을 만드는 메소드
		parents = new int[n]; // n크기의 집합을 만든다
		for (int i = 0; i < n; i++) // 초기값 설정
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

	private static class Edge { // 간선 클래스
		int start, end;
		double dist;

		public Edge(int start, int end, double dist) {
			this.start = start;
			this.end = end;
			this.dist = dist;
		}
	}

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader((new InputStreamReader(System.in))); // 입력을 받기 위한 BufferedReader 객체 생성
		StringBuilder sb = new StringBuilder(); // 출력을 위한 StringBuilder 객체 생성
		int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스의 수 T

		for (int test_case = 1; test_case <= T; test_case++) { // 테스트 케이스 시작
			int N = Integer.parseInt(br.readLine().trim()); // 섬의 개수 N

			int[][] island = new int[N][2]; // x,y,부모
			StringTokenizer st = new StringTokenizer(br.readLine().trim()); // 첫째 줄을 입력 받아 공백으로 나누기
			for (int i = 0; i < N; i++) // N개의 섬에 입력
				island[i][0] = Integer.parseInt(st.nextToken()); // x좌표
			st = new StringTokenizer(br.readLine().trim()); // 두번째 줄을 입력 받아 공백으로 나누기
			for (int i = 0; i < N; i++) // N개의 섬에 입력
				island[i][1] = Integer.parseInt(st.nextToken()); // y좌표
			double E = Double.parseDouble(br.readLine()); // 환경 부담 세율 E

			PriorityQueue<Edge> pq = new PriorityQueue<Edge>((a, b) -> Double.compare(a.dist, b.dist));
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					double dist = E * Math.pow(island[i][0] - island[j][0], 2)
							+ E * Math.pow(island[i][1] - island[j][1], 2);
					Edge edge = new Edge(i, j, dist);
					pq.offer(edge);
				}
			}

			make(N); // 부모 배열을 만든다

			double[] cost = new double[N]; // 비용 배열
			while (!pq.isEmpty()) {
				Edge cur = pq.poll();
				if (union(cur.start, cur.end))
					cost[cur.start] += cur.dist;
			}

			double costSum = 0;
			for (int i = 0; i < N; i++)
				costSum += cost[i];
			long answer = Math.round(costSum);

			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	} // 메인 종료

}
// 클래스 종료
