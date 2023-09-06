import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>((a, b) -> Integer.compare(a[1], b[1]));
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++)
			pq.add(new int[] { i, Integer.parseInt(st.nextToken()) });

		int[] press = new int[N + 1]; // 압축된 좌표
		int[] front = new int[2]; // 같은 값을 처리하기 위해 앞의 값을 저장
		front[1] = Integer.MIN_VALUE;
		int idx = 0;

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			if (front[1] < cur[1]) {
				front[0] = idx++;
				front[1] = cur[1];
			}
			press[cur[0]] = front[0];
		}

		for (int i = 1; i <= N; i++)
			sb.append(press[i]).append(" ");
		System.out.println(sb);
	}

}
