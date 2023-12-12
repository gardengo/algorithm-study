import java.io.*;
import java.util.*;

class Solution {
	static long[] arr = new long[101];

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			long M = Long.parseLong(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++)
				arr[i] = Long.parseLong(st.nextToken());

			long lt = 1;
			long rt = 0;
			for (int i = 1; i <= N; i++)
				rt = Math.max(rt, arr[i]) + 1;

			while (lt < rt) { // upper bound
				long mid = (lt + rt) / 2;
				long candy = 0;

				for (int i = 1; i <= N; i++)
					candy += arr[i] / mid;

				if (candy >= M) { // 가능
					lt = mid + 1;
				} else { // 불가능
					rt = mid;
				}
			}

			sb.append(lt - 1);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}