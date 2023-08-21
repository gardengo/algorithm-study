import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { // 클래스 시작
	static int answer; // 정답

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader((new InputStreamReader(System.in))); // 입력을 받기 위한 BufferedReader 객체 생성
		StringTokenizer st = new StringTokenizer(br.readLine()); // 한 줄을 입력받아 공백으로 나누기
		int N = Integer.parseInt(st.nextToken()); // 수빈이가 있는 위치 N
		int K = Integer.parseInt(st.nextToken()); // 동생이 있는 위치 K

		answer = 100001; // 정답 초기값 설정
		boolean[] visited = new boolean[100001];
		find(N, K, 0, visited); // find 메소드 수행
		System.out.println(answer); // 정답 출력
	} // 메인 종료

	static void find(int N, int K, int cnt, boolean[] visited) { // 정답을 찾는 메소드
		if (N > K) { // N이 K보다 크면 1씩밖에 못 움직이므로
			answer = N - K; // 정답은 N - K
			return;
		}
		Queue<int[]> que = new ArrayDeque<int[]>(); // bfs를 위한 큐 생성
		que.offer(new int[] { K, cnt }); // 초기값을 추가한다
		visited[K] = true;

		while (!que.isEmpty()) { // 큐가 빌때까지
			int[] now = que.poll(); // 큐에서 현재 원소를 뺀다

			if (now[0] % 2 == 1) { // K가 홀수이면
				if (now[0] < 100000) {
					if (!visited[now[0] + 1]) {
						que.offer(new int[] { now[0] + 1, now[1] + 1 });// 1 더한다
						visited[now[0] + 1] = true;
					}
				}
				if (now[0] > 0) {
					if (!visited[now[0] - 1]) {
						que.offer(new int[] { now[0] - 1, now[1] + 1 });// 1 뺀다
						visited[now[0] - 1] = true;
					}
				}
			} else { // K가 짝수이면
				if (!visited[now[0] / 2]) {
					que.offer(new int[] { now[0] / 2, now[1] + 1 });// 반으로 나눈다
					visited[now[0] / 2] = true;
				}
				if (now[0] < 100000) {
					if (!visited[now[0] + 1]) {
						que.offer(new int[] { now[0] + 1, now[1] + 1 });// 1 더한다
						visited[now[0] + 1] = true;
					}
				}
				if (now[0] > 0) {
					if (!visited[now[0] - 1]) {
						que.offer(new int[] { now[0] - 1, now[1] + 1 });// 1 뺀다
						visited[now[0] - 1] = true;
					}
				}
			} // if문 종료

			if (N == now[0]) { // K와 N의 위치가 같아지면
				answer = now[1]; // 정답을 바꾸고
				return; // 종료한다
			} // if문 종료

		} // 반복문 종료
	} // 메소드 종료

} // 클래스 종료
