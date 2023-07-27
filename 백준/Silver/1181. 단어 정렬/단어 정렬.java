import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		String[] word = new String[N];
		for (int i = 0; i < N; i++)
			word[i] = br.readLine();

		// 정렬
		Arrays.sort(word, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				if (s1.length() == s2.length()) {
					return s1.compareTo(s2);
				} else {
					return s1.length() - s2.length();
				}
			}
		});

		// 출력
		for (int i = 0; i < N; i++) {
			bw.write(word[i]);
			bw.newLine();

			while (i + 1 < N && word[i].equals(word[i + 1]))
				i++;
		}
		bw.flush();
	}

}
