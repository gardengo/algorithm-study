import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] table;
	static StringBuilder sb = new StringBuilder();;
	static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		table = new int[6][3]; // 결과를 저장하는 배열

		root: for (int tc = 0; tc < 4; tc++) {
			answer = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 6; i++)
				for (int j = 0; j < 3; j++)
					table[i][j] = Integer.parseInt(st.nextToken());
			// 입력 종료

			// 국가 별 경기 합이 5가 아니면 불가능
			for (int i = 0; i < 6; i++) {
				int countrySum = 0;
				for (int j = 0; j < 3; j++)
					countrySum += table[i][j];
				if (countrySum != 5) {
					sb.append(0).append(" ");
					continue root;
				}
			}

			// 승리 합과 패배 합이 다르면 불가능, 승패의 합은 15를 넘을 수 없다
			int winSum = 0;
			int loseSum = 0;
			for (int i = 0; i < 6; i++) {
				winSum += table[i][0];
				loseSum += table[i][2];
			}
			if (winSum != loseSum) {
				sb.append(0).append(" ");
				continue root;
			}
			if (winSum > 15 || loseSum > 15) {
				sb.append(0).append(" ");
				continue root;
			}

			// 무승부가 짝이 안맞으면 불가능
			int drawCountry = 0;
			int drawSum = 0;
			for (int i = 0; i < 6; i++) {
				if (table[i][1] != 0) {
					drawCountry++;
					drawSum += table[i][1];
				}
			}
			if (drawCountry == 1) { // 무승부 국가가 1곳이면 불가능
				sb.append(0).append(" ");
				continue root;
			}
			if (drawSum % 2 == 1) { // 무승부 합이 홀수면 불가능
				sb.append(0).append(" ");
				continue root;
			}

			boolean[] visited = new boolean[6];
			recursionWin(6, table[0][0], 0, 0, 0, visited);
			sb.append(answer).append(" ");
		}
		System.out.println(sb);
	}

	static void recursionWin(int n, int r, int now, int index, int depth, boolean[] visited) {
		if (depth == r) {
			recursionDraw(n, table[now][1], now, now, 0, visited);
			return;
		}
		for (int i = index + 1; i < n; i++) {
			if (table[i][2] > 0 && !visited[i]) {
				table[now][0]--;
				table[i][2]--;
				visited[i] = true;
				if (table[now][0] < 0 || table[i][2] < 0)
					return;

				recursionWin(n, r, now, i, depth + 1, visited);
				visited[i] = false;
				table[now][0]++;
				table[i][2]++;
			}
		}
	}

	static void recursionDraw(int n, int r, int now, int index, int depth, boolean[] visited) {
		if (depth == r) {
			recursionLose(n, table[now][2], now, now, 0, visited);
			return;
		}
		for (int i = index + 1; i < n; i++) {
			if (table[i][1] > 0 && !visited[i]) {
				table[now][1]--;
				table[i][1]--;
				visited[i] = true;
				if (table[now][1] < 0 || table[i][1] < 0)
					return;

				recursionDraw(n, r, now, i, depth + 1, visited);
				visited[i] = false;
				table[now][1]++;
				table[i][1]++;
			}
		}

	}

	static void recursionLose(int n, int r, int now, int index, int depth, boolean[] visited) {
		if (depth == r) {
			if (now == 5 && isAble()) {
				answer = 1;
				return;
			}
			boolean[] newVisited = new boolean[6];
			recursionWin(n, table[now + 1][0], now + 1, now + 1, 0, newVisited);
			return;
		}
		for (int i = index + 1; i < n; i++) {
			if (table[i][0] > 0 && !visited[i]) {
				table[now][2]--;
				table[i][0]--;
				visited[i] = true;
				if (table[now][2] < 0 || table[i][0] < 0)
					return;

				recursionLose(n, r, now, i, depth + 1, visited);
				visited[i] = false;
				table[now][2]++;
				table[i][0]++;
			}
		}
	}

	static boolean isAble() {
		for (int i = 0; i < 6; i++)
			for (int j = 0; j < 3; j++)
				if (table[i][j] != 0)
					return false;
		return true;
	}
}
