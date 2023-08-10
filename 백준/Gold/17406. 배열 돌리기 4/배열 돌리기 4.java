import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	private static int N, M, K;
	private static int[][] A, copy, calc;
	private static int min = 5000; // 최소값 초기화
	private static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위한 BufferedReader 객체 생성
		StringTokenizer st = new StringTokenizer(br.readLine()); // 한 줄의 입력을 공백으로 나누기 위해 StringTokenizer 객체 사용
		N = Integer.parseInt(st.nextToken()); // 배열의 세로 길이
		M = Integer.parseInt(st.nextToken()); // 배열의 가로 길이
		K = Integer.parseInt(st.nextToken()); // 회전 연산의 개수
		A = new int[N][M]; // N*M 크기의 배열
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				A[i][j] = Integer.parseInt(st.nextToken()); // 배열의 값
		}
		calc = new int[K][3]; // 연산의 정보를 담은 배열
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			calc[i][0] = Integer.parseInt(st.nextToken()); // 회전 연산의 정보 r
			calc[i][1] = Integer.parseInt(st.nextToken()); // 회전 연산의 정보 c
			calc[i][2] = Integer.parseInt(st.nextToken()); // 회전 연산의 정보 s
		}
		visited = new boolean[K];
		copy = A.clone(); // copy 배열 생성
		findMin(0, A.clone()); // 최소값 찾기
		System.out.println(min);
	}

	private static void findMin(int depth, int[][] copy) {
		if (depth == K) { // K번 돌렸으면
			for (int i = 0; i < N; i++) { // N개의 행에서
				int sum = 0; // 합을 구하기 위한 변수
				for (int j = 0; j < M; j++) // M개의 원소를
					sum += copy[i][j]; // sum에 더해준다
				if (sum < min) // sum이 최소값보다 작으면
					min = sum; // 최소값을 갱신한다
			}
			return; // 재귀를 끝낸다
		}
		for (int i = 0; i < K; i++) { // K개로 순열을 만든다
			if (!visited[i]) { // 방문하지 않았으면
				visited[i] = true; // 방문하고
				rotate(copy, calc[i][0], calc[i][1], calc[i][2]); // 돌린다
				findMin(depth + 1, copy); // 재귀를 이용해 한 세트를 만든다
				visited[i] = false; // 재귀가 풀리면다시 false로 만들고
				reRotate(copy, calc[i][0], calc[i][1], calc[i][2]); // 반대로 돌린다
			}
		}
	}

	private static void rotate(int[][] copy, int r, int c, int s) { // r,c 기준으로 s조각 만큼 돌린다
		for (int k = s; k > 0; k--) { // s부터 1까지 반복
			List<Integer> list = new LinkedList<Integer>(); // 쪼갠 배열의 값을 list에 저장
			int i = r - k - 1; // 시작 지점의 세로 좌표
			int j = c - k - 1; // 시작 지점의 가로 좌표
			while (true) {
				list.add(copy[i][j]); // 리스트에 배열 조각의 원소를 넣는다
				if (i == r - k - 1 && j < c + k - 1) { // 위쪽 줄 추가
					j++; // 오른쪽으로 이동하면서
				} else if (j == c + k - 1 && i < r + k - 1) { // 오른쪽 줄 추가
					i++; // 아래로 이동하면서
				} else if (i == r + k - 1 && j > c - k - 1) { // 아래쪽 줄 추가
					j--; /// 왼쪽으로 이동하면서
				} else if (j == c - k - 1 && i > r - k - 1) { // 왼쪽 줄 추가
					i--; // 위로 이동하면서
				}
				if (i == r - k - 1 && j == c - k - 1) // 처음으로 돌아오면
					break; // 멈춘다
			} // 여기까지 list에 쪼갠 배열을 저장하는 코드
			list.add(0, list.get(list.size() - 1)); // 마지막 원소를 맨 앞에 추가
			list.remove(list.size() - 1); // 마지막 원소 제거

			int size = list.size(); // 리스트의 사이즈
			for (int l = 0; l < size; l++) { // 돌린 리스트를 원본 배열에 저장
				copy[i][j] = list.get(l); // 저장할때와 똑같은 순서로 덮어쓰기
				if (i == r - k - 1 && j < c + k - 1) { // 위쪽 줄
					j++; // 오른쪽으로 이동하면서
				} else if (j == c + k - 1 && i < r + k - 1) { // 오른쪽 줄
					i++; // 아래로 이동하면서
				} else if (i == r + k - 1 && j > c - k - 1) { // 아래쪽 줄
					j--; /// 왼쪽으로 이동하면서
				} else if (j == c - k - 1 && i > r - k - 1) { // 왼쪽 줄
					i--; // 위로 이동하면서
				}
			}
		}
	}

	private static void reRotate(int[][] copy, int r, int c, int s) { // r,c 기준으로 s조각 만큼 돌린다
		for (int k = s; k > 0; k--) { // s부터 1까지 반복
			List<Integer> list = new LinkedList<Integer>(); // 쪼갠 배열의 값을 list에 저장
			int i = r - k - 1; // 시작 지점의 세로 좌표
			int j = c - k - 1; // 시작 지점의 가로 좌표
			while (true) {
				list.add(copy[i][j]); // 리스트에 배열 조각의 원소를 넣는다
				if (i == r - k - 1 && j < c + k - 1) { // 위쪽 줄 추가
					j++; // 오른쪽으로 이동하면서
				} else if (j == c + k - 1 && i < r + k - 1) { // 오른쪽 줄 추가
					i++; // 아래로 이동하면서
				} else if (i == r + k - 1 && j > c - k - 1) { // 아래쪽 줄 추가
					j--; /// 왼쪽으로 이동하면서
				} else if (j == c - k - 1 && i > r - k - 1) { // 왼쪽 줄 추가
					i--; // 위로 이동하면서
				}
				if (i == r - k - 1 && j == c - k - 1) // 처음으로 돌아오면
					break; // 멈춘다
			} // 여기까지 list에 쪼갠 배열을 저장하는 코드
			list.add(list.get(0)); // 첫번째 원소를 맨 뒤에 추가
			list.remove(0); // 첫번째 원소 제거

			int size = list.size(); // 리스트의 사이즈
			for (int l = 0; l < size; l++) { // 돌린 리스트를 원본 배열에 저장
				copy[i][j] = list.get(l); // 저장할때와 똑같은 순서로 덮어쓰기
				if (i == r - k - 1 && j < c + k - 1) { // 위쪽 줄
					j++; // 오른쪽으로 이동하면서
				} else if (j == c + k - 1 && i < r + k - 1) { // 오른쪽 줄
					i++; // 아래로 이동하면서
				} else if (i == r + k - 1 && j > c - k - 1) { // 아래쪽 줄
					j--; /// 왼쪽으로 이동하면서
				} else if (j == c - k - 1 && i > r - k - 1) { // 왼쪽 줄
					i--; // 위로 이동하면서
				}
			}
		}
	}
}
