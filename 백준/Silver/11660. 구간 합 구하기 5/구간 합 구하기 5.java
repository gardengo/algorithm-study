import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int[][] NBoard;
	private static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 수의 개수 N
		int M = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수 M

		NBoard = new int[N + 1][N + 1]; // x,y가 0일때 합도 0을 저장
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				NBoard[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i <= N; i++) { // y축으로 더하기
			for (int j = 1; j <= N; j++) {
				NBoard[i][j] += NBoard[i - 1][j];
			}
		}

		for (int i = 1; i <= N; i++) { // x축으로 더하기
			for (int j = 1; j <= N; j++) {
				NBoard[i][j] += NBoard[i][j - 1];
			}
		}

		for (int k = 0; k < M; k++) {
			st = new StringTokenizer(br.readLine()); // x,y 정수 입력
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			// x2,y2까지의 합 - x축 - y축 + 겹치는 부분
			int answer = NBoard[x2][y2] - NBoard[x2][y1 - 1] - NBoard[x1 - 1][y2] + NBoard[x1 - 1][y1 - 1];
			sb.append(answer + "\n");
		}

		System.out.println(sb);
	}

}
