import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	private static int N;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		interesting(0, 0);
		System.out.println(sb);
	}

	private static void interesting(int num, int x) {
		if (num == N) {
			sb.append(x);
			sb.append("\n");
			return;
		}
		for (int i = 1 + x * 10; i <= 9 + x * 10; i++) {
			if (isPrime(i)) {
				interesting(num + 1, i);
			}
		}
	}

	// num가 소수인지 판별하는 메소드
	private static Boolean isPrime(int num) {
		if (num == 1)
			return false;
		if (num == 2 || num == 3)
			return true;
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0)
				return false;
		}
		return true;
	}

}
