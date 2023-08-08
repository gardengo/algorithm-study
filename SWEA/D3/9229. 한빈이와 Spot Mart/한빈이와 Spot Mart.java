import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 메모리 95,880kb 실행시간 489kb
public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 T

		for (int test_case = 1; test_case <= T; test_case++) { // 테스트 케이스 만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 과자 봉지의 개수
			int M = Integer.parseInt(st.nextToken()); // 무게 합 제한
			int[] a = new int[N]; // 과자 봉지의 무게
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				a[i] = Integer.parseInt(st.nextToken());

			int max = -1;
			Arrays.sort(a);
			int lt = 0;
			int rt = N - 1;
			while (lt < rt) {
				int sum = a[lt] + a[rt];
				if (sum > M) {
					rt--;
				} else if (sum == M) {
					max = sum;
					break;
				} else {
					max = Math.max(max, sum);
					lt++;
				}
			}

			sb.append("#").append(test_case).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}

}
