import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[] dx = { 1, 0, -1, 0 }; // 하우상좌
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] campus = new char[N + 2][M + 2];
		int[] doyeon = new int[2]; // 도연이의 위치

		// +2 사이즈로 만들어서 벽으로 다 채운다
		for (int i = 0; i < N + 2; i++)
			for (int j = 0; j < M + 2; j++)
				campus[i][j] = 'X';
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1; j <= M; j++) {
				campus[i][j] = str.charAt(j - 1);
				if (campus[i][j] == 'I') {
					doyeon[0] = i;
					doyeon[1] = j;
				}
			}
		}

		boolean[][] visited = new boolean[N + 2][M + 2];
		Queue<int[]> que = new ArrayDeque<int[]>();
		que.offer(doyeon);
		int people = 0;

		while (!que.isEmpty()) {
			int cur[] = que.poll();
			if (visited[cur[0]][cur[1]])
				continue;
			visited[cur[0]][cur[1]] = true;
			if (campus[cur[0]][cur[1]] == 'P')
				people++;

			for (int d = 0; d < 4; d++) {
				int[] next = new int[] { cur[0] + dx[d], cur[1] + dy[d] };
				if (visited[next[0]][next[1]] || campus[next[0]][next[1]] == 'X')
					continue;
				que.offer(next);
			}
		}

		if (people == 0)
			System.out.println("TT");
		else
			System.out.println(people);
	}
}
