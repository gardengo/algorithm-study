import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution { // 클래스 시작

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기 위한 BufferedReader 객체 생성
		StringBuilder sb = new StringBuilder(); // 출력을 하기 위한 StringBuilder 객체 생성
		int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스 개수 T 입력

		for (int test_case = 1; test_case <= T; test_case++) { // 테스트 케이스 개수만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine().trim()); // H,W 입력
			int H = Integer.parseInt(st.nextToken()); // 맵의 높이 H
			int W = Integer.parseInt(st.nextToken()); // 맵의 너비 W
			char[][] map = new char[H][W]; // H*W 크기의 게임 맵
			int nx = 0; // 전차의 x
			int ny = 0; // 전차의 y
			for (int i = 0; i < H; i++) { // H개의 줄에서
				String str = br.readLine(); // 문자열을 읽어들여
				for (int j = 0; j < W; j++) { // W개의 문자로 나누어
					map[i][j] = str.charAt(j); // 맵에 입력한다
					if (map[i][j] == '^' || map[i][j] == 'v' || map[i][j] == '<' || map[i][j] == '>') {
						nx = i;
						ny = j;
					}
				}
			}
			int N = Integer.parseInt(br.readLine().trim()); // 사용자가 넣을 입력의 개수 N
			char[] input = br.readLine().toCharArray(); // 사용자의 입력 배열
			// 입력 종료

			for (int i = 0; i < N; i++) { // N번 돌면서 input을 수행한다
				if (input[i] == 'U') { // 입력이 U일때
					if (nx > 0 && map[nx - 1][ny] == '.') { // 전차가 위로 이동할 수 있다면
						map[nx][ny] = '.'; // 전차가 원래 있던 위치는 평지가 되고
						map[--nx][ny] = '^'; // 전차가 위로 이동한다
					} else { // 전차가 위로 이동할 수 없다면
						map[nx][ny] = '^'; // 전차가 방향만 위로 바꾼다
					}
				} else if (input[i] == 'D') { // 입력이 D일때
					if (nx < H - 1 && map[nx + 1][ny] == '.') { // 전차가 오른쪽으로 이동할 수 있다면
						map[nx][ny] = '.'; // 전차가 원래 있던 위치는 평지가 되고
						map[++nx][ny] = 'v'; // 전차가 아래쪽으로 이동한다
					} else { // 전차가 위로 이동할 수 없다면
						map[nx][ny] = 'v'; // 전차가 방향만 아래로 바꾼다
					}
				} else if (input[i] == 'L') { // 입력이 L일때
					if (ny > 0 && map[nx][ny - 1] == '.') { // 전차가 위로 이동할 수 있다면
						map[nx][ny] = '.'; // 전차가 원래 있던 위치는 평지가 되고
						map[nx][--ny] = '<'; // 전차가 왼쪽으로 이동한다
					} else { // 전차가 위로 이동할 수 없다면
						map[nx][ny] = '<'; // 전차가 방향만 왼쪽으로 바꾼다
					}
				} else if (input[i] == 'R') { // 입력이 R일때
					if (ny < W - 1 && map[nx][ny + 1] == '.') { // 전차가 오른쪽으로 이동할 수 있다면
						map[nx][ny] = '.'; // 전차가 원래 있던 위치는 평지가 되고
						map[nx][++ny] = '>'; // 전차가 오른쪽으로 이동한다
					} else { // 전차가 위로 이동할 수 없다면
						map[nx][ny] = '>'; // 전차가 방향만 위로 바꾼다
					}
				} else if (input[i] == 'S') { // 입력이 S일때
					int sx = nx; // 포탄의 x
					int sy = ny; // 포탄의 y
					if (map[nx][ny] == '^') { // 전차가 위쪽을 볼때
						while (sx-- > 0) { // 포탄이 위로 갈 수 있다면
							if (map[sx][sy] == '*') { // 포탄이 벽돌 벽을 만나면
								map[sx][sy] = '.'; // 평지로 바뀌고
								break; // 멈춘다
							} else if (map[sx][sy] == '#') // 포탄이 강철 벽을 만나면
								break; // 멈춘다
						}
					} else if (map[nx][ny] == 'v') { // 전차가 아래쪽을 볼때
						while (sx++ < H - 1) { // 포탄이 아래로 갈 수 있다면
							if (map[sx][sy] == '*') { // 포탄이 벽돌 벽을 만나면
								map[sx][sy] = '.'; // 평지로 바뀌고
								break; // 멈춘다
							} else if (map[sx][sy] == '#') // 포탄이 강철 벽을 만나면
								break; // 멈춘다
						}
					} else if (map[nx][ny] == '<') { // 전차가 왼쪽을 볼때
						while (sy-- > 0) { // 포탄이 왼쪽으로 갈 수 있다면
							if (map[sx][sy] == '*') { // 포탄이 벽돌 벽을 만나면
								map[sx][sy] = '.'; // 평지로 바뀌고
								break; // 멈춘다
							} else if (map[sx][sy] == '#') // 포탄이 강철 벽을 만나면
								break; // 멈춘다
						}
					} else if (map[nx][ny] == '>') { // 전차가 오른쪽을 볼때
						while (sy++ < W - 1) { // 포탄이 오른쪽으로 갈 수 있다면
							if (map[sx][sy] == '*') { // 포탄이 벽돌 벽을 만나면
								map[sx][sy] = '.'; // 평지로 바뀌고
								break; // 멈춘다
							} else if (map[sx][sy] == '#') // 포탄이 강철 벽을 만나면
								break; // 멈춘다
						}
					}
				}
			}
			sb.append("#").append(test_case).append(" ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++)
					sb.append(map[i][j]);
				sb.append("\n");
			}
		} // 테스트 케이스 종료
		System.out.println(sb);
	} // 메인 종료

} // 클래스 종료
