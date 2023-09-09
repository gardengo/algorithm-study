import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb  = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		boolean[] visited = new boolean[N + 1];
		visited[1] = true;
		int[] parents = new int[N + 1];
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i <= N; i++)
			graph.add(new ArrayList<Integer>());
		Queue<int[]> que = new ArrayDeque<int[]>();

		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (a == 1 || b == 1)
				que.offer(new int[] { a, b });
			else {
				graph.get(a).add(b);
				graph.get(b).add(a);
			}
		}

		while (!que.isEmpty()) {
			int[] cur = que.poll();
			int a = cur[0];
			int b = cur[1];
			if (visited[a] && visited[b])
				continue;
			if (visited[a]) {
				parents[b] = a;
				visited[b] = true;
				for (int i = 0; i < graph.get(b).size(); i++) {
					int c = graph.get(b).get(i);
					if (!visited[c])
						que.offer(new int[] { b, c });
				}
			} else {
				parents[a] = b;
				visited[a] = true;
				for (int i = 0; i < graph.get(a).size(); i++) {
					int c = graph.get(a).get(i);
					if (!visited[c])
						que.offer(new int[] { a, c });
				}
			}
		}

		for (int i = 2; i <= N; i++)
			sb.append(parents[i]).append("\n");
		System.out.println(sb);
	}

}
