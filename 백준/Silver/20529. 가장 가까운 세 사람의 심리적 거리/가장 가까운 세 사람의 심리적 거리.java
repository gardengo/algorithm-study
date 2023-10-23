
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int test = 1; test <= T; test++) {
			int N = Integer.parseInt(br.readLine());
			List<String> list = new ArrayList<String>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				list.add(st.nextToken());
			if (N > 32)
				sb.append(0).append("\n");
			else {
				Collections.sort(list);

				answer = 12;
				root: for (int i = 0; i < N - 2; i++) {
					String first = list.get(i);
					for (int j = i + 1; j < N - 1; j++) {
						String second = list.get(j);
						for (int k = j + 1; k < N; k++) {
							String third = list.get(k);
							calc(first, second, third);
							if (answer == 0)
								break root;
						}
					}
				}
				sb.append(answer).append("\n");
			}
		}
		System.out.println(sb);
	}

	public static void calc(String first, String second, String third) {
		int cnt = 0;

		for (int i = 0; i < 4; i++) {
			if (first.charAt(i) != second.charAt(i))
				cnt++;
			if (first.charAt(i) != third.charAt(i))
				cnt++;
			if (second.charAt(i) != third.charAt(i))
				cnt++;
		}

		answer = cnt < answer ? cnt : answer;
	}

}
