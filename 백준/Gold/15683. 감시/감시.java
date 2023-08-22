import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main { // 클래스 시작
	static int N, M, answer;
	static int[][] map;
	static int[] dx = { -1, 0, 1, 0 }; // 상우하좌
	static int[] dy = { 0, 1, 0, -1 }; // 상우하좌
	static List<int[]> list = new ArrayList<int[]>(); // CCTV 정보를 담는 큐

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader((new InputStreamReader(System.in))); // 입력을 받기 위한 BufferedReader 객체 생성
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫 줄을 입력받아 공백으로 나누기
		N = Integer.parseInt(st.nextToken()); // 세로 크기 N
		M = Integer.parseInt(st.nextToken()); // 가로 크기 M
		map = new int[N + 2][M + 2]; // 사무실 맵
		for (int i = 0; i < N + 2; i++) { // 사무실 끝을 벽으로 채우자
			map[i][0] = 6; // 왼쪽 벽 채우기
			map[i][M + 1] = 6; // 오른쪽 벽 채우기
		}
		for (int i = 0; i < M + 2; i++) {
			map[0][i] = 6; // 위쪽 벽 채우기
			map[N + 1][i] = 6; // 아래쪽 벽 채우기
		} // 벽 채우기 종료
		for (int i = 1; i <= N; i++) { // N줄의 입력을 받아
			st = new StringTokenizer(br.readLine()); // 공백으로 나누고
			for (int j = 1; j <= M; j++) { // M개의 원소로 나눈다
				map[i][j] = Integer.parseInt(st.nextToken()); // 맵에 입력
				if (1 <= map[i][j] && map[i][j] <= 5) // cctv라면 stack에 추가한다
					list.add(new int[] { i, j, map[i][j] }); // x,y좌표, cctv번호
			}
		} // 입력 종료

		answer = 64; // 초기값 설정
		findBlindSpot(0); // 0번 인덱스부터 시작
		System.out.println(answer); // 정답 출력
	} // 메인 종료

	private static void findBlindSpot(int n) { // 사각지대를 찾는 메소드
		if (list.size() == 0) { // cctv가 없는 경우
			int blindspot = 0;
			for (int j = 1; j < N + 1; j++)
				for (int k = 1; k < M + 1; k++)
					if (map[j][k] == 0) // 0인 경우
						blindspot++; // 사각지대 +1

			answer = blindspot < answer ? blindspot : answer; // 정답보다 구한 값이 작으면 변경한다
			return;
		}
		int[] cctv = list.get(n); // 리스트에서 n번째 원소를 뺀다

		int[][] copyMap = new int[N + 2][M + 2]; // 리턴할 때 되돌릴 맵 복사
		for (int i = 0; i < N + 2; i++)
			for (int j = 0; j < M + 2; j++)
				copyMap[i][j] = map[i][j];

		for (int i = 0; i < 4; i++) { // 4방향 탐색
			int nx = cctv[0]; // cctv x좌표
			int ny = cctv[1]; // cctv y좌표
			int num = cctv[2]; // cctv 번호
			int blindspot = 0; // 사각지대 수

			if (num == 1) { // 1번
				while (map[nx + dx[i]][ny + dy[i]] != 6) { // 벽을 만날 때까지
					nx += dx[i];
					ny += dy[i];
					if (map[nx][ny] == 0) // 빈칸을 만나면
						map[nx][ny] = 7; // 7로 채운다
				}
			} else if (num == 2) { // 2번
				if (i == 0) // 상하, 좌우 2경우이므로
					i += 2; // +2를 해서 절반으로 줄인다
				if (i % 2 == 0) { // 상하
					while (map[nx - 1][ny] != 6) { // 위쪽 탐색
						nx -= 1;
						if (map[nx][ny] == 0)
							map[nx][ny] = 7;
					}
					nx = cctv[0]; // 원래 cctv 위치로 돌아오기
					while (map[nx + 1][ny] != 6) { // 아래쪽 탐색
						nx += 1;
						if (map[nx][ny] == 0)
							map[nx][ny] = 7;
					}
				} else { // 좌우
					while (map[nx][ny - 1] != 6) { // 왼쪽 탐색
						ny -= 1;
						if (map[nx][ny] == 0)
							map[nx][ny] = 7;
					}
					ny = cctv[1]; // 원래 cctv 위치로 돌아오기
					while (map[nx][ny + 1] != 6) { // 오른쪽 탐색
						ny += 1;
						if (map[nx][ny] == 0)
							map[nx][ny] = 7;
					}
				}
			} else if (num == 3) { // 3번
				while (map[nx + dx[i]][ny + dy[i]] != 6) { // 해당 방향 탐색
					nx += dx[i];
					ny += dy[i];
					if (map[nx][ny] == 0)
						map[nx][ny] = 7;
				}
				nx = cctv[0]; // 원래 위치로 돌아오기
				ny = cctv[1]; // 원래 위치로 돌아오기
				while (map[nx + dx[(i + 1) % 4]][ny + dy[(i + 1) % 4]] != 6) { // 해당 방향 +1 탐색
					nx += dx[(i + 1) % 4];
					ny += dy[(i + 1) % 4];
					if (map[nx][ny] == 0)
						map[nx][ny] = 7;
				}
			} else if (num == 4) { // 4번
				while (map[nx + dx[i]][ny + dy[i]] != 6) { // 해당 방향 탐색
					nx += dx[i];
					ny += dy[i];
					if (map[nx][ny] == 0)
						map[nx][ny] = 7;
				}
				nx = cctv[0]; // 원래 위치로 돌아오기
				ny = cctv[1]; // 원래 위치로 돌아오기
				while (map[nx + dx[(i + 1) % 4]][ny + dy[(i + 1) % 4]] != 6) { // 해당 방향 +1 탐색
					nx += dx[(i + 1) % 4];
					ny += dy[(i + 1) % 4];
					if (map[nx][ny] == 0)
						map[nx][ny] = 7;
				}
				nx = cctv[0]; // 원래 위치로 돌아오기
				ny = cctv[1]; // 원래 위치로 돌아오기
				while (map[nx + dx[(i + 2) % 4]][ny + dy[(i + 2) % 4]] != 6) { // 해당 방향 +1 탐색
					nx += dx[(i + 2) % 4];
					ny += dy[(i + 2) % 4];
					if (map[nx][ny] == 0)
						map[nx][ny] = 7;
				}
				nx = cctv[0]; // 원래 위치로 돌아오기
				ny = cctv[1]; // 원래 위치로 돌아오기
			} else { // 5번
				if (i == 0) // 한 번만 실행하기 위해서
					i += 4; // +4를 해준다
				while (map[nx - 1][ny] != 6) { // 위쪽을 7로 바꾸기
					nx -= 1;
					if (map[nx][ny] == 0)
						map[nx][ny] = 7;
				}
				nx = cctv[0];
				while (map[nx + 1][ny] != 6) { // 아래쪽을 7로 바꾸기
					nx += 1;
					if (map[nx][ny] == 0)
						map[nx][ny] = 7;
				}
				nx = cctv[0];
				while (map[nx][ny - 1] != 6) { // 왼쪽을 7로 바꾸기
					ny -= 1;
					if (map[nx][ny] == 0)
						map[nx][ny] = 7;
				}
				ny = cctv[1];
				while (map[nx][ny + 1] != 6) { // 오른쪽을 7로 바꾸기
					ny += 1;
					if (map[nx][ny] == 0)
						map[nx][ny] = 7;
				}
			} // 방향 탐색 완료

			if (n < list.size() - 1) // 리스트에 있는 모든 원소를
				findBlindSpot(n + 1); // dfs로 호출

			for (int j = 1; j < N + 1; j++)
				for (int k = 1; k < M + 1; k++)
					if (map[j][k] == 0) // 0인 경우
						blindspot++; // 사각지대 +1

			answer = blindspot < answer ? blindspot : answer; // 정답보다 구한 값이 작으면 변경한다

			for (int x = 0; x < N + 2; x++)
				for (int y = 0; y < M + 2; y++)
					map[x][y] = copyMap[x][y]; // 다음 방향으로 가기 전 map 되돌리기
		} // 한 쪽 방향 종료
	} // 메소드 종료

}
// 클래스 종료
