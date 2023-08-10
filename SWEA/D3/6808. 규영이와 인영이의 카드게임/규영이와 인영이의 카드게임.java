import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 메모리 20,956kb 실행시간 3,551ms
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
		// idx:승부를 위한 인덱스, output:조합한 인영이의 카드 배열, depth:조합에 사용되는 변수, n:인영이의 카드 수, r:조합에
		// 사용할 카드 수
		if (depth == r) { // r개의 카드를 다 조합하면 게임 시작
			gyuPoint = 0; // 규영이의 포인트
			inPoint = 0; // 인영이의 포인트
			while (idx < 9) { // 0부터 9까지 탐색
				if (gyuCard[idx] > output[idx]) { // 규영이의 카드가 더 크면
					gyuPoint += gyuCard[idx] + output[idx]; // 규영이의 포인트 +
				} else { // 인영이의 카드가 더 크면
					inPoint += gyuCard[idx] + output[idx]; // 인영이의 포인트 +
				}
				if (gyuPoint >= 86) { // 규영이의 포인트와 인영이의 포인트 차이가 남은 점수를 넘으면
					gyuWin++; // 규영이가 이긴다
					return; // 이후 탐색 x
				}
				if (inPoint >= 86) // 인영이의 포인트와 규영이의 포인트 차이가 남은 점수를 넘으면
					return; // 인영이가 이기므로 이후 탐색 x
				idx++; // 다음 인덱스 탐색을 위해 +
			}
			if (gyuPoint > inPoint) // 게임 종료 후 규영이의 점수가 더 높으면
				gyuWin++; // 규영이가 이긴다
			return; // 게임 종료
		}

		for (int i = 0; i < n; i++) { // 인영이의 카드 수 만큼 반복
			if (visited[i] != true) { // 사용하지 않은 카드면
				visited[i] = true; // 사용하고
				output[depth] = inCard[i]; // 결과 배열에 추가한다
				game(idx, output, depth + 1, n, r); // depth를 1 늘려서 다음 범위 탐색
				visited[i] = false; // 재귀가 풀리면 다시 false로
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
