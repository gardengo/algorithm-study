import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {
	static ArrayList<LinkedList<Integer>> magnet;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= T; test_case++) {
			int K = Integer.parseInt(br.readLine().trim());
			magnet = new ArrayList<LinkedList<Integer>>();
			for (int i = 0; i <= 4; i++)
				magnet.add(new LinkedList<Integer>());

			for (int i = 1; i <= 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				while (st.hasMoreTokens())
					magnet.get(i).add(Integer.parseInt(st.nextToken())); // 0:N극, 1:S극
			}

			boolean[] visited = new boolean[5];
			for (int i = 0; i < K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()); // 1:시계방향, -1:반시계방향
				rotate(num, dir, visited);
			}

			int answer = 0;
			for (int i = 1; i <= 4; i++) {
				if (magnet.get(i).get(0) == 1)
					answer += Math.pow(2, i - 1);
			}
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void rotate(int num, int dir, boolean[] visited) {
		int curR, curL, leftR, rightL;
		visited[num] = true;
		if (num != 1 && !visited[num - 1]) {
			curL = magnet.get(num).get(6);
			leftR = magnet.get(num - 1).get(2);
			if (curL != leftR)
				rotate(num - 1, -dir, visited);
		}
		if (num != 4 && !visited[num + 1]) {
			curR = magnet.get(num).get(2);
			rightL = magnet.get(num + 1).get(6);
			if (curR != rightL)
				rotate(num + 1, -dir, visited);
		}
		visited[num] = false;
		if (dir == 1) { // 시계방향 회전
			int last = magnet.get(num).remove(7);
			magnet.get(num).add(0, last);
		} else { // 반시계방향 회전
			int first = magnet.get(num).remove(0);
			magnet.get(num).add(first);
		}
	}
}
