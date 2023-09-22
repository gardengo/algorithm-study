import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<int[]> course = new PriorityQueue<int[]>((a, b) -> Integer.compare(a[0], b[0]));
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			course.offer(new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) });
		}

		PriorityQueue<Integer> room = new PriorityQueue<Integer>();
		while (!course.isEmpty()) {
			int[] cur = course.poll();

			if (room.isEmpty())
				room.offer(cur[1]);
			else {
				if (room.peek() <= cur[0])
					room.poll();
				room.offer(cur[1]);
			}
		}
		System.out.println(room.size());
	}

}
