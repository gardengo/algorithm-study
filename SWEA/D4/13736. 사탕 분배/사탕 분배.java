import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken()); // 나현이의 사탕 수
			int B = Integer.parseInt(st.nextToken()); // 다현이의 사탕 수
			int K = Integer.parseInt(st.nextToken()); // 반복 수

			// X < (X+Y)/2 < Y < X+Y
			// 2X < X+Y => 2X % (X+Y) = 2X
			// Y - X == 2Y - (X+Y)
			// 따라서 *2 % (X+Y) 하면 결과가 나옴
			int resultA = (int) ((power2(K, (A + B)) * A) % (A + B));
			int resultB = (int) ((power2(K, (A + B)) * B) % (A + B));

			sb.append(Math.min(resultA, resultB));
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static long power2(int x, int mod) {
		if (x == 1)
			return 2;

		long result = power2(x / 2, mod) % mod;
		result = (result * result) % mod;
		if (x % 2 == 0) {
			return result;
		} else {
			return (result * 2) % mod;
		}
	}
}