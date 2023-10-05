import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, W, H, answer;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int test = 1; test <= T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			int[][] block = new int[H][W];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++)
					block[i][j] = Integer.parseInt(st.nextToken());
			}

			answer = Integer.MAX_VALUE;
			game(0, block);
			if (answer == Integer.MAX_VALUE)
				answer = 0;
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	public static void game(int depth, int[][] origin) {
		if (depth == N) {
			int sum = 0;
			for (int i = 0; i < H; i++)
				for (int j = 0; j < W; j++)
					if (origin[i][j] != 0)
						sum++;
			answer = sum < answer ? sum : answer;
			return;
		}

		int[][] copy = new int[H][W];
		for (int i = 0; i < H; i++)
			for (int j = 0; j < W; j++)
				copy[i][j] = origin[i][j];

		for (int j = 0; j < W; j++) {
			for (int i = 0; i < H; i++) {
				if (copy[i][j] == 0) {
					continue;
				} else if (copy[i][j] == 1) {
					copy[i][j] = 0;
					game(depth + 1, copy);
					copy[i][j] = 1;
					break;
				} else {
					dfs(i, j, copy[i][j], copy);
					// 0으로 바뀐 지점들 정렬 필요
					sortBlock(copy);
					game(depth + 1, copy);

					for (int h = 0; h < H; h++)
						for (int w = 0; w < W; w++)
							copy[h][w] = origin[h][w];
					break;
				}
			}
		}
	}

	public static void dfs(int h, int w, int num, int[][] copy) {
		copy[h][w] = 0;
		for (int d = 0; d < 4; d++) {
			for (int i = 1; i < num; i++) {
				if (h + dx[d] * i < 0 || h + dx[d] * i >= H || w + dy[d] * i < 0 || w + dy[d] * i >= W)
					break;
				if (copy[h + dx[d] * i][w + dy[d] * i] == 0) {
					continue;
				} else if (copy[h + dx[d] * i][w + dy[d] * i] == 1) {
					copy[h + dx[d] * i][w + dy[d] * i] = 0;
				} else {
					dfs(h + dx[d] * i, w + dy[d] * i, copy[h + dx[d] * i][w + dy[d] * i], copy);
				}
			}
		}
	}

	public static void sortBlock(int[][] copy) {
		for (int j = 0; j < W; j++) {
			for (int i = H - 1; i >= 0; i--) {
				if (copy[i][j] == 0)
					continue;
				int idx = 1;
				while (i + idx < H && copy[i + idx][j] == 0) {
					idx++;
				}
				if (idx != 1) {
					copy[i + idx - 1][j] = copy[i][j];
					copy[i][j] = 0;
				}
			}
		}
	}

}
