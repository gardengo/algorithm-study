import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		if (N > 1023) {
			System.out.println(-1);
			return;
		}

		int idx = 0;
		root: for (int i = 0; i <= 987654321; i++) {
			for (int j = 1; j < Integer.toString(i).length(); j++) {
				if (i % (int) Math.pow(10, j) / (int) Math.pow(10, j - 1) >= i / (int) Math.pow(10, j) % 10) {
					i += (int) Math.pow(10, j);
					i = i / (int) Math.pow(10, j) * (int) Math.pow(10, j);
					i--;
					continue root;
				}
			}

			idx++;
			if (idx == N) {
				System.out.println(i);
				return;
			}
		}
		System.out.println("9876543210");
	}

}
