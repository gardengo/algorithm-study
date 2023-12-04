import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
	static int[] par = new int[100001];
	static int[] depth = new int[100001];
	static ArrayList<ArrayList<Integer>> child = new ArrayList<ArrayList<Integer>>();
	static HashMap<Long, Integer> hash = new HashMap<Long, Integer>();

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i <= 100000; i++)
			child.add(new ArrayList<Integer>());

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			int N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			// 자식 그래프를 초기화한다
			for (int i = 0; i <= N; i++)
				child.get(i).clear();
			hash.clear();

			// 부모 배열과 자식 그래프에 입력 값을 넣어준다
			for (int i = 2; i <= N; i++) {
				int parent = Integer.parseInt(st.nextToken());
				par[i] = parent;
				depth[i] = depth[parent] + 1;
				child.get(parent).add(i);
			}

			// bfs 탐색을 위한 큐를 만든다
			Queue<Integer> que = new ArrayDeque<Integer>();
			for (int i = 0; i < child.get(1).size(); i++)
				que.offer(child.get(1).get(i));
			int before = 1;
			int anc = 1;
			long dist = 0;

			// bfs 시작
			while (!que.isEmpty()) {
				int node = que.poll();
				// 현재 정점의 자식들을 큐에 담는다
				for (int i = 0; i < child.get(node).size(); i++)
					que.offer(child.get(node).get(i));

				// 이전 정점은 같은 depth 또는 부모 depth
				// depth를 확인해서 맞춰주고 그 다음 한칸씩 올라가면서 부모를 찾자
				int cur = node;
				if (depth[cur] != depth[before]) {
					cur = par[cur];
					dist++;
					if (cur == before) {
						before = node;
						continue;
					}
				}

				// 공통 조상을 찾을 때, 찾은 공통 조상을 해시맵에 저장하자
				// a에 더 작은 수가 올 수 있게 입력한다
				if (cur < before)
					anc = saveDist(cur, before);
				else
					anc = saveDist(before, cur);

				// 공통 조상과 노드들 사이의 depth 차이를 더해준다
				dist += depth[cur] - depth[anc];
				dist += depth[before] - depth[anc];

				before = node;
			}

			sb.append(dist);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static int saveDist(int a, int b) {
		if (a == b)
			return a;

		long key = (long) a * 100000 + (long) b;
		if (hash.containsKey(key))
			return hash.get(key);

		a = par[a];
		b = par[b];
		int result = 0;
		if (a < b)
			result = saveDist(a, b);
		else
			result = saveDist(b, a);

		hash.put(key, result);
		return result;
	}
}