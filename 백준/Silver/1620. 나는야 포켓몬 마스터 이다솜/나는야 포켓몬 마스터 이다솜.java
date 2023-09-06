import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 포켓몬의 개수 (10만 이하)
		int M = Integer.parseInt(st.nextToken()); // 맞춰야 하는 문제의 개수 (10만 이하)
		HashMap<String, Integer> sBook = new HashMap<String, Integer>(); // 문자로 찾는 도감
		String[] nBook = new String[N + 1]; // 숫자로 찾는 도감
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			sBook.put(str, i);
			nBook[i] = str;
		}
		for (int i = 0; i < M; i++) {
			String quest = br.readLine(); // 맞춰야하는 문제
			if (Character.isDigit(quest.charAt(0))) { // 숫자라면
				int num = Integer.parseInt(quest);
				sb.append(nBook[num]).append("\n");
			} else { // 문자라면
				sb.append(sBook.get(quest)).append("\n");
			}
		}
		System.out.println(sb);
	}

}
