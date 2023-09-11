import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class Main {
	static String str;
	static Stack<Character> stack;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		stack = new Stack<Character>();

		str = br.readLine();
		int length = str.length();
		for (int i = 0; i < length; i++) {
			char ch = str.charAt(i);
			if (Character.isAlphabetic(ch)) {
				sb.append(ch); // 문자가 알파벳이면 바로 붙인다
			} else if (ch == '(') {
				i = bracket(i);
			} else {
				if (stack.isEmpty()) {
					stack.push(str.charAt(i));
					continue;
				}
				if (str.charAt(i) == '*' || str.charAt(i) == '/') { // *,/ 연산자를 만나면
					while (stack.peek() == '*' || stack.peek() == '/') {
						sb.append(stack.pop()); // *,/ 연산자를 모두 붙인다
						if (stack.isEmpty())
							break;
					}
				} else if (str.charAt(i) == '+' || str.charAt(i) == '-') { // +,- 연산자를 만나면
					while (!stack.isEmpty()) // 연산자를 모두 붙인다
						sb.append(stack.pop());
				}
				stack.push(str.charAt(i));
			}
		}
		while (!stack.isEmpty())
			sb.append(stack.pop());

		System.out.println(sb);
	}

	static int bracket(int idx) {
		Stack<Character> stack = new Stack<Character>();

		while (str.charAt(++idx) != ')') {
			char ch = str.charAt(idx);
			if (Character.isAlphabetic(ch)) {
				sb.append(ch); // 문자가 알파벳이면 바로 붙인다
			} else if (ch == '(')
				idx = bracket(idx);
			else {
				if (stack.isEmpty()) {
					stack.push(str.charAt(idx));
					continue;
				}
				if (str.charAt(idx) == '*' || str.charAt(idx) == '/') { // *,/ 연산자를 만나면
					while (stack.peek() == '*' || stack.peek() == '/') {
						sb.append(stack.pop()); // *,/ 연산자를 모두 붙인다
						if (stack.isEmpty())
							break;
					}
				} else if (str.charAt(idx) == '+' || str.charAt(idx) == '-') { // +,- 연산자를 만나면
					while (!stack.isEmpty()) // 연산자를 모두 붙인다
						sb.append(stack.pop());
				}
				stack.push(str.charAt(idx));
			}
		}
		while (!stack.isEmpty())
			sb.append(stack.pop());

		return idx;
	}

}
