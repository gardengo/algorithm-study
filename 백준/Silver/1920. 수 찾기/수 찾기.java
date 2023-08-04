import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 자연수 N과 N개의 정수를 입력받아 set에 저장한다
		int N = Integer.parseInt(br.readLine());
		HashSet<Integer> set = new HashSet<Integer>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			set.add(Integer.parseInt(st.nextToken()));

		// 자연수 M과 M개의 정수를 입력받는다
		int M = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			if (set.contains(Integer.parseInt(st.nextToken()))) {
				sb.append(1);
			} else {
				sb.append(0);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
