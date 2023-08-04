import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 메모리 107,904kb, 실행시간 800ms
 * 완전탐색으로 한 행씩 바꿔가며 탐색
 * 최소값을 넘어가면 그 이후는 탐색하지 않는 방식으로 연산을 줄임
 */
public class Solution {
	private static int D, W, K, answer;
	private static int[][] film;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 T

		for (int test_case = 1; test_case <= T; test_case++) { // 테스트 케이스 만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken()); // 필름의 두께
			W = Integer.parseInt(st.nextToken()); // 가로크기
			K = Integer.parseInt(st.nextToken()); // 합격기준

			film = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					film[i][j] = Integer.parseInt(st.nextToken()); // 셀의 특성
				}
			}

			answer = Integer.MAX_VALUE;
			if (K == 1) { // K가 1이면 무조건 0, 연산을 줄이기 위해 따로 뺌
				answer = 0;
			} else {
				medicine(0, 0);
			}

			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			sb.append(answer);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void medicine(int idx, int count) {
		if (check()) { // 조건을 만족하면 최소값 갱신
			answer = Math.min(answer, count);
			return;
		}
		if (count >= answer) // 지금까지의 결과보다 크거나 같으면 바로 정지
			return;
		if (idx == D) { // 마지막 행까지 가면 리턴
			return;
		} else {
			int[] copy = new int[W];
			for (int i = 0; i < W; i++)
				copy[i] = film[idx][i];

			// idx행을 그대로 두고 다음 행 탐색
			medicine(idx + 1, count);

			// idx행을 A로 바꾸고 다음 행 탐색
			for (int i = 0; i < W; i++)
				film[idx][i] = 0;
			medicine(idx + 1, count + 1);

			// idx행을 B로 바꾸고 다음 행 탐색
			for (int i = 0; i < W; i++)
				film[idx][i] = 1;
			medicine(idx + 1, count + 1);

			// 수정한 film 행 원래대로
			for (int i = 0; i < W; i++)
				film[idx][i] = copy[i];
		}
	}

	private static boolean check() {
		for (int j = 0; j < W; j++) {
			int count = 1; // 연속된 셀의 수
			boolean condition = false; // 각 열이 조건을 만족하는가
			for (int i = 1; i < D; i++) {
				if (film[i][j] == film[i - 1][j]) { // 직전 행과 같으면 +1
					count++;
				} else { // 직전 행과 다르면 1로 초기화
					count = 1;
				}
				if (count == K) { // 조건을 만족하면 다음 열로 이동
					condition = true;
					break;
				}
			}
			if (condition == false) // 조건을 만족하지 않는 열이 있으면 나머지는 탐색 x
				return false;
		}
		return true; // for문을 모두 완전하게 수행하면 모든 열에 연속된 K개가 존재
	}

}
