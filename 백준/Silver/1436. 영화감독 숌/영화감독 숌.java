import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력
		int N = Integer.parseInt(br.readLine());

		// 출력
		int count = 0;
		int num = 666;
		while (count < N) {
			if (String.valueOf(num++).contains("666"))
				count++;
		}
		System.out.println(--num);
	}

}
