import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution{

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 T 입력

		for (int test_case = 1; test_case <= T; test_case++) { // 테스트 케이스 만큼 반복

			StringTokenizer st = new StringTokenizer(br.readLine()); // N,M 입력
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine()); // Ai 입력
			int[] Ai = new int[N];
			for (int i = 0; i < N; i++)
				Ai[i] = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine()); // Bj 입력
			int[] Bj = new int[M];
			for (int j = 0; j < M; j++)
				Bj[j] = Integer.parseInt(st.nextToken());

			int max = Integer.MIN_VALUE;
			for (int k = 0; k <= Math.abs(M - N); k++) { // 0부터 N, M의 차이까지 이동하며 연산
				int sum = 0;
				for (int i = 0; i < Math.min(N, M); i++) { // N, M의 최소값까지 곱하고 더하기
					if (N < M) { // N이 작을 때 Bj에 k를 더해줘서 Ai를 이동시킨 효과
						sum += Ai[i] * Bj[i + k];
					} else { // M이 작을 때 Ai에 k를 더해줘서 Bj를 이동시킨 효과
						sum += Ai[i + k] * Bj[i];
					}
				}
				max = max > sum ? max : sum; // 이전ㄹㄴ max와 이번의 sum 비교
			}
			System.out.println("#" + test_case + " " + max);
		}
	}
}
