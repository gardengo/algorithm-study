import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { // 클래스 시작
	static int H, W, K, answer;
	static int[][] map;
	static int[][][] dp;
	static int[] dx = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dy = { 0, 0, -1, 1 };
	static int[] dhx = { -2, -1, 1, 2, 2, 1, -1, -2 }; // 시계방향
	static int[] dhy = { 1, 2, 2, 1, -1, -2, -2, -1 };

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken()); // 가로길이 W
		H = Integer.parseInt(st.nextToken()); // 세로길이 H
		map = new int[H][W];
		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		dp = new int[H][W][K + 1]; // 뛴 횟수만큼 공간을 분리해서 저장
		answer = Integer.MAX_VALUE; // 최대값으로 초기 설정
		bfs();

		if (answer == Integer.MAX_VALUE) // 값이 안바뀌었으면 도착 x
			answer = -1;
		System.out.println(answer);
	}

	private static void bfs() {
		Queue<int[]> que = new ArrayDeque<int[]>();
		que.offer(new int[] { 0, 0, 0, 0 }); // x, y, 점프횟수, 움직인횟수

		while (!que.isEmpty()) {
			int[] cur = que.poll();
			int x = cur[0];
			int y = cur[1];
			int jump = cur[2];
			int move = cur[3];

			if (x == H - 1 && y == W - 1) { // 끝까지 도착하면 최소를 구했다
				answer = move;
				return;
			}

			if (dp[x][y][jump] != 0) // 이미 값이 있으면 최소가 아님
				continue;
			dp[x][y][jump] = move;

			for (int d = 0; d < 4; d++) { // 상하좌우 이동
				if (x + dx[d] < 0 || x + dx[d] >= H || y + dy[d] < 0 || y + dy[d] >= W) // 이동가능한지
					continue;
				if (map[x + dx[d]][y + dy[d]] == 1) // 장애물이 있으면 continue
					continue;
				if (dp[x + dx[d]][y + dy[d]][jump] == 0) // 아직 값이 없으면 추가
					que.offer(new int[] { x + dx[d], y + dy[d], jump, move + 1 });
			}

			for (int d = 0; d < 8; d++) { // 점프 이동(시계방향)
				if (x + dhx[d] < 0 || x + dhx[d] >= H || y + dhy[d] < 0 || y + dhy[d] >= W) // 이동가능한지
					continue;
				if (map[x + dhx[d]][y + dhy[d]] == 1) // 장애물이 있으면 continue
					continue;
				if (jump + 1 <= K && dp[x + dhx[d]][y + dhy[d]][jump + 1] == 0) // 아직 값이 없으면 추가
					que.offer(new int[] { x + dhx[d], y + dhy[d], jump + 1, move + 1 });
			}
		}
	}
}
