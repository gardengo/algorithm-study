import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

// 메모리 kb 시간 ms
public class Main {
	// main이 아닌 다른 메소드에서 변수를 사용하기 위해 전역변수로 설정
	private static int N, M, R;
	private static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위한 BufferedReader 객체 생성
		StringBuilder sb = new StringBuilder(); // 출력을 위한 StringBuilder 객체 생성
		StringTokenizer st = new StringTokenizer(br.readLine()); // 한 줄의 입력을 공백으로 나누기 위해 StringTokenizer 객체 사용
		N = Integer.parseInt(st.nextToken()); // 배열의 세로 크기 N
		M = Integer.parseInt(st.nextToken()); // 배열의 가로 크기 M
		R = Integer.parseInt(st.nextToken()); // 연산의 수 R
		arr = new int[N][M]; // N*M 크기의 배열 생성
		for (int i = 0; i < N; i++) { // N개의 줄에서 배열의 원소 입력받기
			st = new StringTokenizer(br.readLine()); // 한 줄에 입력받은 M개의 원소를 공백으로 나눔
			for (int j = 0; j < M; j++) { // M개의 원소를 arr에 저장
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine()); // 수행해야 하는 연산 입력
		for (int i = 0; i < R; i++) { // 입력받은 연산을 순서대로 R번 수행
			calc(Integer.parseInt(st.nextToken())); // 연산 수행
		}
		/*
		 * [1:상하반전] [2:좌우반전] [3:오른쪽 90도 회전] [4:왼쪽 90도 회전] [5:4개의 부분 배열로 나누어 시계 방향으로 회전]
		 * [6.4개의 부분 배열로 나누어 반시계 방향으로 회전]
		 */

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j]).append(" "); // 연산이 끝난 배열을 StringBuilder에 순서대로 저장
			}
			sb.append("\n"); // M개의 입력을 받은 후 줄바꿈 추가
		}
		System.out.println(sb); // 저장한 StringBuilder 출력
	}

	private static void calc(int C) { // 매개변수로 C를 입력받는다
		int lt; // 반전에 사용하기 위한 왼쪽 포인터 변수
		int rt; // 반전에 사용하기 위한 오른쪽 포인터 변수
		Stack<Integer> stack; // 회전에 사용하기 위한 스택
		int[][] subArray = new int[N / 2][M / 2]; // 5,6번 연산에 사용하기 위한 부분 배열

		switch (C) { // C에 해당하는 연산을 수행
		case 1: // C가 1일 때
			lt = 0; // 가장 위쪽인 0부터 시작
			rt = N - 1; // 가장 아래쪽인 N-1부터 시작
			while (lt < rt) { // lt가 rt보다 작을때 두 행을 바꿔준다
				int[] temp = arr[lt].clone(); // 임시 행을 만들어 위쪽 행을 복사한다
				arr[lt] = arr[rt].clone(); // 아래쪽 행을 위로 복사한다
				arr[rt] = temp; // 아래쪽 행에 아까 만들어 둔 임시 행을 복사한다
				lt++; // 위쪽 포인터 +1
				rt--; // 아래쪽 포인터 -1
			}
			return; // 1회 연산이 끝났으므로 calc 메소드를 종료한다
		case 2: // C가 2일 때
			lt = 0; // 가장 왼쪽인 0부터 시작
			rt = M - 1; // 가장 오른쪽인 M-1부터 시작
			while (lt < rt) { // lt가 rt보다 작을때 두 행을 바꿔준다
				int[] temp = new int[N]; // 왼쪽 행을 저장하기 위한 임시 행
				for (int i = 0; i < N; i++)
					temp[i] = arr[i][lt]; // 임시 열에 왼쪽 열의 값을 넣는다
				for (int i = 0; i < N; i++)
					arr[i][lt] = arr[i][rt]; // 왼쪽 열에 오른쪽 열의 값을 넣는다
				for (int i = 0; i < N; i++)
					arr[i][rt] = temp[i]; // 오른쪽 열에 저장해둔 임시 열의 값을 넣는다
				lt++; // 왼쪽 포인터 +1
				rt--; // 오른쪽 포인터 +1
			}
			return; // 1회 연산이 끝났으므로 calc 메소드를 종료한다
		case 3: // C가 3일 때
			stack = new Stack<Integer>(); // 스택 초기화
			for (int j = M - 1; j >= 0; j--) // 가로가 M-1부터 0까지
				for (int i = 0; i < N; i++) // 세로가 0부터 N-1까지
					stack.push(arr[i][j]); // 스택에 순서대로 담는다
			N = arr[0].length; // N을 M으로 변경
			M = arr.length; // M을 N으로 변경
			arr = new int[N][M]; // 배열을 90도 돌렸기 때문에 행과 열의 수가 바뀐다
			for (int i = 0; i < N; i++) // 세로가 0부터 M-1까지
				for (int j = 0; j < M; j++) // 가로가 0부터 N-1까지
					arr[i][j] = stack.pop(); // 스택에서 하나씩 빼서 배열에 저장한다
			return; // 1회 연산이 끝났으므로 calc 메소드를 종료한다
		case 4: // C가 4일 때
			stack = new Stack<Integer>(); // 스택 초기화
			for (int j = 0; j < M; j++) // 가로가 0부터 M-1까지
				for (int i = N - 1; i >= 0; i--) // 세로가 N-1부터 0까지
					stack.push(arr[i][j]); // 스택에 순서대로 담는다
			N = arr[0].length; // N을 M으로 변경
			M = arr.length; // M을 N으로 변경
			arr = new int[N][M]; // 배열을 90도 돌렸기 때문에 행과 열의 수가 바뀐다
			for (int i = 0; i < N; i++) // 세로가 0부터 M-1까지
				for (int j = 0; j < M; j++) // 가로가 0부터 N-1까지
					arr[i][j] = stack.pop(); // 스택에서 하나씩 빼서 배열에 저장한다
			return; // 1회 연산이 끝났으므로 calc 메소드를 종료한다
		case 5: // C가 5일 때
			for (int i = 0; i < N / 2; i++)
				for (int j = 0; j < M / 2; j++)
					subArray[i][j] = arr[i][j]; // 1번 부분 배열을 subArray에 저장
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < M / 2; j++) {
					arr[i][j] = arr[i + N / 2][j]; // 1번 부분 배열을 4번으로 변경
					arr[i + N / 2][j] = arr[i + N / 2][j + M / 2]; // 4번 부분 배열을 3번으로 변경
					arr[i + N / 2][j + M / 2] = arr[i][j + M / 2]; // 3번 부분 배열을 2번으로 변경
					arr[i][j + M / 2] = subArray[i][j]; // 2번 부분 배열을 저장해둔 subArray로 변경
				}
			}
			return; // 1회 연산이 끝났으므로 calc 메소드를 종료한다
		case 6: // C가 6일 때
			for (int i = 0; i < N / 2; i++)
				for (int j = 0; j < M / 2; j++)
					subArray[i][j] = arr[i][j]; // 1번 부분 배열을 subArray에 저장
			for (int i = 0; i < N / 2; i++) {
				for (int j = 0; j < M / 2; j++) {
					arr[i][j] = arr[i][j + M / 2]; // 1번 부분 배열을 2번으로 변경
					arr[i][j + M / 2] = arr[i + N / 2][j + M / 2]; // 2번 부분 배열을 3번으로 변경
					arr[i + N / 2][j + M / 2] = arr[i + N / 2][j]; // 3번 부분 배열을 4번으로 변경
					arr[i + N / 2][j] = subArray[i][j]; // 4번 부분 배열을 저장해둔 subArray로 변경
				}
			}
			return; // 1회 연산이 끝났으므로 calc 메소드를 종료한다
		}
	}

}
