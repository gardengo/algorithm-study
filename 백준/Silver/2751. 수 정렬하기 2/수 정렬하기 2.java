import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());

		boolean[] arr = new boolean[2000001];
		for (int i = 0; i < N; i++)
			arr[Integer.parseInt(br.readLine()) + 1000000] = true;

		for (int i = 0; i < 2000001; i++)
			if (arr[i] == true) {
				sb.append(i - 1000000);
				sb.append("\n");
			}

		System.out.println(sb);
	}

}
