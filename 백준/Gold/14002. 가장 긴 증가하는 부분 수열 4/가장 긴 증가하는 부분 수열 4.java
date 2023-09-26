import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] A = new int[N];
		for (int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());

		int[] LIS = new int[N];
		int[] pos = new int[N];
		LIS[0] = A[0];
		int size = 1;
		for (int i = 1; i < N; i++) {
			if (LIS[size - 1] < A[i]) {
				LIS[size] = A[i];
				pos[i] = size;
				size++;
			} else {
				int lt = 0;
				int rt = size;
				while (lt < rt) {
					int mid = (lt + rt) / 2;
					if (LIS[mid] >= A[i]) {
						rt = mid;
					} else {
						lt = mid + 1;
					}
				}
				LIS[rt] = A[i];
				pos[i] = rt;
			}
		}

		sb.append(size).append("\n");
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = N - 1; i >= 0; i--) {
			if (pos[i] == size - 1) {
				stack.push(A[i]);
				size--;
			}
		}
		while (!stack.isEmpty())
			sb.append(stack.pop()).append(" ");
		System.out.println(sb);
	}

}
