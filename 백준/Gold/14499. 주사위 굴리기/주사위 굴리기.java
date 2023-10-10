import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = { 0, 0, 0, -1, 1 };
	static int[] dy = { 0, 1, -1, 0, 0 };
	static int[] dice;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[][] map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		dice = new int[6];
		int floor = 5;
		int east = 2;
		int north = 1;

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int cmd = Integer.parseInt(st.nextToken());
			int nx = x + dx[cmd];
			int ny = y + dy[cmd];
			if (nx < 0 || nx >= N || ny < 0 || ny >= M)
				continue;

			x = nx;
			y = ny;
			int[] result = rollDice(floor, east, north, cmd);
			floor = result[0];
			east = result[1];
			north = result[2];

			if (map[x][y] == 0) {
				map[x][y] = dice[floor];
			} else {
				dice[floor] = map[nx][ny];
				map[x][y] = 0;
			}
			sb.append(dice[reverseDice(floor)]).append("\n");
		}
		System.out.println(sb);
	}

	public static int reverseDice(int floor) {
		switch (floor) {
		case 0:
			return 5;
		case 1:
			return 4;
		case 2:
			return 3;
		case 3:
			return 2;
		case 4:
			return 1;
		case 5:
			return 0;
		default:
			return -1;
		}
	}

	public static int[] rollDice(int floor, int east, int north, int cmd) {
		int[] result = new int[3];
		switch (cmd) {
		case 1:
			result[0] = east;
			result[1] = reverseDice(floor);
			result[2] = north;
			break;
		case 2:
			result[0] = reverseDice(east);
			result[1] = floor;
			result[2] = north;
			break;
		case 3:
			result[0] = north;
			result[1] = east;
			result[2] = reverseDice(floor);
			break;
		case 4:
			result[0] = reverseDice(north);
			result[1] = east;
			result[2] = floor;
			break;
		default:
			break;
		}
		return result;
	}

}
