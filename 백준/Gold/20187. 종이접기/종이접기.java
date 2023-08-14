import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main { // 클래스 시작
	static int[][] paper;
	static int[][] answer;

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader((new InputStreamReader(System.in))); // 입력을 받기 위한 BufferedReader 객체 생성
		StringBuilder sb = new StringBuilder(); // 출력을 한번에 하기위한 StringBuilder 객체 생성

		// 입력 시작
		int k = Integer.parseInt(br.readLine()); // 손수건의 크기 2^k
		int[] arr = new int[4]; // 접는 방법의 횟수
		Stack<Character> stack = new Stack<Character>(); // 손수건을 접는 순서를 나타내는 정보 2k
		StringTokenizer st = new StringTokenizer(br.readLine()); // 두번째 줄의 입력을 공백으로 구분
		for (int i = 0; i < 2 * k; i++) // 2k 만큼 순서대로
			stack.push(st.nextToken().charAt(0)); // stack에 정보를 입력한다
		int h = Integer.parseInt(br.readLine()); // 구멍 뚫는 위치 h

		// 접힌 종이에 구멍을 뚫고 역순으로 펼치면서 구멍을 복사한다
		int length = 1 << k; // 종이 한 변의 길이
		paper = new int[length * 2 - 1][length * 2 - 1]; // 2^(k+1)-1의 길이를 가진 종이 배열
		answer = new int[length][length]; // 2^k의 길이를 가진 정답 배열

		int rr = length - 1; // 오른쪽 끝의 세로좌표
		int rc = length - 1; // 오른쪽 끝의 가로좌표
		int r_depth = 0; // 세로 반복 횟수(R, L에서 증가)
		int c_depth = 0; // 가로 반복 횟수(D, U에서 증가)
		int nr = rr; // 현재의 세로좌표
		int nc = rc; // 현재의 가로좌표
		paper[rr][rc] = h; // 첫 구멍 입력

		while (true) { // (0,0)에 도착할 때 까지 반복
			char fold = stack.pop(); // 스택에서 접는 방법을 뺀다

			if (fold == 'D') { // D일때
				for (int j = 0; j < 1 << c_depth; j++, nc--) {
					for (int i = r_depth + 1; i > 0; i--, nr--) {
						if (paper[nr][nc] == 0) {
							paper[nr - (1 << i) + 1][nc] = 2;
						}
						if (paper[nr][nc] == 1) {
							paper[nr - (1 << i) + 1][nc] = 3;
						}
						if (paper[nr][nc] == 2) {
							paper[nr - (1 << i) + 1][nc] = 0;
						}
						if (paper[nr][nc] == 3) {
							paper[nr - (1 << i) + 1][nc] = 1;
						}
					}
					nr = rr;
				}
				nc = rc;
				r_depth++;
			} else if (fold == 'U') { // U일때
				for (int j = 0; j < 1 << c_depth; j++, nc--) {
					for (int i = 1; i <= r_depth + 1; i++, nr--) {
						if (paper[nr][nc] == 0) {
							paper[nr + (1 << i) - 1][nc] = 2;
						}
						if (paper[nr][nc] == 1) {
							paper[nr + (1 << i) - 1][nc] = 3;
						}
						if (paper[nr][nc] == 2) {
							paper[nr + (1 << i) - 1][nc] = 0;
						}
						if (paper[nr][nc] == 3) {
							paper[nr + (1 << i) - 1][nc] = 1;
						}
					}
					nr = rr;
				}
				rr = rr + (1 << r_depth);
				nr = rr;
				nc = rc;
				r_depth++;
			} else if (fold == 'R') { // R일때
				for (int j = 0; j < 1 << r_depth; j++, nr--) {
					for (int i = c_depth + 1; i > 0; i--, nc--) {
						if (paper[nr][nc] == 0) {
							paper[nr][nc - (1 << i) + 1] = 1;
						}
						if (paper[nr][nc] == 1) {
							paper[nr][nc - (1 << i) + 1] = 0;
						}
						if (paper[nr][nc] == 2) {
							paper[nr][nc - (1 << i) + 1] = 3;
						}
						if (paper[nr][nc] == 3) {
							paper[nr][nc - (1 << i) + 1] = 2;
						}
					}
					nc = rc;
				}
				nr = rr;
				c_depth++;
			} else { // L일때
				for (int j = 0; j < 1 << r_depth; j++, nr--) {
					for (int i = 1; i <= c_depth + 1; i++, nc--) {
						if (paper[nr][nc] == 0) {
							paper[nr][nc + (1 << i) - 1] = 1;
						}
						if (paper[nr][nc] == 1) {
							paper[nr][nc + (1 << i) - 1] = 0;
						}
						if (paper[nr][nc] == 2) {
							paper[nr][nc + (1 << i) - 1] = 3;
						}
						if (paper[nr][nc] == 3) {
							paper[nr][nc + (1 << i) - 1] = 2;
						}
					}
					nc = rc;
				}
				rc = rc + (1 << c_depth);
				nc = rc;
				nr = rr;
				c_depth++;
			}
			if (stack.isEmpty())
				break;
		}

		for (int i = rr - (1 << r_depth) + 1; i <= rr; i++) {
			for (int j = rc - (1 << c_depth) + 1; j <= rc; j++)
				sb.append(paper[i][j]).append(" ");
			sb.append("\n");
		}
		System.out.println(sb); // 저장된 문자열 sb 출력
	} // 메인 종료
} // 클래스 종료
