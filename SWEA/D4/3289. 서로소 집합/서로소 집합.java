import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution { // 클래스 시작
	static int parents[];

	private static void make(int n) { // 집합을 만드는 메소드
		parents = new int[n + 1]; // 1~n을 사용하는 집합을 만든다
		for (int i = 1; i <= n; i++) // 초기값 설정
			parents[i] = i; // 자기 자신을 가르키게 설정한다
	}

	private static int find(int n) { // 원소를 찾는 메소드
		if (parents[n] == n) // 부모와 자신이 같으면
			return n; // 거기가 루트이다
		return parents[n] = find(parents[n]); // 루트를 찾을때까지 find 호출
	}

	private static void union(int a, int b) { // 집합을 합치는 메소드
		int aRoot = find(a); // a루트를 찾고
		int bRoot = find(b); // b루트를 찾는다

		if (aRoot != bRoot) // a루트와 b루트가 다르면
			parents[bRoot] = aRoot; // b루트를 a루트로 바꾼다
	}

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader((new InputStreamReader(System.in))); // 입력을 받기 위한 BufferedReader 객체 생성
		StringBuilder sb = new StringBuilder(); // 출력을 위한 StringBuilder 객체 생성
		int T = Integer.parseInt(br.readLine().trim()); // 테스트 케이스의 수 T

		for (int test_case = 1; test_case <= T; test_case++) { // 테스트 케이스 시작
			StringTokenizer st = new StringTokenizer(br.readLine().trim()); // 첫째 줄을 입력 받아 공백으로 나누기
			int n = Integer.parseInt(st.nextToken()); // 집합의 크기
			int m = Integer.parseInt(st.nextToken()); // 연산의 개수
			sb.append("#").append(test_case).append(" ");

			make(n); // 집합을 만든다
			for (int i = 0; i < m; i++) { // m개의 줄에 연산이 주어진다
				st = new StringTokenizer(br.readLine().trim()); // 연산을 공백으로 나눈다
				int calc = Integer.parseInt(st.nextToken()); // 0:union, 1:find
				int a = Integer.parseInt(st.nextToken()); // 첫 번째 원소
				int b = Integer.parseInt(st.nextToken()); // 두 번째 원소
				if (calc == 0) { // union 호출
					union(a, b); // a루트에 b루트를 합친다
				} else { // find 호출
					int aRoot = find(a); // a루트를 찾는다
					int bRoot = find(b); // b루트를 찾는다
					if (aRoot == bRoot) // a루트와 b루트가 같으면
						sb.append(1); // 1을 출력하고
					else // 다르면
						sb.append(0); // 0을 출력한다
				}
			}
			sb.append("\n");
		} // 테스트 케이스 종료
		System.out.println(sb);
	} // 메인 종료
} // 클래스 종료
