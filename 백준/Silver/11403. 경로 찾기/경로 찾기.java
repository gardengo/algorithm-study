import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[][] G = new int[N][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				G[i][j] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					G[j][k] = G[j][k] | (G[j][i] & G[i][k]);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				sb.append(G[i][j]).append(" ");
			sb.append("\n");
		}
		
		System.out.println(sb);
	}

}
