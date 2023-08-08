import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리 20,168kb 실행시간 136ms
public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= 10; test_case++) { // 테스트 케이스 만큼 반복
			int N = Integer.parseInt(br.readLine()); // 정점의 총 수
			int answer = 1;
			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			if (N % 2 == 0) {
				answer = 0;
			}
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				st.nextToken();
				String str = st.nextToken();
				if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
					if (!st.hasMoreTokens()) {
						answer = 0;
					}
					while (st.hasMoreTokens()) {
						st.nextToken();
					}
				} else {
					if (st.hasMoreTokens()) {
						answer = 0;
					}
					while (st.hasMoreTokens()) {
						st.nextToken();
					}
				}
			}
			sb.append(answer);
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
