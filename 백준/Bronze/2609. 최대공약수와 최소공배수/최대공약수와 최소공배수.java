import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int divisor = greatestCommonDivisor(n, m);
		int multiple = greatestCommonMultiple(n, m);
		System.out.println(divisor);
		System.out.println(multiple);
	}

	private static int greatestCommonDivisor(int n, int m) {
		int min = Math.min(n, m);
		int divisor = 1;
		for (int i = 1; i <= min; i++) {
			if (n % i == 0 && m % i == 0) {
				divisor = i;
			}
		}
		return divisor;
	}

	private static int greatestCommonMultiple(int n, int m) {
		int divisor = greatestCommonDivisor(n, m);
		int multiple = n * m / divisor;
		return multiple;
	}
}
