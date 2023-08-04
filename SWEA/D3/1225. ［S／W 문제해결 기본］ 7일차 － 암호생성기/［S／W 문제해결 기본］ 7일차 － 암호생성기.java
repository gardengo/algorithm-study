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
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			List<Integer> list = new ArrayList<Integer>();
			for (int i = 0; i < 8; i++)
				list.add(Integer.parseInt(st.nextToken()));

			int num = 1;
			while (list.get(7) > 0) {
				if (list.get(0) - num < 0)
					list.add(0);
				else
					list.add(list.get(0) - num);
				list.remove(0);
				num++;
				if (num > 5)
					num = 1;
			}

			sb.append("#");
			sb.append(test_case);
			for (int i = 0; i < 8; i++) {
				sb.append(" ");
				sb.append(list.get(i));
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
