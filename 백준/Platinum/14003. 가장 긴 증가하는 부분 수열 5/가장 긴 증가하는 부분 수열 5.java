import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		int[] LIS = new int[N];
		int[] LIS_pos = new int[N];
		int idx = 0;
		int point = 0;

		// 1. 수열의 가장 큰 값보다 더 큰 값이 나오면 수열에 추가해준다
		// 2. 수열의 가장 큰 값보다 작은 값이 나오면 그 값보다 한 단계 큰 값을 찾아서 그 위치에 넣어준다
		// 3. 값을 넣은 위치를 LIS_pos 배열에 저장
		// 4. LIS_pos 배열을 역순으로 탐색하며 정답 수열 출력

		LIS[0] = arr[idx];
		LIS_pos[0] = 1;
		root: for (int i = 1; i < N; i++) {
			if (LIS[idx] < arr[i]) {
				LIS[++idx] = arr[i];
				LIS_pos[i] = idx + 1;
				point = i;
			} else {
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
				LIS_pos[i] = lt + 1;
			}
		}

		Stack<Integer> stack = new Stack<Integer>();
		int pos = LIS_pos[point];
		stack.push(arr[point]);
		for (int i = point - 1; i >= 0; i--) {
			if (LIS_pos[i] == pos - 1) {
				stack.push(arr[i]);
				pos--;
			}
		}

		sb.append(idx + 1).append("\n");
		for (int i = 0; i < idx + 1; i++)
			sb.append(stack.pop()).append(" ");
		System.out.println(sb);
	}
}
