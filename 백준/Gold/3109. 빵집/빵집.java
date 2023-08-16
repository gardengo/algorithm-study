import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // 클래스 시작
	static int R, C, answer; // R:세로길이, C:가로길이, answer:정답
	static char[][] map; // 빵집 근처의 모습이 담긴 지도
	boolean[][] visited; // 방문한 점을 나타내는 배열

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 받기 위한 객체 생성
		StringTokenizer st = new StringTokenizer(br.readLine()); // 한 줄을 입력 받아 공백으로 나눔
		R = Integer.parseInt(st.nextToken()); // R 입력
		C = Integer.parseInt(st.nextToken()); // C 입력
		map = new char[R + 2][C + 2]; // 경계선 처리를 원활하게 하기 위해 +2로 생성
		for (int i = 1; i <= R; i++) { // R개 줄에서 입력 받기
			String str = br.readLine(); // 한 줄을 입력 받는다
			for (int j = 1; j <= C; j++) // C개 열로 나누기
				map[i][j] = str.charAt(j - 1); // 지도에 문자를 입력한다
		} // 입력 종료

		boolean[][] visited = new boolean[R + 2][C + 2]; // 방문한 점을 나타내는 배열
		for (int r = 1; r <= R; r++)
			if (pipeLine(r, 1, visited))
				answer++;
		System.out.println(answer);
	} // 메인 종료

	// 파이프라인 개수를 구하는 메소드
	static boolean pipeLine(int r, int c, boolean[][] visited) { // s:R의 시작점, r:현재의 R, c:현재의 C
		visited[r][c] = true; // 방문했다

		if (c == C) { // 파이프가 끝까지 연결되면
			return true;
		}
		if (map[r - 1][c + 1] == '.' && !visited[r - 1][c + 1]) { // 오른쪽 위가 빈 칸일 때
			if (pipeLine(r - 1, c + 1, visited))
				return true;
		}
		if (map[r][c + 1] == '.' && !visited[r][c + 1]) { // 오른쪽이 빈 칸일 때
			if (pipeLine(r, c + 1, visited))
				return true;
		}
		if (map[r + 1][c + 1] == '.' && !visited[r + 1][c + 1]) { // 오른쪽 아래가 빈 칸일 때
			if (pipeLine(r + 1, c + 1, visited))
				return true;
		}
		return false;
	} // 메소드 종료

} // 클래스 종료
