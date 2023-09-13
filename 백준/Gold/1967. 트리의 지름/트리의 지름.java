import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int n, answer;
	static ArrayList<ArrayList<int[]>> graph;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		graph = new ArrayList<ArrayList<int[]>>();
		for (int i = 0; i <= n; i++)
			graph.add(new ArrayList<int[]>());

		for (int i = 0; i < n - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			graph.get(start).add(new int[] { end, cost });
		}

		answer = 0;
		dfs(1);
		System.out.println(answer);
	}

	private static int dfs(int i) {
		int max1 = 0, max2 = 0;

		ArrayList<int[]> list = graph.get(i);
		for (int j = 0; j < list.size(); j++) {
			int next = list.get(j)[0];
			int cost = list.get(j)[1];

			int sum = dfs(next) + cost;
			if (sum > max1) {
				max2 = max1;
				max1 = sum;
			} else if (sum > max2) {
				max2 = sum;
			}
		}

		answer = max1 + max2 > answer ? max1 + max2 : answer;
		return max1;
	}
}