import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main { // 클래스 시작
	static int N;
	static char[][] grid; // 색깔이 있는 그림
	static int[] dx = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dy = { 0, 0, -1, 1 }; // 상하좌우

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader((new InputStreamReader(System.in))); // 입력을 받기 위한 BufferedReader 객체 생성
		N = Integer.parseInt(br.readLine()); // 칸의 크기 N
		grid = new char[N][N]; // N*N 크기의 그리드
		for (int i = 0; i < N; i++) { // N개의 줄에서 입력 받기
			String str = br.readLine(); // 한 줄을 입력받아 string으로 저장
			for (int j = 0; j < N; j++) // N개로 나누어 입력
				grid[i][j] = str.charAt(j); // grid에 문자 저장
		} // 입력 종료

		int normal = 0; // 일반인의 구역의 수
		int color = 0; // 색약인의 구역의 수
		boolean[][] visitedNormal = new boolean[N][N]; // 일반인의 탐색한 영역을 표시하기 위한 배열
		boolean[][] visitedColor = new boolean[N][N]; // 색약인의 탐색한 영역을 표시하기 위한 배열

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visitedNormal[i][j]) { // 방문한 적이 없다면
					dfsNormal(i, j, grid[i][j], visitedNormal); // 탐색한다
					normal++; // 구역의 수 +1
				}
				if (!visitedColor[i][j]) { // 방문한 적이 없다면
					dfsColor(i, j, grid[i][j], visitedColor); // 탐색한다
					color++; // 구역의 수 +1
				}
			}
		}
		System.out.println(normal + " " + color);
	} // 메인 종료

	static void dfsNormal(int x, int y, char ch, boolean[][] visited) {
		if (grid[x][y] != ch) // 다른 문자를 만나면
			return; // 리턴

		visited[x][y] = true; // 방문처리
		for (int i = 0; i < 4; i++) { // 상하좌우 4방향 탐색
			if (x + dx[i] >= 0 && x + dx[i] < N && y + dy[i] >= 0 && y + dy[i] < N) { // 이동 가능하다면
				if (!visited[x + dx[i]][y + dy[i]]) { // 방문한 적 없다면
					dfsNormal(x + dx[i], y + dy[i], ch, visited); // 탐색한다
				}
			}
		}
	}

	static void dfsColor(int x, int y, char ch, boolean[][] visited) {
		if (ch == 'B') { // 문자가 B일때
			if (grid[x][y] != 'B') { // 다른 문자를 만나면
				return; // 리턴
			}
		} else { // 문자가 B가 아닐때
			if (grid[x][y] == 'B') { // B를 만나면
				return; // 리턴
			}
		}

		visited[x][y] = true; // 방문처리
		for (int i = 0; i < 4; i++) { // 상하좌우 4방향 탐색
			if (x + dx[i] >= 0 && x + dx[i] < N && y + dy[i] >= 0 && y + dy[i] < N) { // 이동 가능하다면
				if (!visited[x + dx[i]][y + dy[i]]) { // 방문한 적 없다면
					dfsColor(x + dx[i], y + dy[i], ch, visited); // 탐색한다
				}
			}
		}
	}
} // 클래스 종료
