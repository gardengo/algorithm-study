import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {// 클래스 시작

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader((new InputStreamReader(System.in))); // 입력을 받기 위한 BufferedReader 객체 생성

		// 입력 시작
		StringTokenizer st = new StringTokenizer(br.readLine()); // 한 줄의 입력을 받아서 공백으로 나누기
		int N = Integer.parseInt(st.nextToken()); // 배열의 크기 2^N
		int r = Integer.parseInt(st.nextToken()); // r행
		int c = Integer.parseInt(st.nextToken()); // c열

		int answer = findZ(N, r, c, 0);
		System.out.println(answer);
	}

	static int findZ(int N, int r, int c, int answer) {
		if (N == 0)
			return answer;

		int length = 1 << N; // 한 변의 길이
		if (length / 2 > r && length / 2 > c) // 1사분면
			return findZ(N - 1, r, c, answer);
		else if (length / 2 > r && length / 2 <= c) // 2사분면
			return findZ(N - 1, r, c - length / 2, answer + (1 << (N - 1) << (N - 1)));
		else if (length / 2 <= r && length / 2 > c) // 3사분면
			return findZ(N - 1, r - length / 2, c, answer + (1 << (N - 1) << (N - 1)) * 2);
		else // 4사분면
			return findZ(N - 1, r - length / 2, c - length / 2, answer + (1 << (N - 1) << (N - 1)) * 3);
	}

}
