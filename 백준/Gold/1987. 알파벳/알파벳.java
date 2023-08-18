import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main { // 클래스 시작
	static int R, C, answer;
	static char[][] board;
	static int[] dx = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dy = { 0, 0, -1, 1 };
	static Set<Character> set;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기 위한 BufferedReader 객체 생성
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫째 줄의 입력을 받아 공백으로 나눈다
		R = Integer.parseInt(st.nextToken()); // 세로칸 R
		C = Integer.parseInt(st.nextToken()); // 가로칸 C
		board = new char[R + 2][C + 2]; // 알파벳이 적혀있는 보드, 경계 처리를 위해 +2
		for (int i = 1; i <= R; i++) { // R개 줄에서 입력을 받는다
			String str = br.readLine(); // 한 줄을 입력받아 String으로 저장
			for (int j = 1; j <= C; j++) // C개의 문자로 나눈다
				board[i][j] = str.charAt(j - 1); // 보드에 알파벳 입력
		} // 입력 종료

		set = new HashSet<Character>(); // 사용한 알파벳을 저장하는 set
		visited = new boolean[R + 2][C + 2]; // 방문한 지점을 표시
		dfs(1, 1, 1);
		System.out.println(answer);
	} // 메인 종료

	static void dfs(int x, int y, int sum) {
		visited[x][y] = true; // 현재 위치를 표시한다
		set.add(board[x][y]); // 현재 문자를 set에 넣는다
		answer = sum > answer ? sum : answer;
		
		for (int i = 0; i < 4; i++) {
			if ('A' <= board[x + dx[i]][y + dy[i]] && board[x + dx[i]][y + dy[i]] <= 'Z'
					&& !visited[x + dx[i]][y + dy[i]]) {
				if (!set.contains(board[x + dx[i]][y + dy[i]])) {
					dfs(x + dx[i], y + dy[i], sum + 1);
					set.remove(board[x + dx[i]][y + dy[i]]);
					visited[x + dx[i]][y + dy[i]] = false;
				}
			}
		}
	}
} // 클래스 종료
