import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] Board = new char[N][M];
		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				Board[i][j] = str.charAt(j);
			}
		}

		// 출력
		int answer = 32;
		for (int i = 0; i <= N - 8; i++) {
			for (int j = 0; j <= M - 8; j++) {
				int repaint = 0;
				for (int k = i + 0; k < i + 8; k++) {
					for (int l = j + 0; l < j + 8; l++) {
						// 첫칸이 흰색이라고 가정
						if (Board[k][l] != ((k + l) % 2 == 0 ? 'W' : 'B')) {
							repaint++;
						}
					}
				}
				repaint = Math.min(repaint, 64 - repaint);
				answer = Math.min(repaint, answer);
			}
		}
		System.out.println(answer);
	}
}
