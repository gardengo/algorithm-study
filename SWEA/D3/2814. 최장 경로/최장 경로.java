import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
	static int answer;
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			graph = new ArrayList<ArrayList<Integer>>();
			graph.add(new ArrayList<Integer>()); // 0은 사용하지 않음
			for (int i = 1; i <= N; i++)
				graph.add(new ArrayList<Integer>());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int front = Integer.parseInt(st.nextToken());
				int back = Integer.parseInt(st.nextToken());
				graph.get(front).add(back);
				graph.get(back).add(front);
			}

			answer = 0;
			visited = new boolean[N + 1];
			for (int i = 1; i <= N; i++) {
				visited[i] = true;
				dfs(i, 1);
				visited[i] = false;
			}
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(int n, int depth) {
		if (depth > answer)
			answer = depth;
		for (int i = 0; i < graph.get(n).size(); i++) {
			int next = graph.get(n).get(i);
			if (!visited[next]) {
				visited[next] = true;
				dfs(next, depth + 1);
				visited[next] = false;
			}
		}
	}

}
