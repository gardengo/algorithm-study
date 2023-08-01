import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static StringBuffer sb = new StringBuffer();
	private static int N;
	private static int M;
	private static char[] answer;
	private static boolean[] isSelected;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		answer = new char[M * 2];
		isSelected = new boolean[N];

		for (int i = 0; i < M; i++)
			answer[i * 2 + 1] = ' ';
		answer[M * 2 - 1] = '\n';

		recursion(0);
		System.out.println(sb);
	}

	private static void recursion(int num) throws Exception {
		if (num == M) {
			sb.append(answer);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (isSelected[i])
				continue;
			answer[num * 2] = (char) ('0' + i + 1);
			isSelected[i] = true;
			recursion(num + 1);
			isSelected[i] = false;
		}
	}

}
