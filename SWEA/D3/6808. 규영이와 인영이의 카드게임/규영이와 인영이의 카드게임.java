import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	private static int total = 362880; // 전체 게임의 수
	private static boolean[] card, visited;
	private static int[] gyuCard, inCard, inCardPerm;
	private static int gyuWin, gyuPoint, inPoint;

	public static void main(String[] args) throws Exception { // main 메소드, 입출력 예외를 throws
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위한 BufferedReader 객체 생성
		StringBuilder sb = new StringBuilder(); // 출력을 위한 StringBuilder 객체 생성
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 T

		for (int test_case = 1; test_case <= T; test_case++) { // 테스트 케이스 만큼 반복
			card = new boolean[19]; // 1부터 18까지의 카드
			StringTokenizer st = new StringTokenizer(br.readLine()); // 규영이가 가진 카드
			gyuCard = new int[9]; // 규영이가 내는 카드를 순서대로 담은 배열
			inCard = new int[9]; // 인영이가 가진 카드
			inCardPerm = new int[9]; // 인영이의 카드 조합
			for (int i = 0; i < 9; i++) {
				gyuCard[i] = Integer.parseInt(st.nextToken()); // 카드를 순서대로 담는다
				card[gyuCard[i]] = true; // 규영이가 가진 카드를 표시
			}
			inCardReset(); // 인영이의 카드를 초기화 해준다
			gyuWin = 0; // 규영이의 승리 횟수를 초기화 해준다
			visited = new boolean[9]; // 인영이의 카드를 조합하기 위한 배열
			game(0, inCardPerm, 0, 9, 9);
			sb.append("#").append(test_case).append(" ").append(gyuWin).append(" ").append(total - gyuWin).append("\n");
		}
		System.out.println(sb);
	}

	private static void game(int idx, int[] output, int depth, int n, int r) {
		if (depth == r) {
			gyuPoint = 0;
			inPoint = 0;
			while (idx < 9) {
				if (gyuCard[idx] > output[idx]) {
					gyuPoint += gyuCard[idx] + output[idx];
				} else {
					inPoint += gyuCard[idx] + output[idx];
				}
				if (gyuPoint >= 86) {
					gyuWin++;
					return;
				}
				if (inPoint >= 86)
					return;
				idx++;
			}
			if (gyuPoint > inPoint)
				gyuWin++;
			return;
		}

		for (int i = 0; i < n; i++) {
			if (visited[i] != true) {
				visited[i] = true;
				output[depth] = inCard[i];
				game(idx, output, depth + 1, n, r);
				visited[i] = false;
			}
		}
	}

	private static void inCardReset() { // 인영이의 카드를 초기화
		int index = 0;
		for (int i = 1; i <= 18; i++) { // 1부터 18까지 돌면서
			if (!card[i]) // false면 인영이의 카드이므로
				inCard[index++] = i; // 추가한다
		}
	}
}
