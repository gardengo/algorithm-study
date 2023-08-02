import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	private static int[][] Board;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 T 입력

		for (int test_case = 1; test_case <= T; test_case++) { // 테스트 케이스 만큼 반복
			int N = Integer.parseInt(br.readLine()); // 달팽이 크기 N
			Board = new int[N][N];

			sb.append("#" + test_case + "\n");
			snail(N);
			snailPrint();
		}
		System.out.println(sb);
	}

	private static void snail(int n) {
		int num = 1, i = 0, j = 0;
		char status = 'r';
		while (num <= n * n) {
			Board[i][j] = num++;
			if (status == 'u' && !(i - 1 >= 0 && Board[i - 1][j] == 0)) {
				status = 'r';
			}
			if (j + 1 < n && Board[i][j + 1] == 0 && status == 'r') {
				status = 'r';
				j++;
			} else if (i + 1 < n && Board[i + 1][j] == 0 && (status == 'r' || status == 'd')) {
				status = 'd';
				i++;
			} else if (j - 1 >= 0 && Board[i][j - 1] == 0 && (status == 'r' || status == 'd' || status == 'l')) {
				status = 'l';
				j--;
			} else if (i - 1 >= 0 && Board[i - 1][j] == 0) {
				status = 'u';
				i--;
			}
		}
	}

	private static void snailPrint() {
		for (int[] arr : Board) {
			for (int i : arr) {
				sb.append(i + " ");
			}
			sb.append("\n");
		}
	}

}
