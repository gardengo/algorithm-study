import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		Queue<Integer> queue = new LinkedList<Integer>();

		// M부터 N까지의 자연수 중 소수인 수를 queue에 저장
		for (int i = M; i <= N; i++) {
			if (isDecimal(i))
				queue.offer(i);
		}

		while (!queue.isEmpty()) {
			sb.append(queue.poll());
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static boolean isDecimal(int n) {
		if (n == 1)
			return false;
		if (n == 2 || n == 3)
			return true;
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

}
