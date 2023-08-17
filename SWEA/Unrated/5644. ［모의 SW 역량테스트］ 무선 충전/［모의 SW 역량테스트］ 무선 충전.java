import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution { // 클래스 시작
	static int M, A, answer;
	static int[] moveA, moveB; // (0:이동x) (1:상) (2:우) (3:하) (4:좌)
	static int[][][] map; // BC를 표시한 지도
	static int[] dx = { 0, 0, 1, 0, -1 }; // x이동 배열
	static int[] dy = { 0, -1, 0, 1, 0 }; // y이동 배열

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기 위한 BufferedReader 객체 생성
		StringBuilder sb = new StringBuilder(); // 출력을 하기 위한 StringBuilder 객체 생성
		int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스 개수 T 입력

		for (int test_case = 1; test_case <= T; test_case++) { // 테스트 케이스 개수만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine().trim()); // 첫번째 줄을 읽어 공백으로 나눈다
			M = Integer.parseInt(st.nextToken()); // 총 이동 시간 M 입력
			A = Integer.parseInt(st.nextToken()); // BC의 개수 A 입력

			st = new StringTokenizer(br.readLine().trim()); // A의 이동 정보
			moveA = new int[M];
			for (int i = 0; i < M; i++)
				moveA[i] = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine().trim()); // B의 이동 정보
			moveB = new int[M];
			for (int i = 0; i < M; i++)
				moveB[i] = Integer.parseInt(st.nextToken());

			map = new int[11][11][A + 1]; // (1,1)부터 (10,10)까지, 0번 인덱스는 총 BC의 개수
			for (int i = 1; i <= A; i++) { // A개의 배터리 입력
				st = new StringTokenizer(br.readLine().trim()); // BC의 정보
				int X = Integer.parseInt(st.nextToken()); // BC의 x좌표
				int Y = Integer.parseInt(st.nextToken()); // BC의 y좌표
				int C = Integer.parseInt(st.nextToken()); // 충전범위(C)
				int P = Integer.parseInt(st.nextToken()); // 처리량(P)

				for (int j = -C; j <= C; j++) {
					for (int k = Math.abs(j) - C; k <= C - Math.abs(j); k++)
						if (0 < X + j && X + j <= 10 && 0 < Y + k && Y + k <= 10) {
							map[Y + k][X + j][i] = P; // 해당 배터리의 좌표에 P입력
							map[Y + k][X + j][0]++; // 0번 인덱스에 배터리의 개수 +1
						}
				} // map에 배터리 영역을 표시
			}
			// 입력 종료

			answer = 101000; // 정답 초기값 설정
			charge(1, 1, 10, 10, 0, 0);
			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		} // 테스트 케이스 종료
		System.out.println(sb);
	} // 메인 종료

	static void charge(int ax, int ay, int bx, int by, int time, int sum) {
		List<int[]> aBC = new ArrayList<int[]>(); // a가 접속할 수 있는 BC 리스트
		List<int[]> bBC = new ArrayList<int[]>(); // b가 접속할 수 있는 BC 리스트
		int aP = 0; // a의 충전량
		int bP = 0; // b의 충전량
		if (map[ay][ax][0] > 0) { // a가 충전 가능하고
			if (map[by][bx][0] > 0) { // b도 충전 가능하다
				for (int i = 1; i <= A; i++) { // 배터리를 탐색해서
					if (map[ay][ax][i] > 0) // 배터리가 존재하면 리스트에 추가한다
						aBC.add(new int[] { i, map[ay][ax][i] });
					if (map[by][bx][i] > 0)
						bBC.add(new int[] { i, map[by][bx][i] });
				}
				if (aBC.size() == 1) { // a가 접속할 수 있는 BC가 1개면
					if (bBC.size() == 1) { // b가 접속할 수 있는 BC가 1개면
						if (aBC.get(0)[0] == bBC.get(0)[0]) { // a,b 가 접속할 수 있는 BC가 같으면 한 개의 BC에서 절반씩 충전한다
							aP = aBC.get(0)[1] / 2;
							bP = bBC.get(0)[1] / 2;
						} else { // 접속할 수 있는 BC가 다르면 온전하게 충전한다
							aP = aBC.get(0)[1];
							bP = bBC.get(0)[1];
						}
					} else { // b가 접속할 수 있는 BC가 2개 이상이면
						aP = aBC.get(0)[1]; // a는 접속할 수 있는 BC에 바로 접속
						for (int i = 0; i < bBC.size(); i++) // 배터리를 탐색해서 충전량의 최대값으로 바꿔준다
							if (bBC.get(i)[0] != aBC.get(0)[0])
								bP = map[by][bx][bBC.get(i)[0]] > bP ? map[by][bx][bBC.get(i)[0]] : bP;
					}
				} else { // a가 접속할 수 있는 BC가 2개 이상인 경우
					if (bBC.size() == 1) { // b가 접속할 수 있는 BC가 1개면
						bP = bBC.get(0)[1]; // b는 접속할 수 있는 BC에 바로 접속
						for (int i = 0; i < aBC.size(); i++) // 배터리를 탐색해서 충전량의 최대값으로 바꿔준다
							if (aBC.get(i)[0] != bBC.get(0)[0]) {
								aP = map[ay][ax][aBC.get(i)[0]] > aP ? map[ay][ax][aBC.get(i)[0]] : aP;
							}
					} else { // b가 접속할 수 있는 BC가 2개 이상인 경우 <-여기가 BFS 필요
						int[] power = bfs(aBC, bBC, 0, 0);
						aP = power[0];
						bP = power[1];
					}
				}
			} else { // b는 충전이 불가능하다
				for (int i = 1; i <= A; i++) // 배터리를 탐색해서 충전량의 최대값으로 바꿔준다
					aP = map[ay][ax][i] > aP ? map[ay][ax][i] : aP;
			}
		} else { // a가 충전이 불가능하다
			if (map[by][bx][0] > 0) { // b는 충전이 가능하다
				for (int i = 1; i <= A; i++) // 배터리를 탐색해서 충전량의 최대값으로 바꿔준다
					bP = map[by][bx][i] > bP ? map[by][bx][i] : bP;
			}
		} // aP와 bP의 최대값이 구해졌다

		if (time == M) { // M 시간이 되면 끝난다
			answer = sum + aP + bP; // 마지막 M번째를 더해준다
			return;
		}
		// 다음 시간으로 이동한다
		charge(ax + dx[moveA[time]], ay + dy[moveA[time]], bx + dx[moveB[time]], by + dy[moveB[time]], time + 1,
				sum + aP + bP);
	}

	static int[] bfs(List<int[]> aBC, List<int[]> bBC, int aIndex, int bIndex) {
		int[] output = new int[2]; // aP, bP 결과
		int aP, bP;
		int sum = 0;

		while (aIndex < aBC.size()) {
			aP = aBC.get(aIndex)[1];
			while (bIndex < bBC.size()) {
				if (bBC.get(bIndex)[0] != aBC.get(aIndex)[0]) {
					bP = bBC.get(bIndex)[1];

					if (sum < aP + bP) {
						sum = aP + bP;
						output[0] = aP;
						output[1] = bP;
					}
				}
				bIndex++;
			}
			aIndex++;
			bIndex = 0; // b가 한바퀴 돌면 초기화 해준다
		}
		return output;
	}
} // 클래스 종료
