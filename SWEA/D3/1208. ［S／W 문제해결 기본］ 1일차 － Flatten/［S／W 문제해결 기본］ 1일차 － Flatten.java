import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] boxs = new int[100];

		for (int test_case = 1; test_case <= 10; test_case++) {
			int dump = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 100; i++) {
				boxs[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(boxs);
			for (int i = 0; i < dump; i++) {
				if (boxs[99] - boxs[0] > 1) {
					boxs[99]--;
					boxs[0]++;
					Arrays.sort(boxs);
				} else {
					break;
				}
			}
			System.out.println("#" + test_case + " " + (boxs[99] - boxs[0]));
		}
	}
}