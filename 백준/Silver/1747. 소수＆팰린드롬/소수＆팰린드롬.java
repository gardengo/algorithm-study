import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		boolean[] isNotPrime = new boolean[1003002];
		isNotPrime[0] = true;
		isNotPrime[1] = true;
		for (int i = 2; i <= 1003001; i++) {
			int j = 2;
			while (i * j <= 1003001)
				isNotPrime[i * j++] = true;
		}

		while (N <= 1003001) {
			if (!isNotPrime[N]) {
				String num = Integer.toString(N);
				for (int i = 0; i < (num.length() + 1) / 2; i++) {
					if (num.charAt(i) != num.charAt(num.length() - 1 - i))
						break;
					if (i == (num.length() + 1) / 2 - 1) {
						System.out.println(num);
						return;
					}
				}
			}
			N++;
		}
	}

}
