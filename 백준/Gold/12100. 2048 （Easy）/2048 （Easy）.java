import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int N, answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] board = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				board[i][j] = Integer.parseInt(st.nextToken());
		}

		answer = 0;
		dfs(board, 0);
		System.out.println(answer);
	}

	static void dfs(int[][] board, int depth) {
		if (depth == 5) {
			int max = 0;
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					max = board[i][j] > max ? board[i][j] : max;
			answer = max > answer ? max : answer;
			return;
		}

		int[][] copyBoard = new int[N][N];

		for (int i = 0; i < N; i++)
			copyBoard[i] = Arrays.copyOf(board[i], N);
		up(copyBoard);
		dfs(copyBoard, depth + 1);

		for (int i = 0; i < N; i++)
			copyBoard[i] = Arrays.copyOf(board[i], N);
		down(copyBoard);
		dfs(copyBoard, depth + 1);

		for (int i = 0; i < N; i++)
			copyBoard[i] = Arrays.copyOf(board[i], N);
		left(copyBoard);
		dfs(copyBoard, depth + 1);

		for (int i = 0; i < N; i++)
			copyBoard[i] = Arrays.copyOf(board[i], N);
		right(copyBoard);
		dfs(copyBoard, depth + 1);
	}

	static void up(int[][] copyBoard) {
		boolean[][] merged = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				if (copyBoard[j][i] != 0) {
					int k = j;
					while (true) {
						if (k - 1 < 0)
							break;
						if (copyBoard[k - 1][i] == 0) {
							copyBoard[k - 1][i] = copyBoard[k][i];
							copyBoard[k][i] = 0;
							k--;
						} else if (copyBoard[k - 1][i] == copyBoard[k][i]) {
							if (merged[k - 1][i])
								break;
							copyBoard[k - 1][i] += copyBoard[k][i];
							copyBoard[k][i] = 0;
							merged[k - 1][i] = true;
							break;
						} else {
							break;
						}
					}
				}
			}
		}
	}

	static void down(int[][] copyBoard) {
		boolean[][] merged = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = N - 2; j >= 0; j--) {
				if (copyBoard[j][i] != 0) {
					int k = j;
					while (true) {
						if (k + 1 > N - 1)
							break;
						if (copyBoard[k + 1][i] == 0) {
							copyBoard[k + 1][i] = copyBoard[k][i];
							copyBoard[k][i] = 0;
							k++;
						} else if (copyBoard[k + 1][i] == copyBoard[k][i]) {
							if (merged[k + 1][i])
								break;
							copyBoard[k + 1][i] += copyBoard[k][i];
							copyBoard[k][i] = 0;
							merged[k + 1][i] = true;
							break;
						} else {
							break;
						}
					}
				}
			}
		}
	}

	static void left(int[][] copyBoard) {
		boolean[][] merged = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < N; j++) {
				if (copyBoard[i][j] != 0) {
					int k = j;
					while (true) {
						if (k - 1 < 0)
							break;
						if (copyBoard[i][k - 1] == 0) {
							copyBoard[i][k - 1] = copyBoard[i][k];
							copyBoard[i][k] = 0;
							k--;
						} else if (copyBoard[i][k - 1] == copyBoard[i][k]) {
							if (merged[i][k - 1])
								break;
							copyBoard[i][k - 1] += copyBoard[i][k];
							copyBoard[i][k] = 0;
							merged[i][k - 1] = true;
							break;
						} else {
							break;
						}
					}
				}
			}
		}
	}

	static void right(int[][] copyBoard) {
		boolean[][] merged = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = N - 2; j >= 0; j--) {
				if (copyBoard[i][j] != 0) {
					int k = j;
					while (true) {
						if (k + 1 > N - 1)
							break;
						if (copyBoard[i][k + 1] == 0) {
							copyBoard[i][k + 1] = copyBoard[i][k];
							copyBoard[i][k] = 0;
							k++;
						} else if (copyBoard[i][k + 1] == copyBoard[i][k]) {
							if (merged[i][k + 1])
								break;
							copyBoard[i][k + 1] += copyBoard[i][k];
							copyBoard[i][k] = 0;
							merged[i][k + 1] = true;
							break;
						} else {
							break;
						}
					}
				}
			}
		}
	}
}
