import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < N; i++)
			list.add(Integer.parseInt(br.readLine()));
		Collections.sort(list);

		int dif = 2000000000;
		int i = 0;
		int j = 1;
		for (; i < N; i++) {
			for (; j < N; j++) {
				if (list.get(j) - list.get(i) >= dif)
					break;
				if (list.get(j) - list.get(i) >= M && list.get(j) - list.get(i) < dif) {
					dif = list.get(j) - list.get(i);
					if (dif == M) {
						System.out.println(dif);
						return;
					}
					break;
				}
			}
		}
		System.out.println(dif);
	}

}
