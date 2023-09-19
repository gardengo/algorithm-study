import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		int[] nArr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			nArr[i] = Integer.parseInt(st.nextToken());

		int M = Integer.parseInt(br.readLine());
		int[] mArr = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++)
			mArr[i] = Integer.parseInt(st.nextToken());

		Arrays.sort(nArr);
		for (int i = 0; i < M; i++) {
			int lt = 0;
			int rt = N-1;
			while (lt < rt) {
				int mid = (lt + rt) / 2;
				if (nArr[mid] < mArr[i]) {
					lt = mid + 1;
				} else {
					rt = mid;
				}
			}
			if (nArr[rt] == mArr[i])
				sb.append(1).append(" ");
			else
				sb.append(0).append(" ");
		}
		System.out.println(sb);
	}

}
