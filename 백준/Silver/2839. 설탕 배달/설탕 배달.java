import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main { // 클래스 시작

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위한 BufferedReader 객체 생성
		int N = Integer.parseInt(br.readLine()); // 배달해야하는 설탕의 무게
		int five = N / 5; // 5kg 봉지 개수
		int three = 0; // 3kg 봉지 개수
		while (true) { // 5kg과 3kg 봉지의 수를 구하기 위한 반복문
			if (five < 0) { // 5kg 봉지가 0보다 작으면 만들 수가 없음
				System.out.println(-1); // -1을 출력
				System.exit(0); // 메인 종료
			}
			if ((N - five * 5) - (three * 3) == 0) { // 정확하게 무게가 구해지면 최소 개수이므로
				break; // 멈춘다
			} else if ((N - five * 5) - (three * 3) > 0) { // 무게가 남으면
				three++; // 3kg 봉지를 추가한다
			} else { // 무게가 부족하면
				five--; // 5kg 봉지를 줄인다
			}
		}
		System.out.println(five + three); // 반복문을 빠져나오면 최소 개수 출력
	} // 메인 종료

} // 클래스 종료
