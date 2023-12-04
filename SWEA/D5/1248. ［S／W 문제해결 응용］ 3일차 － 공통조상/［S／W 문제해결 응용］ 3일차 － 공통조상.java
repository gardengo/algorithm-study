import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class Solution {
	static int anc;
	static int[] par, size;
	static Stack<Integer> stack1, stack2;
	static ArrayList<ArrayList<Integer>> graph;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken()); // 정점의 개수
			int E = Integer.parseInt(st.nextToken()); // 간선의 개수
			int v1 = Integer.parseInt(st.nextToken()); // 정점1
			int v2 = Integer.parseInt(st.nextToken()); // 정점2

			anc = 0; // 공통 조상
			par = new int[V + 1]; // 부모
			size = new int[V + 1]; // 서브트리 크기
			stack1 = new Stack<Integer>(); // 부모를 담은 스택
			stack2 = new Stack<Integer>();
			graph = new ArrayList<ArrayList<Integer>>(); // 자식을 담은 그래프
			for (int i = 0; i <= V; i++)
				graph.add(new ArrayList<Integer>());

			// 간선의 정보를 입력받아 부모와 자식을 채운다
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				int e1 = Integer.parseInt(st.nextToken());
				int e2 = Integer.parseInt(st.nextToken());
				par[e2] = e1;
				graph.get(e1).add(e2);
			}

			// 정점1, 정점2의 부모들을 스택에 담는다
			int cur = v1;
			while (cur != 0) {
				stack1.push(cur);
				cur = par[cur];
			}
			cur = v2;
			while (cur != 0) {
				stack2.push(cur);
				cur = par[cur];
			}

			// 부모 스택에서 하나씩 빼며 마지막 공통 조상을 찾는다
			while (true) {
				int par1 = stack1.pop();
				int par2 = stack2.pop();
				if (par1 == par2) {
					anc = par1;
				} else {
					break;
				}
			}

			dfs(1);

			sb.append(anc).append(" ").append(size[anc]);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static void dfs(int v) {
		size[v] = 1;
		for (int i = 0; i < graph.get(v).size(); i++) {
			dfs(graph.get(v).get(i));
			size[v] += size[graph.get(v).get(i)];
		}
	}
}