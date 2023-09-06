import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 클래스 시작
	static int[][] dp; // 최솟값을 저장하는 배열

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위한 BufferedReader 객체 생성
		int N = Integer.parseInt(br.readLine()); // 정수 N 입력
		int[][] cost = new int[N][4]; // 집을 색칠하는데 드는 비용, 2,3번째 비용 차이
		dp = new int[N][3];
		for (int i = 0; i < N; i++) { // N개의 집에 대하여
			StringTokenizer st = new StringTokenizer(br.readLine()); // 색마다 비용을 받는다
			for (int j = 0; j < 3; j++) { // 빨강, 초록, 파랑 순으로 나눠서
				cost[i][j] = Integer.parseInt(st.nextToken()); // 배열에 입력한다
				if (i == 0)
					dp[i][j] = cost[i][j];
				else {
					if (j == 0)
						dp[i][0] = cost[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
					if (j == 1)
						dp[i][1] = cost[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
					if (j == 2)
						dp[i][2] = cost[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
				}
			}
		} // 입력 종료

		int answer = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++)
			answer = dp[N - 1][i] < answer ? dp[N - 1][i] : answer;
		System.out.println(answer);
	} // 메인 종료

} // 클래스 종료
