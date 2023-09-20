import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int k = Integer.parseInt(br.readLine());

		int lt = 1;
		int rt = k;
		int mid = 0;
		int cnt = 0;
		while (lt < rt) {
			mid = (lt + rt) / 2;
			cnt = 0;
			for (int i = 1; i <= N; i++)
				cnt += Math.min(mid / i, N);
			if (cnt >= k) {
				rt = mid;
			} else {
				lt = mid + 1;
			}
		}
		System.out.println(rt);
	}

}
