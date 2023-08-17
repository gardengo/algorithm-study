import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution { // 클래스시작
	static int N, answer;
	static int[] office, house;
	static int[][] customer;
	static boolean[] visited;

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기 위한 BufferedReader 객체 생성
		StringBuilder sb = new StringBuilder(); // 출력을 하기 위한 StringBuilder 객체 생성
		int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스 개수 T 입력

		for (int test_case = 1; test_case <= T; test_case++) { // 테스트 케이스 개수만큼 반복
			N = Integer.parseInt(br.readLine().trim()); // 고객의 수 N 입력
			office = new int[2]; // 회사의 좌표를 담은 배열
			house = new int[2]; // 집의의 좌표를 담은 배열
			customer = new int[N][2]; // 고객의 좌표를 담은 배열
			StringTokenizer st = new StringTokenizer(br.readLine().trim()); // 한 줄을 읽어 공백으로 나눈다
			office[0] = Integer.parseInt(st.nextToken()); // 회사의 x좌표
			office[1] = Integer.parseInt(st.nextToken()); // 회사의 y좌표
			house[0] = Integer.parseInt(st.nextToken()); // 집의 x좌표
			house[1] = Integer.parseInt(st.nextToken()); // 집의 y좌표
			for (int i = 0; i < N; i++) { // N명의 고객 정보 입력
				customer[i][0] = Integer.parseInt(st.nextToken()); // 고객의 x좌표
				customer[i][1] = Integer.parseInt(st.nextToken()); // 고객의 y좌표
			} // 입력 종료

			// 회사에서 출발 -> 고객의 집을 모두 방문하고 -> 집으로 돌아온다
			answer = 2000;
			visited = new boolean[N]; // 방문한 고객의 집을 표시한 배열
			permutation(office[0], office[1], 0, 0);

			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		} // 테스트 케이스 종료
		System.out.println(sb);
	}// 메인 종료

	static void permutation(int nx, int ny, int sum, int depth) {
		if (depth == N) {
			sum += Math.abs(house[0] - nx) + Math.abs(house[1] - ny);
			if (answer > sum)
				answer = sum;
			return;
		}
		for (int i = 0; i < N; i++) {
			if (nx == customer[i][0] && ny == customer[i][1])
				continue;
			if (!visited[i]) {
				sum += Math.abs(customer[i][0] - nx) + Math.abs(customer[i][1] - ny);
				visited[i] = true;
				permutation(customer[i][0], customer[i][1], sum, depth + 1);
				visited[i] = false;
				sum -= Math.abs(customer[i][0] - nx) + Math.abs(customer[i][1] - ny);
			}
		}
	}

} // 클래스 종료
