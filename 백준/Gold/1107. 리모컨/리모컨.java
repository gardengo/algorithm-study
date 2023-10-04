import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String N = br.readLine();
		int M = Integer.parseInt(br.readLine());
		if (M == 0) {
			if (N.equals("100"))
				System.out.println(0);
			else if (N.equals("99") || N.equals("101"))
				System.out.println(1);
			else if (N.equals("102"))
				System.out.println(2);
			else
				System.out.println(N.length());
		} else {
			boolean[] button = new boolean[10];
			Arrays.fill(button, true);
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++)
				button[Integer.parseInt(st.nextToken())] = false;

			if (N.equals("100"))
				System.out.println(0);
			else if (N.equals("99") || N.equals("101"))
				System.out.println(1);
			else if (N.equals("102"))
				System.out.println(2);
			else {
				if (M == 10) {
					System.out.println(Math.abs(Integer.parseInt(N) - 100));
					return;
				}

				int sum = 500000;
				root: for (int i = 0; i <= 999999; i++) {
					String str = Integer.toString(i);
					for (int j = 0; j < str.length(); j++) {
						if (!button[str.charAt(j) - '0']) {
							continue root;
						}
					}
					sum = sum > Math.abs(Integer.parseInt(N) - i) + str.length()
							? Math.abs(Integer.parseInt(N) - i) + str.length()
							: sum;
				}
				sum = sum > Math.abs(Integer.parseInt(N) - 100) ? Math.abs(Integer.parseInt(N) - 100) : sum;
				System.out.println(sum);
			}
		}
	}

}
