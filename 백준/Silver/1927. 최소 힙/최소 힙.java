import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
		for (int i = 0; i < N; i++) {
			int x = Integer.parseInt(br.readLine());
			if (x == 0) {
				if (pq.isEmpty())
					sb.append(0).append("\n");
				else
					sb.append(pq.poll()).append("\n");
			} else {
				pq.add(x);
			}
		}
		System.out.println(sb);
	}

}
