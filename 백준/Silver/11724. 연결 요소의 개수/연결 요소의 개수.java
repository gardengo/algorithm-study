import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static boolean[] visited;
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 정점의 개수 (최대 1000)
		int M = Integer.parseInt(st.nextToken()); // 간선의 개수 (최대 50만)
		graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<Integer>());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int front = Integer.parseInt(st.nextToken());
			int back = Integer.parseInt(st.nextToken());
			graph.get(front).add(back);
			graph.get(back).add(front);
		}

		int answer = 0;
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			if (visited[i])
				continue;
			answer++;
			bfs(i);
		}
		System.out.println(answer);
	}

	private static void bfs(int n) {
		Queue<Integer> que = new ArrayDeque<Integer>();
		que.add(n);

		while (!que.isEmpty()) {
			int cur = que.poll();
			if (visited[cur])
				continue;
			visited[cur] = true;

			ArrayList<Integer> list = graph.get(cur);
			for (int x : list) {
				if (!visited[x])
					que.add(x);
			}
		}
	}
}
