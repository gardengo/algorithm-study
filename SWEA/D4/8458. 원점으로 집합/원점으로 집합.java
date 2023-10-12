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
			StringTokenizer st = new StringTokenizer(br.readLine());
			int dist = Math.abs(Integer.parseInt(st.nextToken())) + Math.abs(Integer.parseInt(st.nextToken()));
			int max = dist;

			int move = 0;
			boolean flag = false;
			for (int i = 1; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Math.abs(Integer.parseInt(st.nextToken())) + Math.abs(Integer.parseInt(st.nextToken()));
				if (dist % 2 != x % 2) 
					flag = true;
				max = x > max ? x : max;
			}

			if (flag) {
				move = -1;
			} else {
				long sum = 0;
				while (true) {
					sum += move;
					if (sum >= max && (sum - max) % 2 == 0)
						break;
					move++;
				}
			}

			sb.append("#").append(test).append(" ").append(move).append("\n");
		}
		System.out.println(sb);
	}

}
