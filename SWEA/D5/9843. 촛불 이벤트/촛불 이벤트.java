import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			long N = Long.parseLong(br.readLine());
			long lt = 1; // k의 왼쪽 끝
			long rt = 2000000000; // k의 오른쪽 끝
			long res = 0; // 최고의 k

			while (lt < rt) { // upper bound
				long mid = (lt + rt) / 2;
				long need = mid * (mid + 1) / 2; // 필요한 양초

				if (need <= N) { // 삼각형을 만들 수 있으면
					res = mid;
					lt = mid + 1;
				} else { // 삼각형을 만들 수 없으면
					rt = mid;
				}
			}

			long need = res * (res + 1) / 2;
			if (N != need)
				res = -1;

			sb.append(res);
			sb.append("\n");
		}
		System.out.print(sb);
	}
}