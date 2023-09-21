import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		// 4자리 소수인 수를 표시(수행:100만)
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1000; i <= 9999; i++) {
			if (isPrime(i))
				list.add(i);
		}
		int size = list.size(); // 1061

		root: for (int test = 1; test <= T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int before = Integer.parseInt(st.nextToken());
			int after = Integer.parseInt(st.nextToken());
			if (before == after) {
				sb.append(0).append("\n");
				continue root;
			}
			boolean[] visited = new boolean[10000];

			// bfs로 최솟값을 구해보자
			Queue<int[]> que = new ArrayDeque<int[]>();
			for (int i = 0; i < size; i++) {
				int prime = list.get(i);
				int dif = 0;

				// 첫번째 자리 비교
				if (prime / 1000 != before / 1000)
					dif++;
				// 두번째 자리 비교
				if (prime / 100 % 10 != before / 100 % 10)
					dif++;
				// 세번째 자리 비교
				if (prime / 10 % 10 != before / 10 % 10)
					dif++;
				// 네번째 자리 비교
				if (prime % 10 != before % 10)
					dif++;
				// 차이가 1이면 바꿀 수 있다
				if (dif == 1) {
					if (prime == after) {
						sb.append(1).append("\n");
						continue root;
					} else
						que.offer(new int[] { prime, 1 });
				}
			}

			while (!que.isEmpty()) {
				int[] cur = que.poll();
				int num = cur[0];
				int cnt = cur[1];
				if (visited[num])
					continue;
				visited[num] = true;

				for (int i = 0; i < size; i++) {
					int prime = list.get(i);
					int dif = 0;

					// 첫번째 자리 비교
					if (prime / 1000 != num / 1000)
						dif++;
					// 두번째 자리 비교
					if (prime / 100 % 10 != num / 100 % 10)
						dif++;
					// 세번째 자리 비교
					if (prime / 10 % 10 != num / 10 % 10)
						dif++;
					// 네번째 자리 비교
					if (prime % 10 != num % 10)
						dif++;
					// 차이가 1이면 바꿀 수 있다
					if (dif == 1) {
						if (prime == after) {
							sb.append(cnt + 1).append("\n");
							continue root;
						} else
							que.offer(new int[] { prime, cnt + 1 });
					}
				}
			}
		}
		System.out.println(sb);

	}

	public static boolean isPrime(int n) {
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
}
