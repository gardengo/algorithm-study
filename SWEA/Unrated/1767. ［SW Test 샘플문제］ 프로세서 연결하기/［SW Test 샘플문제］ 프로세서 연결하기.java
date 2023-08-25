import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution { // 클래스 시작
	static int N, answer, maxCon;
	static int[][] map;
	static List<int[]> core;
	static int[] dx = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader((new InputStreamReader(System.in))); // 입력을 받기 위한 BufferedReader 객체 생성
		StringBuilder sb = new StringBuilder(); // 출력을 위한 StringBuilder 객체 생성
		int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스의 수 T

		for (int test_case = 1; test_case <= T; test_case++) { // 테스트 케이스 시작
			StringTokenizer st = new StringTokenizer(br.readLine().trim()); // 한 줄을 받아 공백으로 나눈다
			N = Integer.parseInt(st.nextToken()); // 배열의 크기 N
			map = new int[N][N]; // N*N 크기의 맵 생성
			core = new ArrayList<int[]>(); // 코어의 좌표를 갖는 리스트
			for (int i = 0; i < N; i++) { // N줄의 입력
				st = new StringTokenizer(br.readLine().trim()); // 한 줄을 공백으로 나누자
				for (int j = 0; j < N; j++) { // N개의 입력
					map[i][j] = Integer.parseInt(st.nextToken()); // map에 값을 넣는다
					if (i > 0 && i < N - 1 && j > 0 && j < N - 1) { // 가장자리가 아닌 경우
						if (map[i][j] == 1) // 코어인 경우
							core.add(new int[] { i, j }); // 리스트에 추가한다
					}
				}
			} // 입력 종료

			maxCon = 0;
			dfs(0, 0, 0);
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		} // 테스트 케이스 종료
		System.out.println(sb);
	} // 메인 종료

	static void dfs(int n, int len, int con) {
		if (maxCon < con) {
			maxCon = con;
			answer = len;
		} else if (maxCon == con) {
			answer = len < answer ? len : answer;
		}
		if (n == core.size())
			return;

		int[] cur = core.get(n);
		int curX = cur[0];
		int curY = cur[1];

		for (int d = 0; d < 4; d++) { // 4방향에 대하여
			boolean connected = true;
			while (curX + dx[d] >= 0 && curX + dx[d] < N && curY + dy[d] >= 0 && curY + dy[d] < N) {
				int nextX = curX + dx[d];
				int nextY = curY + dy[d];
				if (map[nextX][nextY] == 0) { // 다음 위치가 비어있다면
					map[nextX][nextY] = 2;
					len++;
					curX = nextX;
					curY = nextY;
				} else { // 다음 위치에 core나 전선이 있는 경우
					connected = false;
					break;
				}
			}

			if (!connected) {
				while (curX != cur[0] || curY != cur[1]) {
					map[curX][curY] = 0;
					len--;
					curX -= dx[d];
					curY -= dy[d];
				}
				if (con + core.size() - n - 1 >= maxCon)
					dfs(n + 1, len, con);
			} else {
				if (con + core.size() - n >= maxCon)
					dfs(n + 1, len, con + 1);
				while (curX != cur[0] || curY != cur[1]) { // 다음 방향 탐색 전 원래대로
					map[curX][curY] = 0;
					len--;
					curX -= dx[d];
					curY -= dy[d];
				}
			}
		}
	}
} // 클래스 종료
