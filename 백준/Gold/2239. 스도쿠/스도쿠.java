import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int[][] board;
	static boolean[][][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[10][10];
		visited = new boolean[3][10][10]; // 0:행, 1:열, 2:사각형

		for (int i = 1; i <= 9; i++) {
			String str = br.readLine();
			for (int j = 1; j <= 9; j++) {
				board[i][j] = str.charAt(j - 1) - '0';
				if (board[i][j] != 0) {
					visited[0][i][board[i][j]] = true;
					visited[1][board[i][j]][j] = true;
					visited[2][findSquare(i, j)][board[i][j]] = true;
				}
			}
		}

		recursion(1, 1);
	}

	public static void recursion(int r, int c) {
		if (r == 10) {
			StringBuilder sb = new StringBuilder();
			for (int i = 1; i <= 9; i++) {
				for (int j = 1; j <= 9; j++)
					sb.append(board[i][j]);
				sb.append("\n");
			}
			System.out.println(sb);
			System.exit(0);
		}

		if (board[r][c] == 0) {
			for (int i = 1; i <= 9; i++) {
				if (visited[0][r][i])
					continue;
				if (visited[1][i][c])
					continue;
				if (visited[2][findSquare(r, c)][i])
					continue;
				visited[0][r][i] = true;
				visited[1][i][c] = true;
				visited[2][findSquare(r, c)][i] = true;
				board[r][c] = i;
				if (c < 9)
					recursion(r, c + 1);
				else
					recursion(r + 1, 1);
				board[r][c] = 0;
				visited[0][r][i] = false;
				visited[1][i][c] = false;
				visited[2][findSquare(r, c)][i] = false;
			}
		} else {
			if (c < 9)
				recursion(r, c + 1);
			else
				recursion(r + 1, 1);
		}
	}

	public static int findSquare(int i, int j) {
		if (i <= 3) {
			if (j <= 3) {
				return 1;
			} else if (j <= 6) {
				return 2;
			} else {
				return 3;
			}
		} else if (i <= 6) {
			if (j <= 3) {
				return 4;
			} else if (j <= 6) {
				return 5;
			} else {
				return 6;
			}
		} else {
			if (j <= 3) {
				return 7;
			} else if (j <= 6) {
				return 8;
			} else {
				return 9;
			}
		}
	}
}
