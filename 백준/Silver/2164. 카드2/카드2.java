import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	private static Queue<Integer> queue = new LinkedList<Integer>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++)
			queue.add(i);

		discard();
		System.out.println(queue.poll());
	}

	private static void discard() {
		if (queue.size() == 1)
			return;
		queue.remove();
		queue.add(queue.poll());
		discard();
	}

}
