import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int nPk = permutation(N, K);
		System.out.println(nPk);
	}

	private static int permutation(int n, int k) {
		int front = 1;
		int back = 1;
		for (int i = n; i > n - k; i--)
			front *= i;
		for (int i = k; i > 0; i--)
			back *= i;
		return front / back;
	}
}
