import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 종료시간, 시작시간으로 정렬하는 타임테이블 생성
		PriorityQueue<int[]> table = new PriorityQueue<int[]>((a, b) -> {
			if (a[1] == b[1])
				return Integer.compare(a[0], b[0]);
			else
				return Integer.compare(a[1], b[1]);
		});

		// 타임테이블에 시작시간과 종료시간 입력
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			table.add(new int[] { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) });
		}

		int answer = 0;
		int time = 0;

		// 정렬된 타임테이블에서 하나씩 빼서 회의를 진행할 수 있으면 진행
		while (!table.isEmpty()) {
			int[] cur = table.poll();
			if (time <= cur[0]) {
				answer++;
				time = cur[1];
			}
		}
		System.out.println(answer);
	}

}
