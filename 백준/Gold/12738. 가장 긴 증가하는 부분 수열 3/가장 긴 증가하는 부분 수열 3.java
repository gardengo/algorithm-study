import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());

		List<Integer> LIS = new ArrayList<Integer>();
		LIS.add(A[0]);
		for (int i = 1; i < N; i++) {
			if (LIS.get(LIS.size() - 1) < A[i]) {
				LIS.add(A[i]);
			} else {
				int lt = 0;
				int rt = LIS.size();
				while (lt < rt) {
					int mid = (lt + rt) / 2;
					// lower bound로 탐색하자
					if (LIS.get(mid) >= A[i]) {
						rt = mid;
					} else {
						lt = mid + 1;
					}
				}
				LIS.set(rt, A[i]);
			}
		}
		System.out.println(LIS.size());
	}

}
