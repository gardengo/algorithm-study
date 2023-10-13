import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		String str = br.readLine();
		StringBuilder sb = new StringBuilder();
		sb.append("I");
		for (int i = 0; i < N; i++)
			sb.append("OI");
		String Pn = sb.toString();

		int[] table = new int[Pn.length()];
		for (int i = 2; i < Pn.length(); i++)
			table[i] = i - 1;

		int answer = 0;
		int idx = 0;
		for (int i = 0; i < str.length(); i++) {
			while (idx > 0 && str.charAt(i) != Pn.charAt(idx)) {
				idx = table[idx - 1];
			}
			if (str.charAt(i) == Pn.charAt(idx)) {
				if (idx == Pn.length() - 1) {
					answer++;
					idx = table[idx];
				} else {
					idx++;
				}
			}
		}
		System.out.println(answer);
	}

}