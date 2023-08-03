import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 T

		for (int test_case = 1; test_case <= T; test_case++) { // 테스트 케이스 만큼 반복
			char[] ironBar = br.readLine().toCharArray();

			int peice = 0;
			Stack<Integer> stack = new Stack<Integer>();
			for (int i = 0; i < ironBar.length; i++) {
				if (ironBar[i] == '(') {
					stack.add(i);
				} else {
					if (ironBar[i - 1] == '(') {
						stack.pop();
						peice += stack.size();
					} else {
						stack.pop();
						peice++;
					}
				}
			}
			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			sb.append(peice);
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
