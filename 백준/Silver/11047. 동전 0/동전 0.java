import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(br.readLine());

		int answer = 0;
		int idx = N - 1;
		while (K > 0) {
			if (K / arr[idx] == 0)
				idx--;
			else {
				answer += K / arr[idx];
				K -= K / arr[idx] * arr[idx];
			}
		}

		sb.append(answer);
		System.out.println(sb);
	}

}
