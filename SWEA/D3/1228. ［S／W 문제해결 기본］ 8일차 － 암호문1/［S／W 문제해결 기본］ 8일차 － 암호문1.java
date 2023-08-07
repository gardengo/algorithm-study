import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= 10; test_case++) { // 테스트 케이스 만큼 반복
			int N = Integer.parseInt(br.readLine()); // 원본 암호문의 길이
			List<Integer> original = new ArrayList<Integer>(); // 원본 암호문
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				original.add(Integer.parseInt(st.nextToken()));
			int C = Integer.parseInt(br.readLine()); // 명령어의 개수
			String[] command = br.readLine().split(" "); // 명령어

			int length = command.length;
			int x = 0;
			int y = 0;
			for (int i = 0; i < length; i++) {
				if (command[i].equals("I")) {
					x = Integer.parseInt(command[++i]);
					y = Integer.parseInt(command[++i]);
					if (x >= 10) {
						i = i + y;
						continue;
					}
					List<Integer> left = new ArrayList<Integer>();
					List<Integer> right = new ArrayList<Integer>();
					for (int j = 0; j <= x - 1; j++) { // x를 포함하는 왼쪽 서브리스트
						left.add(original.get(j));
					}
					for (int j = x; j < 10; j++) { // x를 포함하지 않는 오른쪽 서브리스트
						right.add(original.get(j));
					}
					for (int j = 0; j < y; j++) { // x 바로 뒤부터 y개 만큼 값 추가
						left.add(Integer.parseInt(command[++i]));
					}
					if (left.size() < 10) {
						int idx = 0;
						for (int j = left.size(); j < 10; j++)
							left.add(right.get(idx++));
					}
					original.clear();
					for (int k = 0; k < 10; k++)
						original.add(left.get(k));
				}
			}
			sb.append("#");
			sb.append(test_case);
			for (int i = 0; i < 10; i++) {
				sb.append(" ");
				sb.append(original.get(i));
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
