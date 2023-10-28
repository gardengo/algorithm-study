import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int total = (1 << 10) - 1; // 모든 숫자가 등장 했을 때(1023)

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());
			int visited = 0;

			int cnt = 1;
			while (true) {
				char[] arr = String.valueOf(N * cnt).toCharArray(); // 문자 배열로 변환
				for (char ch : arr) {
					int num = ch - '0';
					visited = visited | (1 << num);
				}
				if (visited == total) // 일치하면 종료
					break;
				cnt++;
			}

			sb.append("#").append(test_case).append(" ").append(N * cnt).append("\n");
		}

		System.out.println(sb);
	}

}
