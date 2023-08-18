import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { // 클래스 시작
	static StringBuilder sb = new StringBuilder(); // 출력을 위한 StringBuilder 객체 생성
	static int N, M, V;
	static boolean[][] graph;
	static boolean[] visited;

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기 위한 BufferedReader 객체 생성
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫째 줄의 입력을 받아 공백으로 나눈다
		N = Integer.parseInt(st.nextToken()); // 정점의 개수 N
		M = Integer.parseInt(st.nextToken()); // 간선의 개수 M
		V = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점의 번호 V

		// 정점 번호가 작은 것 부터 방문해야 하므로 인접 행렬로 구성
		graph = new boolean[N + 1][N + 1]; // 1부터 사용하기 위해 +1
		for (int i = 0; i < M; i++) { // M개의 줄에서 입력을 받는다
			st = new StringTokenizer(br.readLine()); // 한 줄을 읽어 공백으로 나눈다
			int first = Integer.parseInt(st.nextToken()); // 첫 번째 정점
			int second = Integer.parseInt(st.nextToken()); // 두 번째 정점
			graph[first][second] = true; // 첫 번째 정점의 인덱스에 두 번째 정점을 추가
			graph[second][first] = true; // 두 번째 정점의 인덱스에 첫 번째 정점을 추가
		}

		visited = new boolean[N + 1];
		dfs(V); // dfs 호출
		sb.append("\n"); // 개행
		visited = new boolean[N + 1];
		bfs(V); // bfs 호출
		System.out.println(sb); // 출력
	} // 메인 종료

	static void dfs(int n) {
		sb.append(n).append(" "); // 탐색한 정점을 sb에 추가한다
		visited[n] = true; // 방문처리
		for (int i = 1; i <= N; i++) // n에 연결된 정점을 찾자
			if (graph[n][i] && !visited[i]) // 연결되어있고 방문한적 없다면
				dfs(i); // 탐색한다
	}

	static void bfs(int n) {
		Queue<Integer> que = new ArrayDeque<Integer>(); // bfs를 위한 큐 생성
		que.offer(n); // 큐에 초기값을 넣는다
		visited[n] = true; // 초기값 방문처리

		while (!que.isEmpty()) { // 큐가 비면 끝까지 탐색 완료
			int now = que.poll(); // 현재 정점 위치
			sb.append(now).append(" "); // 현재 정점을 추가한다
			for (int i = 1; i <= N; i++) // 연결된 정점 찾기
				if (graph[now][i] && !visited[i]) { // 연결되어있고 방문한적 없다면
					que.offer(i); // 큐에 추가한다
					visited[i] = true;
				}
		}
	}
} // 클래스 종료
