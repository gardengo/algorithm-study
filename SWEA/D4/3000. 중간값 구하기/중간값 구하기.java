import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Solution {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a, b) -> b - a);
		PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((a, b) -> a - b);
		int MOD = 20171109;

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			int answer = 0;
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());

			maxHeap.clear();
			minHeap.clear();
			minHeap.offer(A);

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());

				// 더 큰 수를 minHeap에, 작은 수를 maxHeap에 넣는다
				if (X > Y) {
					minHeap.offer(X);
					maxHeap.offer(Y);
				} else {
					minHeap.offer(Y);
					maxHeap.offer(X);
				}

				// minHeap의 peek가 중간값이 돼야함
				// minHeap의 peek가 maxHeap의 peek보다 커야함
				while (minHeap.peek() < maxHeap.peek()) {
					int x = minHeap.poll();
					int y = maxHeap.poll();
					minHeap.offer(y);
					maxHeap.offer(x);
				}

				// minHeap의 peek가 중간값임이 보장됨
				answer = (answer + minHeap.peek() % MOD) % MOD;
			}

			sb.append(answer);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}