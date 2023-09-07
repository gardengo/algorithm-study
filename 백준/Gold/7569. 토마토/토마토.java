import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = { -1, 1, 0, 0, 0, 0 };
	static int[] dy = { 0, 0, -1, 1, 0, 0 };
	static int[] dz = { 0, 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken()); // 상자의 가로 칸의 수(2<=M<=100)
		int N = Integer.parseInt(st.nextToken()); // 상자의 세로 칸의 수(2<=N<=100)
		int H = Integer.parseInt(st.nextToken()); // 상자의 수(1<=H<=100)

		Queue<int[]> que = new ArrayDeque<int[]>();
		int[][][] box = new int[N][M][H];
		int tomato = 0;
		for (int z = 0; z < H; z++) {
			for (int x = 0; x < N; x++) {
				st = new StringTokenizer(br.readLine());
				for (int y = 0; y < M; y++) {
					box[x][y][z] = Integer.parseInt(st.nextToken());
					if (box[x][y][z] == 1) {
						que.offer(new int[] { x, y, z, 0 });
						tomato++;
					}
					if (box[x][y][z] == 0)
						tomato++;
				}
			}
		}

		int day = 0;
		while (!que.isEmpty()) {
			int cur[] = que.poll();
			if (cur[3] != 0 && box[cur[0]][cur[1]][cur[2]] == 1)
				continue;
			day = cur[3] > day ? cur[3] : day;
			box[cur[0]][cur[1]][cur[2]] = 1;
			tomato--;

			for (int d = 0; d < 6; d++) {
				if (cur[0] + dx[d] < 0 || cur[0] + dx[d] >= N || cur[1] + dy[d] < 0 || cur[1] + dy[d] >= M
						|| cur[2] + dz[d] < 0 || cur[2] + dz[d] >= H)
					continue;
				if (box[cur[0] + dx[d]][cur[1] + dy[d]][cur[2] + dz[d]] == 0)
					que.offer(new int[] { cur[0] + dx[d], cur[1] + dy[d], cur[2] + dz[d], cur[3] + 1 });
			}
		}

		if (tomato > 0)
			System.out.println(-1);
		else
			System.out.println(day);
	}

}
