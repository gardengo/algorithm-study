import java.io.*;
import java.util.*;

public class Solution {
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			visited = new boolean[10];
			int N = Integer.parseInt(br.readLine());
			checkNum(N);

			int cur = N;
			while (!checkVisited()) {
				cur += N;
				checkNum(cur);
			}
			sb.append("#").append(test_case).append(" ").append(cur).append("\n"); 
		}

		System.out.println(sb);
	}

	public static void checkNum(int x) {
		while (x != 0) {
			visited[x - x / 10 * 10] = true;
			x = x / 10;
		}
	}

	public static boolean checkVisited() {
		boolean result = true;
		for (int i = 0; i < 10; i++)
			if (!visited[i])
				result = false;
		return result;
	}

}
