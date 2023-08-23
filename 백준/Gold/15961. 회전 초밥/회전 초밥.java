import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main { // 클래스 시작
	static int answer; // 정답

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader((new InputStreamReader(System.in))); // 입력을 받기 위한 BufferedReader 객체 생성
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫 줄을 입력 받아 공백으로 나눈다
		int N = Integer.parseInt(st.nextToken()); // 접시의 수 N
		int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓수 d
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수 k
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호 c

		int[] arr = new int[N]; // 초밥의 순서를 저장하는 리스트
		for (int i = 0; i < N; i++) // N줄에서 입력받자
			arr[i] = Integer.parseInt(br.readLine()); // 초밥의 종류 추가

		int lt = 0; // 왼쪽 포인터
		int rt = k; // 오른쪽 포인터

		int[] cnt = new int[d + 1]; // 먹은 초밥의 개수
		int type = 0; // 초밥의 종류
		for (int i = lt; i < rt; i++) { // 0부터 k까지
			if (cnt[arr[i]] == 0) // 지금까지 먹은 적 없는 초밥이면
				type++; // 종류 +1
			cnt[arr[i]]++; // 초밥 초기 세팅
		}

		while (lt < N) { // lt가 0부터 N까지 갈 때까지 탐색
			if (cnt[c] == 0) { // c를 먹은 적 없다면
				cnt[c]++; // c를 1 증가시킨다
				type++; // 종류 +1
			}
			answer = type > answer ? type : answer; // 이번에 구한 종류가 정답보다 크면 바꾼다

			if (--cnt[arr[lt]] == 0) // 왼쪽 초밥을 제거했을 때 0이되면
				type--; // 종류 -1
			if (cnt[arr[rt]]++ == 0) // 오른쪽 초밥이 처음 먹는 거면
				type++; // 종류 +1

			lt++; // 왼쪽 포인터 +1
			if (++rt == N) // 오른쪽 포인터 +1, N이 되면
				rt = 0; // 0으로 바꾼다
		}
		System.out.println(answer);
	} // 메인 종료

} // 클래스 종료
