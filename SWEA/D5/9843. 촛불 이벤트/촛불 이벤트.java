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
			int k = (int) Math.sqrt(N * 2);
			long need = (long) k * (k + 1) / 2;
			
			if (N == need)
				sb.append(k);
			else
				sb.append(-1);

			sb.append("\n");
		}
		System.out.print(sb);
	}
}