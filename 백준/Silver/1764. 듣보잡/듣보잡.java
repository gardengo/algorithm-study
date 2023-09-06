import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 듣도 못한 사람의 수 (50만 이하)
		int M = Integer.parseInt(st.nextToken()); // 보도 못한 사람의 수 (50만 이하)
		Set<String> hear = new HashSet<String>();
		PriorityQueue<String> pq = new PriorityQueue<String>(); // 사전순 정렬
		int answer = 0;
		for (int i = 0; i < N; i++)
			hear.add(br.readLine());
		for (int i = 0; i < M; i++) {
			String eye = br.readLine();
			if (hear.contains(eye)) {
				answer++;
				pq.offer(eye);
			}
		}
		sb.append(answer).append("\n");
		while (!pq.isEmpty())
			sb.append(pq.poll()).append("\n");
		System.out.println(sb);
	}

}
