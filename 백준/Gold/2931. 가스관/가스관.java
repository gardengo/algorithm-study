import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리 11536kb 시간 76ms
public class Main { // 클래스 시작
	static int[] dx = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dy = { 0, 0, -1, 1 }; // 상하좌우

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위한 BufferedReader 객체 생성
		StringBuilder sb = new StringBuilder(); // 출력을 위한 StringBuilder 객체 생성

		StringTokenizer st = new StringTokenizer(br.readLine().trim()); // 한 줄을 입력받아 공백으로 나누기
		int R = Integer.parseInt(st.nextToken()); // 도면의 행크기 R
		int C = Integer.parseInt(st.nextToken()); // 도면의 열크기 C
		char[][] map = new char[R][C]; // 도면정보
		int[] posM = new int[2]; // 출발 위치
		for (int i = 0; i < R; i++) { // R개의 줄에서 입력을 받는다
			String str = br.readLine(); // 한 줄을 입력받아 string으로 저장
			for (int j = 0; j < C; j++) { // C개로 나누어서
				map[i][j] = str.charAt(j); // map에 저장한다
				if (map[i][j] == 'M') { // 입력이 M이면
					posM[0] = i; // M의 위치를 저장한다
					posM[1] = j; // M의 위치를 저장한다
				} // if문 종료
			} // 한 줄 종료
		} // M은 출발지점

