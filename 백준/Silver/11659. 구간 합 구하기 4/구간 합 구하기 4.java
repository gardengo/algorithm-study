import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 수의 개수 N
		int M = Integer.parseInt(st.nextToken()); // 합을 구해야 하는 횟수 M

		int[] NSumArr = new int[N + 1]; // N개의 숫자 누적합 배열
		st = new StringTokenizer(br.readLine());
		NSumArr[1] = Integer.parseInt(st.nextToken());
		for (int i = 1; i < N; i++)
			NSumArr[i + 1] = NSumArr[i] + Integer.parseInt(st.nextToken());

		for (int k = 0; k < M; k++) {
			st = new StringTokenizer(br.readLine());
			int i = Integer.parseInt(st.nextToken());
			int j = Integer.parseInt(st.nextToken());
			System.out.println(NSumArr[j] - NSumArr[i - 1]); // j까지의 누적합 - (i-1)까지의 누적합
		}

	}
}
