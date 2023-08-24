import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { // 클래스 시작
	static int N, answer;
	static int[] area;
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader((new InputStreamReader(System.in))); // 입력을 받기 위한 BufferedReader 객체 생성
		N = Integer.parseInt(br.readLine()); // 구역의 개수 N
		area = new int[N + 1]; // 구역 별 인구 수
		StringTokenizer st = new StringTokenizer(br.readLine()); // 구역의 인구 수를 한 줄로 받는다
		for (int i = 1; i <= N; i++) // N개의 구역으로 나눈다
			area[i] = Integer.parseInt(st.nextToken()); // 구역 별 인구 수를 입력한다

		graph = new ArrayList<ArrayList<Integer>>(); // 연결된 구역을 저장하는 그래프
		for (int i = 0; i <= N; i++) // N까지 초기 설정
			graph.add(new ArrayList<Integer>()); // N개의 구역에 리스트를 추가한다
		for (int i = 1; i <= N; i++) { // N개의 구역에 대하여
			st = new StringTokenizer(br.readLine()); // 인접한 구역의 정보를 한 줄로 받는다
			int num = Integer.parseInt(st.nextToken()); // 인접한 구역의 수
			for (int j = 0; j < num; j++) // 인접한 구역에 대하여
				graph.get(i).add(Integer.parseInt(st.nextToken())); // i구역과 j구역을 연결한다
		} // 입력 종료

		answer = Integer.MAX_VALUE; // MAX_VALUE로 초기값 설정
		ArrayList<Integer> red = new ArrayList<Integer>();
		for (int i = 1; i <= N / 2; i++)
			combination(1, N, i, red);

		if (answer == Integer.MAX_VALUE) // answer가 바뀌지 않았으면 조합에 실패
			System.out.println(-1); // -1을 출력한다
		else // 2개 구역으로 나눌 수 있는 경우
			System.out.println(answer); // 정답 출력
	} // 메인 종료

	// 지역을 r개만큼 조합해 주는 메소드
	private static void combination(int start, int n, int r, ArrayList<Integer> red) {
		if (r == 0) { // 조합이 완료되면
			gerrymandering(red); // 게리멘더링 시작
			return;
		}
		for (int i = start; i <= n; i++) {
			red.add(i);
			combination(i + 1, n, r - 1, red);
			red.remove(red.size() - 1);
		}
	}

	// 조합한 지역의 인구수 차이를 계산하는 메소드
	private static void gerrymandering(ArrayList<Integer> red) {
		if (!isConnected(red.get(0), red, red.size()))
			return;

		ArrayList<Integer> blue = new ArrayList<Integer>();
		for (int i = 1; i <= N; i++) {
			if (red.contains(i))
				continue;
			blue.add(i);
		}
		if (!isConnected(blue.get(0), blue, blue.size()))
			return;

		int resultRed = 0;
		for (int i = 0; i < red.size(); i++)
			resultRed += area[red.get(i)];

		int resultBlue = 0;
		for (int i = 0; i < blue.size(); i++)
			resultBlue += area[blue.get(i)];

		int result = Math.abs(resultRed - resultBlue);
		answer = result < answer ? result : answer;
	}

	// 조합한 구역이 연결되어 있는지 확인하는 메소드
	private static boolean isConnected(int root, ArrayList<Integer> list, int size) {
		boolean[] visited = new boolean[N + 1];
		visited[root] = true;
		Queue<Integer> que = new ArrayDeque<Integer>();
		que.offer(root);

		int count = 1;
		while (!que.isEmpty()) {
			int cur = que.poll();

			for (int i : graph.get(cur)) {
				if (!visited[i] && list.contains(i)) {
					visited[i] = true;
					count++;
					que.offer(i);
				}
			}
		}

		if (count == size)
			return true;
		else
			return false;
	}
} // 클래스 종료
