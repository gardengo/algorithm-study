import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());

		for (int test = 1; test <= T; test++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			int d = N / 4; // 자릿수

			pq.clear();
			StringBuilder inputStr = new StringBuilder();
			inputStr.append(br.readLine());

			for (int j = 0; j < d; j++) {
				for (int i = 0; i < 4; i++) {
					String hexStr = inputStr.substring(i * d, i * d + d);
					int num = Integer.parseInt(hexStr, 16);
					if (!pq.contains(num))
						pq.offer(num);
				}

				char last = inputStr.charAt(inputStr.length() - 1);
				inputStr.deleteCharAt(inputStr.length() - 1);
				inputStr.insert(0, last);
			}

			int answer = 0;
			for (int i = 0; i < K; i++)
				answer = pq.poll();
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

}
