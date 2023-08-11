import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// 메모리 11484kb 시간 80ms
public class Main { // 클래스 시작
	private static boolean[] visited = new boolean[9]; // 조합을 만들기 위해 방문한 지점을 표시
	private static int sum; // 모자에 적힌 숫자의 합을 계산하기위한 변수
	private static List<Integer> answer = new ArrayList<Integer>(); // 정답이 되는 리스트
	private static int[] hat = new int[9]; // 아홉 난쟁이의 모자에 적힌 수

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위한 BufferedReader 객체 생성
		StringBuilder sb = new StringBuilder(); // 출력을 위한 StringBuilder 객체 생성
		for (int i = 0; i < 9; i++) { // 9번 반복하며
			hat[i] = Integer.parseInt(br.readLine()); // 모자에 적힌 수를 입력 받는다
			sum += hat[i]; // 아홉 난쟁이의 숫자를 모두 더한다
		}

		combination(0, 0, 9, 2); // 조합 시작
		for (int i = 0; i < 7; i++) // 일곱 난쟁이의 숫자를 순서대로
			System.out.println(answer.get(i)); // println으로 출력한다
	} // 메인 종료

	private static void combination(int calc, int start, int n, int r) { // 9명 중 2명을 골라내는 조합
		if (r == 0) { // 2명을 모두 골랐으면
			if (sum - calc == 100) { // 전체 합과 2명의 합의 차이가 100이면 범인
				for (int i = 0; i < n; i++) // 0부터 9까지 돌면서
					if (!visited[i]) // 방문하지 않은 모자(진짜)를
						answer.add(hat[i]); // 정답 리스트에 추가한다
			}
			return; // 리턴한다
		}

		for (int i = start; i < n; i++) { // 0부터 9까까지 돌면서 조합한다
			visited[i] = true; // 방문한 모자는 표시하고
			calc += hat[i]; // 그 숫자를 더해준다
			combination(calc, i + 1, n, r - 1); // 더한 숫자 다음부터 탐색한다
			visited[i] = false; // 재귀가 풀리면 다시 false로 바꾸고
			calc -= hat[i]; // 더한 숫자로 빼준다
		}

	}

} // 클래스 종료
