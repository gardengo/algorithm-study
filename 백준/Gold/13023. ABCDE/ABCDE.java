import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main { // 클래스 시작
	static int N, M;
	static boolean flag;
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(); // 인접리스트 그래프 생성

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader((new InputStreamReader(System.in))); // 입력을 받기 위한 BufferedReader 객체 생성
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫 줄을 입력받아 공백으로 나누기
		N = Integer.parseInt(st.nextToken()); // 사람의 수 N
		M = Integer.parseInt(st.nextToken()); // 친구 관계의 수 M

		for (int i = 0; i < N; i++) // 0부터 N-1까지
			graph.add(new ArrayList<Integer>()); // 리스트를 추가한다
		for (int i = 0; i < M; i++) { // M개의 줄에서
			st = new StringTokenizer(br.readLine()); // 입력을 받아 공백으로 나눈다
			int front = Integer.parseInt(st.nextToken()); // 앞사람
			int back = Integer.parseInt(st.nextToken()); // 뒷사람
			graph.get(front).add(back); // 앞사람과 뒷사람이 친구관계
			graph.get(back).add(front); // 앞사람과 뒷사람이 친구관계
		} // 입력 종료

		boolean[] visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			visited[i] = true;
			findFriends(i, 0, visited);
			visited[i] = false;
		}
		if (flag)
			System.out.println(1);
		else
			System.out.println(0);
	} // 메인 종료

	private static void findFriends(int n, int depth, boolean[] visited) {
		if (flag == true)
			return;
		if (depth == 4) {
			flag = true;
			return;
		}

		for (int i = 0; i < graph.get(n).size(); i++) {
			if (!visited[graph.get(n).get(i)]) {
				visited[graph.get(n).get(i)] = true;
				findFriends(graph.get(n).get(i), depth + 1, visited);
				visited[graph.get(n).get(i)] = false;
			}
		}
	}

} // 클래스 종료
