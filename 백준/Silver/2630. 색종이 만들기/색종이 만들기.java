import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int white, blue;
	static char[][] paper;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		paper = new char[N + 1][N + 1];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				paper[i][j] = st.nextToken().charAt(0);
		}

		make(0, 0, N, N);
		System.out.println(white);
		System.out.println(blue);
	}

	private static void make(int sx, int sy, int ex, int ey) {
		int num = paper[sx][sy];

		for (int x = sx; x < ex; x++) {
			for (int y = sy; y < ey; y++) {
				if (paper[x][y] != num) {
					make(sx, sy, ex / 2 + sx / 2, ey / 2 + sy / 2); // 1사분면
					make(sx, ey / 2 + sy / 2, ex / 2 + sx / 2, ey); // 2사분면
					make(ex / 2 + sx / 2, sy, ex, ey / 2 + sy / 2); // 3사분면
					make(ex / 2 + sx / 2, ey / 2 + sy / 2, ex, ey); // 4사분면
					return;
				}
			}
		}

		if (num == '0')
			white++;
		else
			blue++;
	}
}
