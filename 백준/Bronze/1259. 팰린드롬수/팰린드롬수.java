import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 입력
		List<String> list = new ArrayList<String>();
		while (true) {
			String num = br.readLine();
			if (Integer.parseInt(num) == 0) {
				break;
			} else {
				list.add(num);
			}
		}

		// 탐색
		for (String str : list) {
			int ch = 0;
			for (int i = 0; i < str.length() / 2; i++) {
				if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
					bw.write("no");
					bw.newLine();
					break;
				} else {
					ch++;
				}
			}
			if (ch >= str.length() / 2) {
				bw.write("yes");
				bw.newLine();
			}
		}
		// 출력
		bw.flush();
	}

}
