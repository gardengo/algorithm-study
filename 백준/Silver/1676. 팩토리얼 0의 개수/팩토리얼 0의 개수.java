import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BigInteger N = BigInteger.valueOf(Integer.parseInt(br.readLine()));
		searchNotZero(factorial(N));

	}

	private static void searchNotZero(BigInteger num) {
		String str = String.valueOf(num);
		for (int i = str.length() - 1; i >= 0; i--) {
			if (str.charAt(i) != '0') {
				System.out.println(str.length() - 1 - i);
				return;
			}
		}
	}

	private static BigInteger factorial(BigInteger N) {
		if (N.compareTo(BigInteger.ONE) > 0)
			return N.multiply(factorial(N.subtract(BigInteger.ONE)));
		else
			return BigInteger.ONE;
	}
}
