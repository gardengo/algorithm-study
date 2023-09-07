import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수(최대 100)

		root: for (int test = 1; test <= T; test++) {
			String p = br.readLine(); // 수행할 함수(최대 10만)
			int n = Integer.parseInt(br.readLine()); // 배열에 들어있는 수의 개수(최대 10만)
			int[] arr = new int[n];
			String str = br.readLine().replace("[", "").replace("]", "");
			StringTokenizer st = new StringTokenizer(str, ",");
			for (int i = 0; i < n; i++)
				arr[i] = Integer.parseInt(st.nextToken());

			boolean status = true; // true: 정방향, false: 역방향
			int lt = 0; // 왼쪽 끝
			int rt = n - 1; // 오른쪽 끝

			int length = p.length();
			for (int i = 0; i < length; i++) {
				char com = p.charAt(i);
				if (com == 'R') { // 뒤집기
					status = !status;
				} else { // 버리기
					if (lt > rt) {
						sb.append("error\n");
						continue root;
					}
					if (status) // 정방향
						lt++;
					else // 역방향
						rt--;
				}
			}

			sb.append("[");
			if (status) {
				for (int i = lt; i <= rt; i++) {
					sb.append(arr[i]);
					if (i == rt)
						continue;
					sb.append(",");
				}
			} else {
				for (int i = rt; i >= lt; i--) {
					sb.append(arr[i]);
					if (i == lt)
						continue;
					sb.append(",");
				}
			}
			sb.append("]\n");
		}
		System.out.println(sb);
	}

}
