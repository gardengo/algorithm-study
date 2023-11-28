import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	static Node[] node;
	static StringBuilder sb;

	static class Node {
		char data;
		int left;
		int right;

		Node(char data) {
			this.data = data;
			this.left = 0;
			this.right = 0;
		}
	}

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		int T = 10;
		node = new Node[101];

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			int N = Integer.parseInt(br.readLine());

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				int num = Integer.parseInt(st.nextToken());
				char data = st.nextToken().charAt(0);
				node[num] = new Node(data);
				if (st.hasMoreTokens())
					node[i].left = Integer.parseInt(st.nextToken());
				if (st.hasMoreTokens())
					node[i].right = Integer.parseInt(st.nextToken());
			}
			inOrder(1);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static void inOrder(int num) {
		if (node[num].left != 0) {
			inOrder(node[num].left);
		}
		// 왼쪽 노드가 없거나 갔다왔으면 출력
		sb.append(node[num].data);
		
		if (node[num].right != 0) {
			inOrder(node[num].right);
		}
	}
}