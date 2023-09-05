import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		long answer = multiple(A, B, C);
		System.out.println(answer);
	}

	static long multiple(int A, int B, int C) {
		if (B == 1)
			return A % C;

		long result = multiple(A, B / 2, C);
		result = (result * result) % C;
		if (B % 2 == 1) // 홀수
			result = (result * A) % C;

		return result;
	}

}
