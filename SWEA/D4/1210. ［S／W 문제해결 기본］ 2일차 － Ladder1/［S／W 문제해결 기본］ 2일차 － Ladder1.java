import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int x, y;
	private static int[][] board;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[100][100];

		for (int test_case = 1; test_case <= 10; test_case++) {
			int n = Integer.parseInt(br.readLine());
			x = -1;
			for (int i = 0; i < 100; i++) {
				String[] str = br.readLine().split(" ");
//				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					board[i][j] = Integer.parseInt(str[j]);
//					board[i][j] = Integer.parseInt(st.nextToken());
					if (i == 99 && board[i][j] == 2) {
						x = j;
						break;
					}
				}
			}

			y = 98;
			while (board[y][x] == 1) {
				if (y == 0)
					break;
				if (x > 0 && board[y][x - 1] == 1) {
					move('l');
				} else if (x < 99 && board[y][x + 1] == 1) {
					move('r');
				} else {
					y -= 1;
				}
			}
			System.out.println("#" + test_case + " " + x);
		}
	}

	private static void move(char ch) {
		if (ch == 'l') {
			x--;
			if (x > 0 && board[y][x - 1] == 1) {
				move('l');
			} else {
				y--;
			}
		} else {
			x++;
			if (x < 99 && board[y][x + 1] == 1) {
				move('r');
			} else {
				y--;
			}
		}
	}

}
