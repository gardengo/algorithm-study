import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { // 클래스 시작
	static int answer;

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader((new InputStreamReader(System.in))); // 입력을 받기 위한 BufferedReader 객체 생성
		int N = Integer.parseInt(br.readLine()); // 이닝 수 N
		int[][] result = new int[N][9]; // 각 이닝에서 얻는 결과
		for (int i = 0; i < N; i++) { // N번의 이닝
			StringTokenizer st = new StringTokenizer(br.readLine()); // 한 줄을 입력받아 공백으로 나누기
			for (int j = 0; j < 9; j++) // 이닝에서 얻는 결과
				result[i][j] = Integer.parseInt(st.nextToken()); // 한 선수의 결과
		} // 입력 종료

		int[] tmp = new int[N]; // 자리를 바꾸기 위한 임시 변수
		for (int i = 0; i < N; i++) { // n번째 이닝까지 바꾼다
			tmp[i] = result[i][0]; // 1번 선수를 임시로 저장하고
			result[i][0] = result[i][3]; // 1번 자리에 3번 선수가 들어온다
			result[i][3] = tmp[i]; // 3번 자리에 1번 선수가 들어간다
		}
		boolean[] visited = new boolean[9];
		int[][] output = new int[N][9];
		for (int i = 0; i < N; i++) // N이닝 동안
			output[i][3] = result[i][3]; // 4번 타자는 정해져있다
		visited[3] = true; // 4번 타자는 정해졌으므로 true

		permutation(N, 0, result, output, visited); // 선수의 순서를 순열로
		System.out.println(answer); // 정답 출력
	} // 메인 종료

	public static void permutation(int n, int depth, int[][] result, int[][] output, boolean[] visited) {
		if (depth == 3)
			depth += 1;

		if (depth == 9) {
			int score = simulation(output, n);
			answer = score > answer ? score : answer;
			return;
		}

		for (int i = 0; i < 9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				for (int j = 0; j < n; j++)
					output[j][depth] = result[j][i];
				permutation(n, depth + 1, result, output, visited);
				visited[i] = false;
			}
		}
	}

	private static int simulation(int[][] output, int n) {
		int score = 0; // 점수
		int outCnt = 0; // 아웃 횟수
		int base = 0; // 베이스에 나가있는 선수

		int i = 0;
		int j = 0;
		while (true) {
			j %= 9;
			if (output[i][j] == 0) {
				outCnt++;
			} else if (output[i][j] == 1) {
				base += 1;
				base = base << 1;
				if (base >= 16) { // 16을 넘으면 1명이 홈 도착
					base -= 16;
					score++;
				}
			} else if (output[i][j] == 2) {
				base += 1;
				base = base << 2;
				if (base >= 32) { // 32를 넘으면 1명이 홈 도착
					base -= 32;
					score++;
				}
				if (base >= 16) { // 16을 넘으면 1명이 홈 도착
					base -= 16;
					score++;
				}
			} else if (output[i][j] == 3) {
				base += 1;
				base = base << 3;
				if (base >= 64) { // 64를 넘으면 1명이 홈 도착
					base -= 64;
					score++;
				}
				if (base >= 32) { // 32를 넘으면 1명이 홈 도착
					base -= 32;
					score++;
				}
				if (base >= 16) { // 16을 넘으면 1명이 홈 도착
					base -= 16;
					score++;
				}
			} else if (output[i][j] == 4) {
				base += 1;
				base = base << 4;
				if (base >= 128) { // 128을 넘으면 1명이 홈 도착
					base -= 128;
					score++;
				}
				if (base >= 64) { // 64를 넘으면 1명이 홈 도착
					base -= 64;
					score++;
				}
				if (base >= 32) { // 32를 넘으면 1명이 홈 도착
					base -= 32;
					score++;
				}
				if (base >= 16) { // 16을 넘으면 1명이 홈 도착
					base -= 16;
					score++;
				}
			}
			if (outCnt == 3) {
				base = 0;
				outCnt = 0;
				i++;
				if (i == n)
					break;
			}
			j++;
		}
		return score;
	}
} // 클래스 종료
