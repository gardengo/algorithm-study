import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Stack<Top> stack = new Stack<Top>();
		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			Top top = new Top(Integer.parseInt(st.nextToken()), i + 1);
			while (!stack.isEmpty()) {
				if (stack.peek().height > top.height) {
					sb.append(stack.peek().index);
					sb.append(" ");
					stack.push(top);
					break;
				} else {
					stack.pop();
				}
			}
			if (stack.isEmpty()) {
				sb.append(0);
				sb.append(" ");
				stack.push(top);
			}
		}
		System.out.println(sb);
	}

}

class Top {
	int height;
	int index;

	Top(int height, int index) {
		this.height = height;
		this.index = index;
	}
}
