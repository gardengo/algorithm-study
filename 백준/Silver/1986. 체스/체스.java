import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		char[][] board = new char[n + 1][m + 1];
		Queue<int[]> queen = new ArrayDeque<int[]>();

		st = new StringTokenizer(br.readLine());
		int q = Integer.parseInt(st.nextToken());
		int queenx = 0;
		int queeny = 0;
		for (int i = 0; i < q; i++) {
			queenx = Integer.parseInt(st.nextToken());
			queeny = Integer.parseInt(st.nextToken());
			board[queenx][queeny] = 'p'; // queen의 위치도 pawn이랑 같게 표시
			queen.add(new int[] { queenx, queeny });
		}
		// knight 입력 받자마자 board에 이동할 수 있는 곳 표시
		st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		int[] knight = new int[2];
		int[] kx = { -2, -1, 1, 2, 2, 1, -1, -2 };
		int[] ky = { 1, 2, 2, 1, -1, -2, -2, -1 };
		for (int i = 0; i < k; i++) {
			knight[0] = Integer.parseInt(st.nextToken());
			knight[1] = Integer.parseInt(st.nextToken());
			board[knight[0]][knight[1]] = 'p'; // knight의 위치도 pawn이랑 같게 표시
			for (int j = 0; j < 8; j++) {
				if (knight[0] + kx[j] <= 0 || knight[0] + kx[j] > n || knight[1] + ky[j] <= 0 || knight[1] + ky[j] > m)
					continue;
				if (board[knight[0] + kx[j]][knight[1] + ky[j]] == '\0')
					board[knight[0] + kx[j]][knight[1] + ky[j]] = 'k';
			}
		}

		// pawn 입력 받으면 board에 표시
		st = new StringTokenizer(br.readLine());
		int p = Integer.parseInt(st.nextToken());
		for (int i = 0; i < p; i++)
			board[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 'p';

		// queen을 하나씩 빼면서 이동 가능한 곳 표시
		int[] now = new int[2];
		int[] qx = { -1, -1, 0, 1, 1, 1, 0, -1 };
		int[] qy = { 0, 1, 1, 1, 0, -1, -1, -1 };
		while (!queen.isEmpty()) {
			now = queen.poll();
			int nx = now[0];
			int ny = now[1];

			for (int i = 0; i < 8; i++) {
				nx = now[0];
				ny = now[1];
				while (true) {
					if (nx + qx[i] <= 0 || nx + qx[i] > n || ny + qy[i] <= 0 || ny + qy[i] > m)
						break;
					if (board[nx + qx[i]][ny + qy[i]] == 'p')
						break;
					nx += qx[i];
					ny += qy[i];
					board[nx][ny] = 'q';
				}
			}
		}

		int answer = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++)
				if (board[i][j] == '\0')
					answer++;
		}

		System.out.println(answer);
	}

}
