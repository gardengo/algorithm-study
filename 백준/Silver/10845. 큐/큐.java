import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Queue<Integer> que = new ArrayDeque<Integer>();
		int x = 0;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			if (command.equals("push")) {
				x = Integer.parseInt(st.nextToken());
				que.offer(x);
			} else if (command.equals("pop")) {
				if (que.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(que.poll()).append("\n");
			} else if (command.equals("size")) {
				sb.append(que.size()).append("\n");
			} else if (command.equals("empty")) {
				if (que.isEmpty())
					sb.append(1).append("\n");
				else
					sb.append(0).append("\n");
			} else if (command.equals("front")) {
				if (que.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(que.peek()).append("\n");
			} else if (command.equals("back")) {
				if (que.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(x).append("\n");
			}
		}
		System.out.println(sb);
	}

}
