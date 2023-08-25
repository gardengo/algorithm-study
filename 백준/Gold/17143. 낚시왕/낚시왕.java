import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = { 0, -1, 1, 0, 0 }; // 0상하우좌
	static int[] dy = { 0, 0, 0, 1, -1 }; // 0상하우좌
	static Shark[][] map; // 상어를 저장하는 배열
	static int R, C, output; // 낚시 결과

	private static class Shark { // 상어 클래스
		int s, d, z;

		public Shark(int s, int d, int z) {
			this.s = s; // 속력 s
			this.d = d; // 이동방향 d
			this.z = z; // 크기 z
		}
	}

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader((new InputStreamReader(System.in))); // 입력을 받기 위한 BufferedReader 객체 생성
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫 줄을 입력받아 공백으로 나눈다
		R = Integer.parseInt(st.nextToken()); // 격자판의 세로 길이 R
		C = Integer.parseInt(st.nextToken()); // 격자판의 가로 길이 C
		int M = Integer.parseInt(st.nextToken()); // 상어의 수 M

		map = new Shark[R][C]; // 상어를 저장하는 배열
		for (int i = 0; i < M; i++) { // M마리의 상어 정보 입력
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			map[r][c] = new Shark(s, d, z);
		}

		for (int c = 0; c < C; c++) // 낚시왕이 1칸씩 이동하면서
			fishing(c); // 낚시 시작

		System.out.println(output); // 결과 출력
	}

	private static void fishing(int c) { // 낚시하자
		for (int i = 0; i < R; i++) {
			if (map[i][c] != null) {
				Shark near = map[i][c]; // 가장 가까운 상어
				output += near.z;// 상어의 크기를 더한다
				map[i][c] = null; // 상어를 지운다
				break;
			}
		}
		moveShark(); // 상어 이동 ㄱㄱ
	} // 낚시 종료

	private static void moveShark() { // 상어가 움직인다
		Shark[][] copyMap = new Shark[R][C];

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] != null) {
					Shark cur = map[i][j]; // 현재 상어
					int curR = i + dx[cur.d] * cur.s;
					int curC = j + dy[cur.d] * cur.s;

					while (curR < 0 || curR > R - 1) {
						if (curR < 0) { // 0 보다 작아지면
							curR = Math.abs(curR); // 절댓값을 씌워 반대로 이동
							cur.d = 2; // 방향을 바꿔준다
						}
						if (curR > R - 1) { // R-1보다 커지면
							curR = (R - 1) * 2 - curR; // 2R에서 빼서 반대로 이동
							cur.d = 1; // 방향을 바꿔준다
						}
					}
					while (curC < 0 || curC > C - 1) {
						if (curC < 0) { // 0보다 작아지면
							curC = Math.abs(curC); // 절댓값을 씌워 반대로 이동
							cur.d = 3; // 방향을 바꿔준다
						}
						if (curC > C - 1) { // C-1보다 커지면
							curC = (C - 1) * 2 - curC; // 2C에서 빼서 반대로 이동
							cur.d = 4; // 방향을 바꿔준다
						}
					}

					if (copyMap[curR][curC] != null) { // 이동한 자리에 상어가 있다면
						Shark next = copyMap[curR][curC];
						if (next.z < cur.z) // 크기가 큰 경우만
							copyMap[curR][curC] = cur; // 바꾼다
					} else { // 상어가 없다면
						copyMap[curR][curC] = cur; // 복사한 map에 상어를 입력한다
					}
				}
			}
		}
		map = copyMap; // 이동이 끝난 맵으로 바꿔준다
	} // 상어 이동 종료

} // 클래스 종료
