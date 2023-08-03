import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int[] S, B;
	private static int min = Integer.MAX_VALUE;
	private static int dif = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		S = new int[N];
		B = new int[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
		}

		cooking(0, 1, 0);
		System.out.println(min);
	}

	private static void cooking(int num, int s, int b) {
		if (b != 0) {
			dif = Math.abs(s - b);
			min = Math.min(min, dif);
		}
		if (num == N)
			return;
		cooking(num + 1, s, b);
		cooking(num + 1, s * S[num], b + B[num]);
	}

}
