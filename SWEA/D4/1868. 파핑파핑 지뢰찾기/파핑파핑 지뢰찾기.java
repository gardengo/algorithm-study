import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static int N;
	static char[][] board;
	static int[][] cntMine;
	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 }; // 좌상,상,우상,좌,우,좌하,하,우하
	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 }; // 좌상,상,우상,좌,우,좌하,하,우하

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스의 수 T

		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine().trim()); // 표의 크기 N
			board = new char[N][N]; // 지뢰찾기 표(N*N)
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					board[i][j] = str.charAt(j);
				}
			} // 입력 종료

			int answer = 0;
			cntMine = new int[N][N]; // 주변 지뢰의 수

			// 주변 지뢰의 수를 찾자
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == '.') {
						int cnt = 0;
						for (int d = 0; d < 8; d++) {
							int nx = i + dx[d];
							int ny = j + dy[d];
							if (nx >= 0 && nx < N && ny >= 0 && ny < N && board[nx][ny] == '*')
								cnt++;
						}
						cntMine[i][j] = cnt;
					}
				}
			}

			// 주변에 지뢰가 없는 곳부터 누른다
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (cntMine[i][j] == 0 && board[i][j] == '.') {
						click(i, j);
						answer++;
					}
				}
			}

			// 아직 안눌린 곳을 누른다
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (board[i][j] == '.')
						answer++;
				}
			}

			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		} // 테스트 케이스 종료
		System.out.println(sb);
	}

	// 해당 좌표를 클릭하는 메소드
	private static void click(int x, int y) {
		board[x][y] = 'v';
		if (cntMine[x][y] == 0) { // 주변에 지뢰가 없다
			for (int d = 0; d < 8; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (nx >= 0 && nx < N && ny >= 0 && ny < N && board[nx][ny] == '.') {
					click(nx, ny);
				}
			}
		}
	}

}
