import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
	private static final int PRIME1 = 31;
	private static final int PRIME2 = 37;
	private static final int PRIME3 = 41;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		// 라빈-카프 알고리즘을 사용해서 풀어보자
		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			String B = br.readLine(); // 책의 내용
			String S = br.readLine(); // 찾고자 하는 단어
			int count = 0;

			int bLength = B.length(); // B의 길이
			int sLength = S.length(); // S의 길이

			int bHash1 = 0;
			int bHash2 = 0;
			int bHash3 = 0;

			int sHash1 = 0;
			int sHash2 = 0;
			int sHash3 = 0;

			int power1 = 1;
			int power2 = 1;
			int power3 = 1;

			// 한 칸씩 이동하며 hash 값이 같은 지 확인
			for (int i = 0; i <= bLength - sLength; i++) {
				if (i == 0) { // 0일 때는 초기값 세팅 필요
					for (int j = 0; j < sLength; j++) {
						bHash1 += B.charAt(sLength - 1 - j) * power1;
						sHash1 += S.charAt(sLength - 1 - j) * power1;

						bHash2 += B.charAt(sLength - 1 - j) * power2;
						sHash2 += S.charAt(sLength - 1 - j) * power2;

						bHash3 += B.charAt(sLength - 1 - j) * power3;
						sHash3 += S.charAt(sLength - 1 - j) * power3;

						if (j != sLength - 1) {
							power1 *= PRIME1;
							power2 *= PRIME2;
							power3 *= PRIME3;
						}
					}
				} else { // 0 이후는 가장 앞 문자 빼주고 이전 값에 power 곱한 후 뒤에 문자 더하기
					bHash1 = PRIME1 * (bHash1 - B.charAt(i - 1) * power1) + B.charAt(i + sLength - 1);
					bHash2 = PRIME2 * (bHash2 - B.charAt(i - 1) * power2) + B.charAt(i + sLength - 1);
					bHash3 = PRIME3 * (bHash3 - B.charAt(i - 1) * power3) + B.charAt(i + sLength - 1);
				}

				if (bHash1 == sHash1 && bHash2 == sHash2 && bHash3 == sHash3)
					count++;
			}

			sb.append(count);
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
