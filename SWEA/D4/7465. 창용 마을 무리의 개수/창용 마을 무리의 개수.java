import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int[] parents;

	private static void make(int n) {
		parents = new int[n + 1];
		for (int i = 1; i <= n; i++)
			parents[i] = i;
	}

	private static int find(int n) {
		if (parents[n] == n)
			return n;
		else
			return parents[n] = find(parents[n]);
	}

	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);

		if (aRoot == bRoot)
			return false;
		else {
			parents[bRoot] = aRoot;
			return true;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			make(N);
			int rootNum = N;
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int front = Integer.parseInt(st.nextToken());
				int back = Integer.parseInt(st.nextToken());
				if (union(front, back))
					rootNum--;
			}
			sb.append("#").append(test_case).append(" ").append(rootNum).append("\n");
		}
		System.out.println(sb);
	}

}
