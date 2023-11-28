import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class Solution {
	static Node[] nodes;

	static class Node {
		int parent, left, right;

		Node() {
			parent = 1;
			left = 0;
			right = 0;
		}
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());

			nodes = new Node[V + 1];
			for (int i = 1; i <= V; i++)
				nodes[i] = new Node();

			st = new StringTokenizer(br.readLine()); // 간선의 정보
			for (int i = 0; i < E; i++) {
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				if (nodes[parent].left == 0) {
					nodes[parent].left = child;
				} else {
					nodes[parent].right = child;
				}
				nodes[child].parent = parent;
			}

			int parent = findParent(first, second);
			int size = subTreeSize(parent);
			sb.append(parent).append(" ").append(size);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static int findParent(int first, int second) {
		Queue<Integer> que = new ArrayDeque<Integer>();
		que.offer(first);
		while (true) {
			first = nodes[first].parent;
			que.offer(first);
			if (first == 1) {
				break;
			}
		}

		while (true) {
			if (que.contains(second)) {
				return second;
			}
			second = nodes[second].parent;
		}
	}

	static int subTreeSize(int parent) {
		int size = 0;
		Queue<Integer> que = new ArrayDeque<Integer>();
		que.offer(parent);
		while (!que.isEmpty()) {
			int cur = que.poll();
			size++;
			if (nodes[cur].left != 0) {
				que.offer(nodes[cur].left);
			}
			if (nodes[cur].right != 0) {
				que.offer(nodes[cur].right);
			}
		}
		return size;
	}
}