import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int[] bag = new int[1001];

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			Arrays.fill(bag, 0); // 가방 초기화

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int v = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());

				// K-v부터 0까지 c를 더해줌
				for (int j = K - v; j >= 0; j--) {
					bag[j + v] = bag[j + v] < bag[j] + c ? bag[j] + c : bag[j + v];
				}
			}
			sb.append("#").append(test_case).append(" ").append(bag[K]).append("\n");
		}
		System.out.println(sb);
	}
}