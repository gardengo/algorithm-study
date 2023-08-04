import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<Integer>();
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(br.readLine());

		int rt = 1;
		for (int x : arr) {
			while (rt <= x) {
				stack.push(rt);
				sb.append("+");
				sb.append("\n");
				rt++;
			}
			int num = stack.pop();
			if (x != num) {
				System.out.println("NO");
				System.exit(0);
			}
			sb.append("-");
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
