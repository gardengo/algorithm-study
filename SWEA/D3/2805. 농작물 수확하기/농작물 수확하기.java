import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {
	private static int[][] farm;
	private static int N;
	private static int revenue;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 T 입력
		for (int test_case = 1; test_case <= T; test_case++) { // 테스트 케이스 만큼 반복
			N = Integer.parseInt(br.readLine()); // 농장의 크기 N 입력
			farm = new int[N][N]; // 농장 객체 생성
			for (int i = 0; i < N; i++) { // 농장 내 농작물의 가치 입력
				char[] arr = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					farm[i][j] = arr[j] - '0';
				}
			}

			revenue = 0; // 수익 초기화
			harvest(); // 수익 계산
			System.out.println("#" + test_case + " " + revenue); // 테스트 케이스 별 수익 출력
		}
	}

	private static void harvest() {
		int lx = N / 2, rx = N / 2, y = 0;

		
		while (y <= N / 2) {
			if (y == N / 2) {
				for (int i = lx; i <= rx; i++) {
					revenue += farm[y][i];
				}
				break;
			}
			for (int i = lx; i <= rx; i++) {// 위아래 삼각형 더하기
				revenue += farm[y][i];
				revenue += farm[N - y - 1][i];
			}
			y++;
			lx--;
			rx++;
		}

	}
}
