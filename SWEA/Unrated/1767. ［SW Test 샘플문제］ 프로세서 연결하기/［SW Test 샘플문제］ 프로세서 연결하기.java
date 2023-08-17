import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int N, connect, length; // 연결된 core의 수, 전선의 길이
	static int[][] maxinos; // 멕시노스 정보를 갖는 2차원 배열
	static List<int[]> list; // core의 좌표를 갖는 리스트
	static int[] dx = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dy = { 0, 0, -1, 1 }; // 상하좌우

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		// 테스트 케이스 시작
		for (int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine().trim());
			maxinos = new int[N][N];
			list = new ArrayList<int[]>();

			// 멕시노스 입력 시작
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					maxinos[i][j] = Integer.parseInt(st.nextToken());
					if (maxinos[i][j] == 1) { // core의 위치
						if (0 < i && i < N - 1 && 0 < j && j < N - 1) // 가장자리가 아니면 큐에 추가
							list.add(new int[] { i, j });
					}
				}
			}

			connect = 1;
			length = 144;
			findLength(0, 0, 0);
			sb.append("#").append(test_case).append(" ").append(length).append("\n");
		}
		System.out.println(sb);
	}

	static void findLength(int index, int con, int len) {
		if (con > connect) {
			connect = con;
			length = len;
		} else if (con == connect && len < length) {
			length = len;
		}

		if (index == list.size()) // 리스트 끝까지 가면 리턴한다
			return;

		int[] core = list.get(index);
		for (int d = 0; d < 4; d++) {
			boolean connected = true;
			int x = core[0];
			int y = core[1];
			while (0 < x && x < N - 1 && 0 < y && y < N - 1) {
				if (maxinos[x + dx[d]][y + dy[d]] != 0) {
					connected = false;
					while (x != core[0] || y != core[1]) {
						maxinos[x][y] = 0;
						x -= dx[d];
						y -= dy[d];
						len--;
					}
					break;
				}
				x += dx[d];
				y += dy[d];
				len++;
				maxinos[x][y] = 2;
			}
			if (connected) { // 연결이 되었다면
				if (list.size() - index - 1 >= connect - con - 1) // 남은 코어가 연결의 차이보다 작을 때만
					findLength(index + 1, con + 1, len); // 다음 core 탐색
				while (x != core[0] || y != core[1]) { // 탐색이 끝나면 맥시노스 원래대로
					maxinos[x][y] = 0;
					x -= dx[d];
					y -= dy[d];
					len--;
				}
			} else { // 연결이 안돼도 탐색
				if (list.size() - index - 1 >= connect - con) // 남은 코어가 연결의 차이보다 작을 때만
					findLength(index + 1, con, len); // 다음 core 탐색
			}

		}

	}
}
