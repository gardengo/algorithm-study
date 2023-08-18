
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main { // 클래스 시작
	static int N, M, D, answer;
	static int[][] board;
	static int[] dx = { 0, -1, 0 }; // 좌상우
	static int[] dy = { -1, 0, 1 };

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기 위한 BufferedReader 객체 생성
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫째 줄의 입력을 받아 공백으로 나눈다
		N = Integer.parseInt(st.nextToken()); // 행의 수 N
		M = Integer.parseInt(st.nextToken()); // 열의 수 M
		D = Integer.parseInt(st.nextToken()); // 공격 거리 제한 D
		board = new int[N][M]; // 격자판
		for (int i = 0; i < N; i++) { // N개의 줄에서 입력을 받는다
			st = new StringTokenizer(br.readLine()); // 공백으로 나눈다
			for (int j = 0; j < M; j++) // M개의 열에 입력한다
				board[i][j] = Integer.parseInt(st.nextToken());
		}
		List<Integer> position = new ArrayList<Integer>(); // 궁수의 위치를 담는 리스트
		combination(M, 3, 0, position); // M개의 행에서 3명의 궁수 위치를 조합한다
		System.out.println(answer);
	} // 메인 종료

	static void combination(int n, int r, int idx, List<Integer> position) { // 궁수의 위치를 조합으로 찾는다
		while (r-- > 0) { // r번 반복
			for (int i = idx; i < n; i++) {
				position.add(i); // 리스트에 i를 더하고
				combination(n, r, i + 1, position); // 다음을 조합한다
				position.remove(position.size() - 1); // i를 리스트에서 제거한다
			}
		}
		if (position.size() == 3) { // 궁수가 3명 모두 배치되면
//			System.out.println(position);
			int[][] newBoard = new int[N][M]; // 궁수의 포지션 별 새로운 보드 생성
			for (int i = 0; i < N; i++)
				for (int j = 0; j < M; j++)
					newBoard[i][j] = board[i][j];
			attackCount(newBoard, position, 1, 0); // 공격횟수를 구한다
		}
	}

	static void attackCount(int[][] newBoard, List<Integer> position, int time, int sum) {
		// N - time이 그 시간에 행(x) 위치
		Set<Integer> set = new HashSet<Integer>(); // 중복공격을 처리하기 위한 set
		Queue<int[]> que = new ArrayDeque<int[]>(); // 중복 시 공격을 위한 queue
		for (int i = 0; i < 3; i++) { // 궁수 3명 반복
			root: for (int j = 0; j < D; j++) { // 0부터 D-1까지
				for (int k = -j; k <= j; k++) { // j 범위에서 왼쪽부터 찾는다
					// 움직일 수 있으면
					if (N - time - j + Math.abs(k) >= 0 && position.get(i) + k >= 0 && position.get(i) + k < M) {
						if (newBoard[N - time - j + Math.abs(k)][position.get(i) + k] == 1) { // 적을 공격할 수 있으면
							int[] pos = new int[] { N - time - j + Math.abs(k), position.get(i) + k }; // 공격 위치
							if (!set.contains(pos[1])) { // set에 없다면
								set.add(pos[1]); // set에 추가한다
								que.offer(pos); // que에 추가한다
							}
							break root; // 다음 궁수로 이동
						}
					}
				}
			}
		}
		while (!que.isEmpty()) { // que가 빌 때까지
			int[] pos = que.poll(); // 원소를 하나씩 빼서
			newBoard[pos[0]][pos[1]] = 0; // 적을 제거하고
			sum++; // 공격횟수를 더해준다
		}

//		System.out.println(time + ":" + sum);
		if (time == N) {
			answer = sum > answer ? sum : answer;
			return;
		}
		attackCount(newBoard, position, time + 1, sum);
	}
} // 클래스 종료
