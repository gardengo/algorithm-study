import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int N, M;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int test_case = 1; test_case <= 10; test_case++) { // 테스트 케이스 만큼 반복}
			int t = Integer.parseInt(br.readLine()); // 테스트 케이스 번호 입력
			StringTokenizer st = new StringTokenizer(br.readLine()); // N, M 입력
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			power(N, M);
			System.out.println("#" + t + " " + N);
		}
	}

	private static void power(int n, int m) {
		if (m == 0) {
			N = 1;
		}
		if (m > 1) {
			N = N * n;
			m--;
			power(n, m);
		}
	}

}
