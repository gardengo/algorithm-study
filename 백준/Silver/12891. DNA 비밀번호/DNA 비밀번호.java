import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken()); // DNA 문자열 길이
		int P = Integer.parseInt(st.nextToken()); // 비밀번호 문자열 길이
		String DNA = br.readLine(); // DNA 문자열
		int[] ACGT = new int[4];
		st = new StringTokenizer(br.readLine()); // A,C,G,T의 최소 개수
		for (int i = 0; i < 4; i++) {
			ACGT[i] = Integer.parseInt(st.nextToken());
		}

		int answer = 0;
		int lt = 0;
		int rt = P - 1;
		int[] cond = new int[4];

		// index 0부터 시작하는 최초 pw 문자열 저장
		for (int i = lt; i <= rt; i++) {
			if (DNA.charAt(i) == 'A') {
				cond[0]++;
			} else if (DNA.charAt(i) == 'C') {
				cond[1]++;
			} else if (DNA.charAt(i) == 'G') {
				cond[2]++;
			} else if (DNA.charAt(i) == 'T') {
				cond[3]++;
			}
		}

		if (cond[0] >= ACGT[0] && cond[1] >= ACGT[1] && cond[2] >= ACGT[2] && cond[3] >= ACGT[3])
			answer++;

		while (rt < S - 1) {
			// lt에 있던 문자 제거
			if (DNA.charAt(lt) == 'A') {
				cond[0]--;
			} else if (DNA.charAt(lt) == 'C') {
				cond[1]--;
			} else if (DNA.charAt(lt) == 'G') {
				cond[2]--;
			} else if (DNA.charAt(lt) == 'T') {
				cond[3]--;
			}
			lt++;
			rt++;
			// rt+1 문자 추가
			if (DNA.charAt(rt) == 'A') {
				cond[0]++;
			} else if (DNA.charAt(rt) == 'C') {
				cond[1]++;
			} else if (DNA.charAt(rt) == 'G') {
				cond[2]++;
			} else if (DNA.charAt(rt) == 'T') {
				cond[3]++;
			}

			if (cond[0] >= ACGT[0] && cond[1] >= ACGT[1] && cond[2] >= ACGT[2] && cond[3] >= ACGT[3])
				answer++;
		}

		System.out.println(answer);
	}

}
