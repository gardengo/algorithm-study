import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 T 입력

		for (int test_case = 1; test_case <= T; test_case++) { // 테스트 케이스 만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine()); // N,M 입력
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int[][] board = new int[N][N]; // N*N 배열 입력
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int max = 0;
			for (int i = 0; i <= N - M; i++) {
				for (int j = 0; j <= N - M; j++) {
					int sum = 0;
					for (int x = i; x < i + M; x++) {
						for (int y = j; y < j + M; y++)
							sum += board[x][y];
					}
					max = max < sum ? sum : max;
				}
			}

			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			sb.append(max);
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
