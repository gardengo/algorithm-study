import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main { // 클래스 시작
	static StringBuilder sb = new StringBuilder(); // 출력을 위한 StringBuilder 객체 생성
	static char[] ch;
	static int L;

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader((new InputStreamReader(System.in))); // 입력을 받기 위한 BufferedReader 객체 생성
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫 줄을 입력받아 공백으로 나누기
		L = Integer.parseInt(st.nextToken()); // 암호의 길이 L
		int C = Integer.parseInt(st.nextToken()); // 문자의 종류 C
		ch = new char[C]; // 문자를 저장하는 배열
		st = new StringTokenizer(br.readLine()); // 한 줄을 입력받아 공백으로 나눈다
		for (int i = 0; i < C; i++) { // C개의 문자로 나눠서
			ch[i] = st.nextToken().charAt(0);
		} // 입력 종료

		Arrays.sort(ch); // 오름차순으로 정렬한다
		StringBuilder sbp = new StringBuilder(); // 조합에 사용할 StringBuilder
		permutation(C, L, 0, sbp);
		System.out.println(sb);
	} // 메인 종료

	private static void permutation(int n, int r, int idx, StringBuilder sbp) {
		if (r == 0) {
			int cons = 0; // 자음의 개수
			int gath = 0; // 모음의 개수
			for (int i = 0; i < L; i++) {
				if (sbp.charAt(i) == 'a' || sbp.charAt(i) == 'e' || sbp.charAt(i) == 'i' || sbp.charAt(i) == 'o'
						|| sbp.charAt(i) == 'u')
					gath++;
				else
					cons++;
			}
			if (cons >= 2 && gath >= 1)
				sb.append(sbp).append("\n");
			return;
		}

		for (int i = idx; i < n; i++) {
			sbp.append(ch[i]);
			permutation(n, r - 1, i + 1, sbp);
			sbp.deleteCharAt(sbp.length() - 1);
		}
	}

} // 클래스 종료
