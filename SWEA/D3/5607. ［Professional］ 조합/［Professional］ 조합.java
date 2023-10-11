import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int test = 1; test <= T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			sb.append("#").append(test).append(" ").append(combination(N, R)).append("\n");
		}
		System.out.println(sb);
	}

	public static long combination(int n, int r) {
		long mole = factorial(n) % 1234567891;
		long deno = pow(factorial(r) * factorial(n - r) % 1234567891, 1234567891 - 2);
		return mole * deno % 1234567891;
	}

	public static long pow(long data, int exp) {
		if (exp == 1)
			return data % 1234567891;

		long result = pow(data, exp / 2);
		if (exp % 2 == 1) {
			return (result * result % 1234567891) * data % 1234567891;
		}
		return result * result % 1234567891;
	}

	public static long factorial(long n) {
		long result = 1L;
		while (n > 1) {
			result = (result * n) % 1234567891;
			n--;
		}
		return result;
	}
}
