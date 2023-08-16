import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[][] video;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		video = new int[N][N];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++)
				video[i][j] = (int) str.charAt(j) - '0';
		}
		// 입력 종료

		quadTree(0, 0, N, video[0][0]);
		System.out.println(sb);
	}

	static void quadTree(int r, int c, int n, int dot) {
		if (n == 1) {
			sb.append(dot);
			return;
		}

		boolean can = true;
		root: for (int i = r; i < r + n; i++) {
			for (int j = c; j < c + n; j++) {
				if (video[i][j] != dot) { // 다른 점이 나오면 멈춘다
					can = false;
					break root;
				}
			}
		}

		if (!can) { // 중간에서 멈췄다면 4조각으로 나눠 탐색
			sb.append("(");
			quadTree(r, c, n / 2, video[r][c]);
			quadTree(r, c + n / 2, n / 2, video[r][c + n / 2]);
			quadTree(r + n / 2, c, n / 2, video[r + n / 2][c]);
			quadTree(r + n / 2, c + n / 2, n / 2, video[r + n / 2][c + n / 2]);
			sb.append(")");
		} else { // 한 조각이 모두 같은 점을 갖고 있다면
			sb.append(dot);
			return;
		}
	}

}
