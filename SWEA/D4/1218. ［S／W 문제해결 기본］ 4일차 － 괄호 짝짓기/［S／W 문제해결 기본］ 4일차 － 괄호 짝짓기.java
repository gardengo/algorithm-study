import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= 10; test_case++) { // 테스트 케이스 만큼 반복
			int N = Integer.parseInt(br.readLine()); // 문자열의 길이
			String str = br.readLine(); // 문자열 입력

			// 각 괄호의 여는 문자를 담는 Stack 생성
			Stack<Character> stack1 = new Stack<Character>();
			Stack<Character> stack2 = new Stack<Character>();
			Stack<Character> stack3 = new Stack<Character>();
			Stack<Character> stack4 = new Stack<Character>();

			sb.append("#");
			sb.append(test_case);
			sb.append(" ");

			for (char ch : str.toCharArray()) {
				if (ch == '(') {
					stack1.add(ch);
				} else if (ch == '[') {
					stack2.add(ch);
				} else if (ch == '{') {
					stack3.add(ch);
				} else if (ch == '<') {
					stack4.add(ch);
				} else if (ch == ')' && stack1.size() > 0) {
					stack1.pop();
				} else if (ch == ']' && stack2.size() > 0) {
					stack2.pop();
				} else if (ch == '}' && stack3.size() > 0) {
					stack3.pop();
				} else if (ch == '>' && stack4.size() > 0) {
					stack4.pop();
				} else {
					break; // 조건을 만족하지 못하면 break
				}
			}
			if (stack1.empty() && stack2.empty() && stack3.empty() && stack4.empty())
				sb.append(1); // 괄호의 짝이 맞을 경우 1을 출력
			else
				sb.append(0); // 괄호의 짝이 맞지 않을 경우 0을 출력
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
