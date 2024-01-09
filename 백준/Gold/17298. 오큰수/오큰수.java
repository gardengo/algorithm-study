import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine()); // 최대 100만
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		int[] answer = new int[N]; // 정답 배열
		Stack<Integer> stack = new Stack<>();

		// 오른쪽에서부터 채워보자
		for (int i = N - 1; i >= 0; i--) {
			while (!stack.isEmpty() && stack.peek() <= arr[i]) {
				stack.pop();
			}

			if (stack.isEmpty()) {
				answer[i] = -1;
			} else {
				answer[i] = stack.peek();
			}

			stack.add(arr[i]);
		}

		for (int i : answer)
			sb.append(i).append(" ");

		System.out.println(sb);
	}
}
