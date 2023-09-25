import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Things {
		public int v, c;

		public Things(int v, int c) {
			this.v = v; // 무게
			this.c = c; // 만족도
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 집에 있는 물건의 종류(1<=N<=100)
		int M = Integer.parseInt(st.nextToken()); // 가방의 최대 무게(1<=M<=10000)
		PriorityQueue<Things> pq = new PriorityQueue<Things>((a, b) -> Integer.compare(a.v, b.v));
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			// k개를 모두 넣으면 무조건 시간초과 -> 개수를 줄여서 넣어야함
			// 1 2 4 8 16 ... 2의 배수의 합으로 표현
			while (k > 0) {
				if (k % 2 == 0) { // k가 짝수일 때
					pq.offer(new Things(v * k / 2, c * k / 2));
					k = k / 2;
				} else { // k가 홀수일 때
					pq.offer(new Things(v, c));
					k = k - 1;
				}
			}
		}

		int[] dp = new int[M + 1];
		while (!pq.isEmpty()) {
			Things thing = pq.poll();
			for (int i = M - thing.v; i >= 0; i--)
				dp[i + thing.v] = Math.max(dp[i + thing.v], dp[i] + thing.c);
		}

		int max = 0;
		for (int i = 1; i <= M; i++)
			max = dp[i] > max ? dp[i] : max;
		System.out.println(max);
	}

}
