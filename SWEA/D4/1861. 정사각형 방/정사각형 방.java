import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int[] dx = { -1, 0, 0, 1 };
	private static int[] dy = { 0, -1, 1, 0 };
	private static int[][] room;
	private static int N, max, answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 T

		for (int test_case = 1; test_case <= T; test_case++) { // 테스트 케이스 만큼 반복
			N = Integer.parseInt(br.readLine()); // 방의 크기 N
			room = new int[N][N]; // N*N 크기의 방
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++)
					room[i][j] = Integer.parseInt(st.nextToken()); // 방에 적힌 숫자
			}
			max = 0;
			answer = 0;

			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					search(i, j, 0);
			sb.append("#").append(test_case).append(" ").append(answer).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}

	private static void search(int x, int y, int num) {
		for (int i = 0; i < 4; i++) {
			if (x + dx[i] >= 0 && x + dx[i] < N && y + dy[i] >= 0 && y + dy[i] < N) {
				if (room[x + dx[i]][y + dy[i]] - room[x][y] == 1)
					search(x + dx[i], y + dy[i], num + 1);
			}
			if (max < num + 1) {
				max = num + 1;
				answer = room[x][y] - num;
			}
			if (max == num + 1) {
				answer = answer > room[x][y] - num ? room[x][y] - num : answer;
			}
		}
	}

}
