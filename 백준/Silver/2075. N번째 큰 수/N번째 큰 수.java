import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 우선순위 큐를 int의 역순으로 정렬한다
		Queue<Integer> que = new PriorityQueue<Integer>(Collections.reverseOrder());
		// 표를 입력받으면 큐에 넣는다
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				que.offer(Integer.parseInt(st.nextToken()));
		}
		// 큐에서 N-1개를 빼내면 다음 순서는 N번째 수가 된다
		for (int i = 0; i < N - 1; i++)
			que.poll();
		System.out.println(que.poll());
	}
}
