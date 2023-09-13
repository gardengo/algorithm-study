import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 사람의 수(50이하)
		int M = Integer.parseInt(st.nextToken()); // 파티의 수(50이하)
		st = new StringTokenizer(br.readLine());
		int real_num = Integer.parseInt(st.nextToken()); // 진실을 아는 사람의 수(50이하)
		boolean[] real = new boolean[N + 1]; // 진실을 알고 있는지
		for (int i = 0; i < real_num; i++)
			real[Integer.parseInt(st.nextToken())] = true;

		List<int[]> list = new ArrayList<int[]>();
		for (int i = 0; i < M; i++) {
			boolean exist = false; // 파티에 아는 사람이 있는가
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken()); // 파티에 오는 사람 수
			int[] people = new int[num];
			for (int j = 0; j < num; j++) {
				people[j] = Integer.parseInt(st.nextToken());
				if (real[people[j]])
					exist = true;
			}

			if (exist) { // 진실을 알고 있는 사람이 있으면 파티에 온 사람들이 모두 true
				for (int j = 0; j < num; j++)
					real[people[j]] = true;
			} else { // 없으면 일단 리스트에 추가
				list.add(people);
			}
		}

		int answer = 0;
		root: while (true) {
			int cnt = 0;

			for (int i = 0; i < list.size(); i++) {
				boolean exist = false; // 파티에 아는 사람이 있는가
				int[] cur = list.get(i); // 현재 파티의 사람들
				for (int j = 0; j < cur.length; j++) {
					if (real[cur[j]]) {
						exist = true;
						break;
					}
					if (j == cur.length - 1) // 진실을 아는 사람이 없으면 cnt+1
						cnt++;
				}

				if (exist) { // 진실을 알고 있는 사람이 있으면 파티에 온 사람들이 모두 true
					for (int j = 0; j < cur.length; j++)
						real[cur[j]] = true;
					list.remove(i);
					continue root;
				}
			}

			// 여기에 도착했다면 더 이상 진실을 알고 있는 사람이 리스트에 없는 것
			answer = cnt;
			break;
		}
		System.out.println(answer);
	}

}
