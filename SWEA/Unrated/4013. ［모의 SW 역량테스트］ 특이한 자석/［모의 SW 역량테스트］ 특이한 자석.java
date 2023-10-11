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
		int T = Integer.parseInt(br.readLine());

		for (int test = 1; test <= T; test++) {
			magnet = new ArrayList<LinkedList<Integer>>();
			int K = Integer.parseInt(br.readLine());
			for (int i = 0; i < 4; i++) {
				magnet.add(new LinkedList<Integer>());
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++)
					magnet.get(i).add(Integer.parseInt(st.nextToken()));
			}
			for (int i = 0; i < K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken()) - 1;
				int dir = Integer.parseInt(st.nextToken());
				boolean[] visited = new boolean[4];
				rotate(idx, dir, visited);
			}
			int answer = 0;
			for (int i = 0; i < 4; i++)
				answer += magnet.get(i).get(0) << i;
			sb.append("#").append(test).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	public static void rotate(int idx, int dir, boolean[] visited) {
		visited[idx] = true;
		if (idx > 0) {
			if (!visited[idx - 1] && magnet.get(idx).get(6) != magnet.get(idx - 1).get(2))
				rotate(idx - 1, -dir, visited);
		}
		if (idx < 3) {
			if (!visited[idx + 1] && magnet.get(idx).get(2) != magnet.get(idx + 1).get(6))
				rotate(idx + 1, -dir, visited);
		}

		if (dir == 1)
			magnet.get(idx).addFirst(magnet.get(idx).removeLast());
		else
			magnet.get(idx).addLast(magnet.get(idx).removeFirst());
	}

}
