import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int N, foodA, foodB, min;
	private static int[][] table;
	private static boolean[] used;

	public static void main(String[] args) throws Exception { // main 메소드, 입출력 예외를 throws
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위한 BufferedReader 객체 생성
		StringBuilder sb = new StringBuilder(); // 출력을 위한 StringBuilder 객체 생성
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 T

		for (int test_case = 1; test_case <= T; test_case++) { // 테스트 케이스 만큼 반복
			N = Integer.parseInt(br.readLine()); // 식재료의 수
			table = new int[N][N]; // 식재료들 간의 시너지 테이블
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++)
					table[i][j] = Integer.parseInt(st.nextToken()); // 테이블에 시너지 입력
			}
			used = new boolean[N]; // A가 사용한 재료들

			min = 40000; // 시너지 합의 최대인 40000으로 초기화
			foodA = 0;
			foodB = 0;
			select(0, 0);
			sb.append("#").append(test_case).append(" ").append(min).append("\n");
		}
		System.out.println(sb);
	}

	private static void select(int use, int x) { // 음식A가 사용한 재료의 수 use, 식재료 번호 x
		if (use == N / 2) { // 사용한 재료가 N/2개가 되면
			foodA = 0;
			foodB = 0;
			for (int i = 0; i < N; i++) { // 식재료 전체를 돌면서
				if (used[i]) { // A가 사용한 것이면
					int j = i + 1; // i+1부터
					while (j < N) { // N까지 돌면서
						if (used[j]) // 사용된 식재료를 찾아서
							foodA += table[i][j] + table[j][i]; // 둘의 시너지를 더해준다
						j++; // N까지 다 확인해야 하므로 +1
					}
				} else { // B가 사용한 것이면
					int j = i + 1;
					while (j < N) {
						if (!used[j])
							foodB += table[i][j] + table[j][i];
						j++;
					}
				}
			}
			min = min > Math.abs(foodA - foodB) ? Math.abs(foodA - foodB) : min; // 두 음식 시너지의 차이와 min을 비교해서 더 작은 것을 저장
			return; // 멈춘다
		}
		if (x + 1 == N) { // x가 N까지 가면

			return; // 멈춘다
		}

		select(use, x + 1); // x를 사용하지 않은 경우
		used[x] = true;
		select(use + 1, x + 1); // x를 사용한 경우
		used[x] = false;
	}

}