import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int N, max;
	private static int[][] board;
	private static int[] dx = { -1, 1, 0, 0 }; // 상,하,좌,우
	private static int[] dy = { 0, 0, -1, 1 };
	public static int[][][] warmhole; // 웜홀담을 배열. 1번째 인덱스 웜홀 번호, 2번째 인덱스 웜홀 쌍, 3번째 인덱스 x,y좌표

	public static void main(String[] args) throws IOException { // 메인 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		// 테스트 케이스 시작
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine().trim());
			board = new int[N + 2][N + 2]; // 맵 바깥 벽에 부딪히는 것을 5로 채워주기 위해 +2로 생성
			warmhole = new int[11][2][2];

			for (int i = 0; i < N + 2; i++) {
				board[i][0] = 5; // 왼쪽 벽 5로 채움
				board[i][N + 1] = 5; // 오른쪽 벽 5로 채움
				board[0][i] = 5; // 위쪽 벽 5로 채움
				board[N + 1][i] = 5; // 아래쪽 벽 5로 채움
			}

			// board 입력 시작
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				for (int j = 1; j <= N; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					if (6 <= tmp && tmp <= 10) { // 웜홀 입력
						int warmIdx = 0; // 웜홀 쌍 중 첫번째인지 두번째인지
						if (warmhole[tmp][warmIdx][0] != 0) // 해당 웜홀번호 입력받은적있으면, 1번재 쌍에 저장.
							warmIdx++;
						warmhole[tmp][warmIdx][0] = i;
						warmhole[tmp][warmIdx][1] = j;
					}
					board[i][j] = tmp;
				}
			}
			// board 입력 종료

			max = 0;
			// 전체 좌표를 돌면서 게임을 한다. 1~N까지 사용
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					if (board[i][j] != 0) // 빈 공간일 때만 게임 시작
						continue;
					game(i, j);
				}
			}
			sb.append("#").append(test_case).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}

	private static void game(int x, int y) {
		int point, dir, tmp, nx, ny;

		// 각 좌표에서 4방향으로 게임을 진행한다
		for (int d = 0; d < 4; d++) {
			dir = d; // dir - 0:상, 1:하, 2:좌, 3:우
			point = 0; // 점수 초기화
			nx = x + dx[dir];
			ny = y + dy[dir];

			// 탐색 시작
			while (true) {
				tmp = board[nx][ny];
				// 블랙홀을 만나거나 처음 위치로 돌아오면 끝난다
				if (tmp == -1 || (nx == x && ny == y)) {
					break;
				}
				// 맵 끝에 닿거나 5번 블럭에 닿으면 끝난다
				else if (tmp == 5) {
					point = point * 2 + 1; // 벽에 박으면 지금까지 왔던 길을 반복해서 돌아가므로 *2+1
					break;
				}
				// 웜홀을 만나면 반대쪽 웜홀로 이동한다
				else if (tmp > 5) {
					int warmIdx = 0;
					if (warmhole[tmp][warmIdx][0] == nx && warmhole[tmp][warmIdx][1] == ny) // 현재 웜홀말고 페어 웜홀 좌표찾기.
						warmIdx++;
					nx = warmhole[tmp][warmIdx][0]; // 페어 웜홀 좌표로 x갱신
					ny = warmhole[tmp][warmIdx][1]; // 페어 웜홀 좌표로 y갱신
				} else if (tmp == 1) {// 1번블록
					point++;
					if (dir == 1)
						dir = 3;
					else if (dir == 2)
						dir = 0;
					else if (dir == 3)
						dir = 2;
					else if (dir == 0)
						dir = 1;
				} else if (tmp == 2) {// 2번블록
					point++;
					if (dir == 0)
						dir = 3;
					else if (dir == 2)
						dir = 1;
					else if (dir == 1)
						dir = 0;
					else if (dir == 3)
						dir = 2;
				} else if (tmp == 3) { // 3번블록
					point++;
					if (dir == 3)
						dir = 1;
					else if (dir == 0)
						dir = 2;
					else if (dir == 1)
						dir = 0;
					else if (dir == 2)
						dir = 3;
				} else if (tmp == 4) { // 4번블록
					point++;
					if (dir == 3)
						dir = 0;
					else if (dir == 1)
						dir = 2;
					else if (dir == 0)
						dir = 1;
					else if (dir == 2)
						dir = 3;
				}
				// 다음으로 이동
				nx += dx[dir];
				ny += dy[dir];
			}
			if (max < point) // 포인트가 max보다 크면 max를 갱신한다
				max = point;
		}
	}
}
