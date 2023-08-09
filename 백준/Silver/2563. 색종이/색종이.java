import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위한 BufferedReader 객체 생성
		StringBuilder sb = new StringBuilder(); // 출력을 위한 StringBuilder 객체 생성
		int[][] paper = new int[100][100]; // 100*100의 도화지
		int N = Integer.parseInt(br.readLine()); // 색종이의 수 N
		int black = 0; // 검은 영역의 넓이
		for (int i = 0; i < N; i++) { // N개의 색종이를 붙인다
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()); // 색종이의 x좌표
			int y = Integer.parseInt(st.nextToken()); // 색종이의 y좌표
			for (int j = x; j < x + 10; j++) {
				for (int k = y; k < y + 10; k++) {
					if (paper[k][j] == 0) {
						paper[k][j] = 1;
						black++;
					}
				}
			}
		}
		System.out.println(black);
	}

}
