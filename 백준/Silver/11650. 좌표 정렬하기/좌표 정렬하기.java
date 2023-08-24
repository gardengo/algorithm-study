import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[][] pos = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pos[i][0] = Integer.parseInt(st.nextToken());
			pos[i][1] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(pos, (a, b) -> {
			if (a[0] == b[0])
				return Integer.compare(a[1], b[1]);
			else
				return Integer.compare(a[0], b[0]);
		});

		for (int i = 0; i < N; i++)
			sb.append(pos[i][0]).append(" ").append(pos[i][1]).append("\n");
		System.out.println(sb);
	}

}
