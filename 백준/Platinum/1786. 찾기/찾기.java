import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String T = br.readLine();
		String P = br.readLine();

		int[] table = new int[P.length()];
		int idx = 0;
		for (int i = 1; i < P.length(); i++) {
			while (idx > 0 && P.charAt(i) != P.charAt(idx)) {
				idx = table[idx - 1];
			}

			if (P.charAt(i) == P.charAt(idx)) {
				idx++;
				table[i] = idx;
			}
		}

		int cnt = 0;
		idx = 0;
		for (int i = 0; i < T.length(); i++) {
			while (idx > 0 && T.charAt(i) != P.charAt(idx)) {
				idx = table[idx - 1];
			}

			if (T.charAt(i) == P.charAt(idx)) {
				if (idx == P.length() - 1) {
					cnt++;
					sb.append(i - idx + 1).append("\n");
					idx = table[idx];
				} else
					idx++;
			}
		}
		System.out.println(cnt);
		System.out.println(sb);
	}

}