		boolean[][] visited = new boolean[R][C];
		int[] answer = search(R, C, map, posM, visited);
		sb.append(answer[0]).append(" ").append(answer[1]).append(" ").append((char) answer[2]);
		System.out.println(sb);
	} // 메인 종료

	static int[] search(int R, int C, char[][] map, int[] posM, boolean[][] visited) { // 정답을 찾는 메소드
		int nx = posM[0]; // 현재 위치, 출발 위치에서 시작
		int ny = posM[1]; // 현재 위치, 출발 위치에서 시작
		char block = ' '; // 다음 위치의 블록
		int ndx = 0; // 현재의 이동 방향
		int ndy = 0; // 현재의 이동 방향
		visited[nx][ny] = true;

		for (int i = 0; i < 4; i++) { // 출발 위치에서 4방향 탐색
			if (0 <= nx + dx[i] && nx + dx[i] < R && 0 <= ny + dy[i] && ny + dy[i] < C) { // 이동가능하다면
				if (map[nx + dx[i]][ny + dy[i]] == 'Z')
					continue;
				if (map[nx + dx[i]][ny + dy[i]] == '.') // 다음 위치가 빈칸인 경우
					continue;
				if (dx[i] == -1) {
					if (map[nx + dx[i]][ny + dy[i]] == '-' || map[nx + dx[i]][ny + dy[i]] == '2'
							|| map[nx + dx[i]][ny + dy[i]] == '3')
						continue;
				} else if (dx[i] == 1) {
					if (map[nx + dx[i]][ny + dy[i]] == '-' || map[nx + dx[i]][ny + dy[i]] == '1'
							|| map[nx + dx[i]][ny + dy[i]] == '4')
						continue;
				} else if (dy[i] == -1) {
					if (map[nx + dx[i]][ny + dy[i]] == '|' || map[nx + dx[i]][ny + dy[i]] == '3'
							|| map[nx + dx[i]][ny + dy[i]] == '4')
						continue;
				} else if (dy[i] == 1) {
					if (map[nx + dx[i]][ny + dy[i]] == '|' || map[nx + dx[i]][ny + dy[i]] == '1'
							|| map[nx + dx[i]][ny + dy[i]] == '2')
						continue;
				}
				block = map[nx + dx[i]][ny + dy[i]]; // 블록을 저장한다
				nx = nx + dx[i]; // 다음 위치로 이동한다
				ny = ny + dy[i]; // 다음 위치로 이동한다
				ndx = dx[i]; // 이동 방향 저장
				ndy = dy[i]; // 이동 방향 저장
				break; // for문 종료
			}
		} // 4방향 탐색 종료

		while (block != '.') { // .을 만날 때까지 이동
			if (block == '|' || block == '-') { // 직선 블록을 만났을 때
				visited[nx][ny] = true;

			} else if (block == '1') { // 1번 블록을 만났을 때
				visited[nx][ny] = true;
				if (ndy == -1) { // 오른쪽에서 진입했을 때
					ndx = 1; // 방향이 아래로 바뀐다
					ndy = 0; // 방향이 아래로 바뀐다
				} else if (ndx == -1) { // 밑에서 진입했을 때
					ndx = 0; // 방향이 오른쪽으로 바뀐다
					ndy = 1; // 방향이 오른쪽으로 바뀐다
				}

			} else if (block == '2') { // 2번 블록을 만났을 때
				visited[nx][ny] = true;
				if (ndy == -1) { // 오른쪽에서 진입했을 때
					ndx = -1; // 방향이 위로 바뀐다
					ndy = 0; // 방향이 위로 바뀐다
				} else if (ndx == 1) { // 위에서 진입했을 때
					ndx = 0; // 방향이 오른쪽으로 바뀐다
					ndy = 1; // 방향이 오른쪽으로 바뀐다
				}

			} else if (block == '3') { // 3번 블록을 만났을 때
				visited[nx][ny] = true;
				if (ndy == 1) { // 왼쪽에서 진입했을 때
					ndx = -1; // 방향이 위로 바뀐다
					ndy = 0; // 방향이 위로 바뀐다
				} else if (ndx == 1) { // 위에서 진입했을 때
					ndx = 0; // 방향이 왼쪽으로 바뀐다
					ndy = -1; // 방향이 왼쪽으로 바뀐다
				}

			} else if (block == '4') { // 4번 블록을 만났을 때
				visited[nx][ny] = true;
				if (ndy == 1) { // 왼쪽에서 진입했을 때
					ndx = 1; // 방향이 아래로 바뀐다
					ndy = 0; // 방향이 아래로 바뀐다
				} else if (ndx == -1) { // 밑에서 진입했을 때
					ndx = 0; // 방향이 왼쪽으로 바뀐다
					ndy = -1; // 방향이 왼쪽으로 바뀐다
				}
			} // if문 종료

			block = map[nx + ndx][ny + ndy]; // 다음 블록을 저장하고
			nx = nx + ndx; // 현재 이동방향으로 이동한다
			ny = ny + ndy; // 현재 이동방향으로 이동한다
		} // while문을 빠져나오면 .위치에 도착

		if (0 < nx && nx < R - 1 && 0 < ny && ny < C - 1)
			if ((map[nx - 1][ny] == '|' || map[nx - 1][ny] == '+' || map[nx - 1][ny] == '1' || map[nx - 1][ny] == '4')
					&& (map[nx + 1][ny] == '|' || map[nx + 1][ny] == '+' || map[nx + 1][ny] == '2'
							|| map[nx + 1][ny] == '3')
					&& (map[nx][ny - 1] == '-' || map[nx][ny - 1] == '+' || map[nx][ny - 1] == '1'
							|| map[nx][ny - 1] == '2')
					&& (map[nx][ny + 1] == '-' || map[nx][ny + 1] == '+' || map[nx][ny + 1] == '3'
							|| map[nx][ny + 1] == '4')) // 4방향으로 다 연결되어 있으면
				return new int[] { nx + 1, ny + 1, '+' }; // +를 반환한다

		for (int i = 0; i < 4; i++) { // 4방향 탐색
			if (0 <= nx + dx[i] && nx + dx[i] < R && 0 <= ny + dy[i] && ny + dy[i] < C
					&& !visited[nx + dx[i]][ny + dy[i]]) { // 이동가능하고 방문한적 없다면
				if (map[nx + dx[i]][ny + dy[i]] == '.' || map[nx + dx[i]][ny + dy[i]] == 'Z') // 빈칸인 경우 지나간다
					continue;

				if (ndx == -1) { // 이전 방향이 위쪽이고
					if (dx[i] == -1) { // 현재 방향이 위쪽이면
						if (map[nx - 1][ny] == '|' || map[nx - 1][ny] == '+' || map[nx - 1][ny] == '1'
								|| map[nx - 1][ny] == '4') {
							block = '|'; // 지워진 블럭은 |이다
							break;
						}
					} else if (dy[i] == 1) {
						if (map[nx][ny + 1] == '-' || map[nx][ny + 1] == '+' || map[nx][ny + 1] == '3'
								|| map[nx][ny + 1] == '4') {
							block = '1';
							break;
						}
					} else if (dy[i] == -1) {
						if (map[nx][ny - 1] == '-' || map[nx][ny - 1] == '+' || map[nx][ny - 1] == '1'
								|| map[nx][ny - 1] == '2') {
							block = '4';
							break;
						}
					}
				} else if (ndx == 1) { // 이전 방향이 아래쪽이고
					if (dx[i] == 1) {
						if (map[nx + 1][ny] == '|' || map[nx + 1][ny] == '+' || map[nx + 1][ny] == '2'
								|| map[nx + 1][ny] == '3') {
							block = '|';
							break;
						}
					} else if (dy[i] == 1) {
						if (map[nx][ny + 1] == '-' || map[nx][ny + 1] == '+' || map[nx][ny + 1] == '3'
								|| map[nx][ny + 1] == '4') {
							block = '2';
							break;
						}
					} else if (dy[i] == -1) {
						if (map[nx][ny - 1] == '-' || map[nx][ny - 1] == '+' || map[nx][ny - 1] == '1'
								|| map[nx][ny - 1] == '2') {
							block = '3';
							break;
						}
					}
				} else if (ndy == -1) { // 이전 방향이 왼쪽이고
					if (dy[i] == -1) {
						if (map[nx][ny - 1] == '-' || map[nx][ny - 1] == '+' || map[nx][ny - 1] == '1'
								|| map[nx][ny - 1] == '2') {
							block = '-';
							break;
						}
					} else if (dx[i] == -1) {
						if (map[nx - 1][ny] == '|' || map[nx - 1][ny] == '+' || map[nx - 1][ny] == '1'
								|| map[nx - 1][ny] == '4') {
							block = '2';
							break;
						}
					} else if (dx[i] == 1) {
						if (map[nx + 1][ny] == '|' || map[nx + 1][ny] == '+' || map[nx + 1][ny] == '2'
								|| map[nx + 1][ny] == '3') {
							block = '1';
							break;
						}
					}
				} else if (ndy == 1) { // 이전 방향이 오른쪽이고
					if (dy[i] == 1) {
						if (map[nx][ny + 1] == '-' || map[nx][ny + 1] == '+' || map[nx][ny + 1] == '3'
								|| map[nx][ny + 1] == '4') {
							block = '-';
							break;
						}
					} else if (dx[i] == -1) {
						if (map[nx - 1][ny] == '|' || map[nx - 1][ny] == '+' || map[nx - 1][ny] == '1'
								|| map[nx - 1][ny] == '4') {
							block = '3';
							break;
						}
					} else if (dx[i] == 1) {
						if (map[nx + 1][ny] == '|' || map[nx + 1][ny] == '+' || map[nx + 1][ny] == '2'
								|| map[nx + 1][ny] == '3') {
							block = '4';
							break;
						}
					}
				}
			}
		} // 탐색 종료
		return new int[] { nx + 1, ny + 1, block };
	} // 메소드 종료

} // 클래스 종료