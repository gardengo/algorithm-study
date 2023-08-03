import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static StringBuilder sb = new StringBuilder();
	private static int[][] ingredient;
	private static int maxScore;
	private static int N, L;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 T

		for (int test_case = 1; test_case <= T; test_case++) { // 테스트 케이스 만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 재료의 수
			L = Integer.parseInt(st.nextToken()); // 제한 칼로리
			ingredient = new int[N][2]; // 재료의 배열

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				ingredient[i][0] = Integer.parseInt(st.nextToken()); // 맛에 대한 점수
				ingredient[i][1] = Integer.parseInt(st.nextToken()); // 칼로리
			}
            
			maxScore = 0;
			sb.append("#");
			sb.append(test_case);
			sb.append(" ");
			select(0, 0, 0);
			sb.append(maxScore);
			sb.append("\n");
		}
		System.out.println(sb);
	}

	private static void select(int i, int score, int cal) {
		if (cal > L) {
			return;
		} else {
			maxScore = Math.max(maxScore, score);
		}
		if (i == N)
			return;

		select(i + 1, score + ingredient[i][0], cal + ingredient[i][1]);
		select(i + 1, score, cal);
	}
}
