import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 클래스 시작
	static int[][] map;
	static int[][][] dp;

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine()); // 집의 크기 (3 <= N <= 16)
		map = new int[N + 1][N + 1]; // 집의 상태
		dp = new int[N + 1][N + 1][3]; // 각 위치의 파이프 수 (0:가로, 1:세로, 2:대각선)
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		calc(N);
		System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]); // 세 방향의 파이프를 모두 더해 출력
	}

	private static void calc(int n) {
		for (int i = 1; i <= n; i++) {
			for (int j = 2; j <= n; j++) {
				if (map[i][j] == 1) // 이동하려는 위치가 1이면 넘어간다
					continue;
				if (i == 1 && j == 2)
					dp[i][j][0] = 1; // 최초 위치(1,2) 1로 설정
				else {
					dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2]; // 가로 이동
					dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2]; // 세로 이동
					if (map[i - 1][j] == 1 || map[i][j - 1] == 1) // 대각선 이동의 경우 상좌도 확인
						continue;
					dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2]; // 대각선 이동
				}
			}
		}
	}

}
