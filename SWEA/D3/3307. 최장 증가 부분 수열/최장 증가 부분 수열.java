import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int test = 1; test <= T; test++) {
			int N = Integer.parseInt(br.readLine());
			int[] LIS = new int[N];
			int cnt = 0;

			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			LIS[cnt] = Integer.parseInt(st.nextToken());

			for (int i = 1; i < N; i++) {
				int x = Integer.parseInt(st.nextToken());
				if (LIS[cnt] < x) {
					LIS[++cnt] = x;
				} else {
					int lt = 0;
					int rt = cnt;
					while (lt < rt) {
						int mid = (lt + rt) / 2;
						if (LIS[mid] >= x)
							rt = mid;
						else
							lt = mid + 1;
					}
					LIS[rt] = x;
				}
			}
			sb.append("#").append(test).append(" ").append(cnt + 1).append("\n");
		}
		System.out.println(sb);
	}

}
