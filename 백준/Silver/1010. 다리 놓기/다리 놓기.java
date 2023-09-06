import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 클래스 시작
	static int[][] dp;

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수 T

		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 강의 서쪽 사이트
			int M = Integer.parseInt(st.nextToken()); // 강의 동쪽 사이트
			dp = new int[M + 1][N + 1];
			comb(M, N); // 두 사이트의 조합이 다리를 지을 수 있는 경우의 수
			sb.append(dp[M][N]).append("\n");
		}
		System.out.println(sb);
	} // 메인 종료

	private static void comb(int m, int n) { // nCm이 을 구하는 메소드
		for (int i = 1; i <= m; i++) {
			for (int j = 0; j <= n; j++) {
				if (i == j || j == 0)
					dp[i][j] = 1;
				else
					dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
			}
		}
	} // 메소드 종료
} // 클래스 종료
