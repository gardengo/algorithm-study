import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
	static int N;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		BigInteger K = new BigInteger("2");
		K = K.pow(N).subtract(BigInteger.ONE);
		sb.append(K + "\n");
		if (N <= 20)
			hanoi(N, 1, 2, 3);

		System.out.println(sb);
	}

	public static void hanoi(int n, int start, int tmp, int end) {
		if (n == 1) {
			sb.append(start + " " + end + "\n");
			return;
		}

		hanoi(n - 1, start, end, tmp);
		sb.append(start + " " + end + "\n");
		hanoi(n - 1, tmp, start, end);
	}

}
