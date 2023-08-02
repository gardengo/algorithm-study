import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	private static int N, M;
	private static char[] answer;
	private static boolean[] isSelected;
	private static int check;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		isSelected = new boolean[N]; // 중복을 제거하기 위한 배열
		answer = new char[M * 2]; // 수열을 저장하는 배열, 공백까지 저장하기 위해 2배로 크기 설정
		for (int i = 0; i < M; i++) // 홀수 index를 공백으로 설정
			answer[i * 2 + 1] = ' ';
		answer[M * 2 - 1] = '\n';

		recursion(0);
		System.out.println(sb);

	}

	private static void recursion(int num) {
		if (num == M) { // M번 실행 후 리턴
			sb.append(answer);
			return;
		}

		for (int i = check; i < N; i++) { // 이전에 선택된 숫자 이후부터 탐색
			if (isSelected[i]) // 이미 선택된 숫자는 넘어가기
				continue;
			answer[num * 2] = (char) (i + 1 + '0');
			isSelected[i] = true;
			check = i;

			recursion(num + 1);
			isSelected[i] = false;
			check = 0;
		}
	}
}
