import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main { // 클래스 시작

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위한 BufferedReader 객체 생성
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫 번째 줄 입력을 공백으로 문자를 나누기
		int N = Integer.parseInt(st.nextToken()); // 과일의 개수
		int L = Integer.parseInt(st.nextToken()); // 스네이크버드의 초기 길이
		int[] h = new int[N]; // 과일의 높이 배열
		st = new StringTokenizer(br.readLine()); // 두 번째 줄 입력을 공백으로 문자를 나누기
		for (int i = 0; i < N; i++) // 과일의 개수 N 만큼
			h[i] = Integer.parseInt(st.nextToken()); // 과일의 높이를 입력한다

		Arrays.sort(h); // 과일의 높이를 정렬한다
		int pos = 0; // 뱀의 위치
		while (L >= h[pos]) { // 과일의 높이가 뱀의 길이보다 높지 않으면
			L++; // 뱀의 길이가 1 증가하고
			pos++; // 다음 과일로 이동한다
			if (pos == N) // 끝까지 이동하면
				break; // 멈춘다
		} // 반복문을 빠져나오면 그때가 최대 길이
		System.out.println(L); // 최대 길이를 출력한다
	} // 메인 종료

} // 클래스 종료
