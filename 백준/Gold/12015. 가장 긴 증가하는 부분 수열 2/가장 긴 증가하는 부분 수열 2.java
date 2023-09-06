import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		int[] LIS = new int[N];
		Arrays.fill(LIS, 1000000);
		int idx = 0;

		LIS[0] = arr[idx];
		root: for (int i = 1; i < N; i++) {
			if (LIS[idx] < arr[i])
				LIS[++idx] = arr[i];
			else {
				int lt = 0;
				int rt = idx;
				int mid = 0;

				while (lt < rt) {
					mid = (lt + rt) / 2;
					if (LIS[mid] < arr[i])
						lt = mid + 1;
					else if (LIS[mid] > arr[i])
						rt = mid;
					else
						continue root;
				}
				LIS[lt] = arr[i];
			}
		}

		System.out.println(idx + 1);
	}
}
