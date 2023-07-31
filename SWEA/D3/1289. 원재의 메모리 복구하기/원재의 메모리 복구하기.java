import java.io.BufferedReader;
import java.io.InputStreamReader;

class Solution {
	static char[] arr;
	static char[] ini;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			arr = br.readLine().toCharArray();
			ini = new char[arr.length];
			for (int i = 0; i < ini.length; i++)
				ini[i] = '0';

			int fix = 0;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] != ini[i]) {
					change(i);
					fix++;
				}
			}
			System.out.println("#" + test_case + " " + fix);
		}
	}

	public static void change(int num) {
		for (int i = num; i < ini.length; i++) {
			if (arr[num] == '1') {
				ini[i] = '1';
			} else {
				ini[i] = '0';
			}
		}
	}

}
