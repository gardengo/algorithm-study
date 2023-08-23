import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		ArrayDeque<Integer> deque = new ArrayDeque<Integer>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			if (command.equals("push_front")) {
				int x = Integer.parseInt(st.nextToken());
				deque.offerFirst(x);
			} else if (command.equals("push_back")) {
				int x = Integer.parseInt(st.nextToken());
				deque.offerLast(x);
			} else if (command.equals("pop_front")) {
				if (deque.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(deque.pollFirst()).append("\n");
			} else if (command.equals("pop_back")) {
				if (deque.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(deque.pollLast()).append("\n");
			} else if (command.equals("size")) {
				sb.append(deque.size()).append("\n");
			} else if (command.equals("empty")) {
				if (deque.isEmpty())
					sb.append(1).append("\n");
				else
					sb.append(0).append("\n");
			} else if (command.equals("front")) {
				if (deque.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(deque.peekFirst()).append("\n");
			} else if (command.equals("back")) {
				if (deque.isEmpty())
					sb.append(-1).append("\n");
				else
					sb.append(deque.peekLast()).append("\n");
			}
		}
		System.out.println(sb);
	}

}
