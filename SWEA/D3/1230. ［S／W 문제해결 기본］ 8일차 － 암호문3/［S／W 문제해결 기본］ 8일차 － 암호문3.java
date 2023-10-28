import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		LinkedList<Integer> list = new LinkedList<>();
		int T = 10;

		for (int test_case = 1; test_case <= T; test_case++) {
			list.clear();

			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				list.add(Integer.parseInt(st.nextToken()));

			int M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				String command = st.nextToken();
				if (command.equals("I")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					for (int j = 0; j < y; j++) 
						list.add(x++, Integer.parseInt(st.nextToken()));
				} else if (command.equals("D")) {
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					for (int j = 0; j < y; j++)
						list.remove(x);
				} else if (command.equals("A")) {
					int y = Integer.parseInt(st.nextToken());
					for (int j = 0; j < y; j++)
						list.add(Integer.parseInt(st.nextToken()));
				}
			}

			sb.append("#").append(test_case);
			for (int i = 0; i < 10; i++)
				sb.append(" ").append(list.get(i));
			sb.append("\n");
		}

		System.out.println(sb);
	}

}
