import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine()); // 스위치 개수
		StringTokenizer st = new StringTokenizer(br.readLine()); // 스위치 배열
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		int std = Integer.parseInt(br.readLine()); // 학생 수
		for (int i = 0; i < std; i++) {
			st = new StringTokenizer(br.readLine());
			int gen = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());

			if (gen == 1) { // 남학생
				int tmp = num - 1;
				while (tmp < arr.length) {
					arr[tmp] = arr[tmp] == 0 ? 1 : 0;
					tmp += num;
				}
			} else {// 여학생
				int tmp = num - 1;
				arr[tmp] = arr[tmp] == 0 ? 1 : 0;
				for (int j = 1; j < n / 2; j++) {
					if (tmp + j >= n || tmp - j < 0)
						break;
					if (arr[tmp + j] == arr[tmp - j]) {
						arr[tmp + j] = arr[tmp + j] == 0 ? 1 : 0;
						arr[tmp - j] = arr[tmp - j] == 0 ? 1 : 0;
					} else {
						break;
					}
				}
			}
		}
		
		for (int k = 0; k < n; k++) {
			bw.write(arr[k] + " ");
			if ((k + 1) % 20 == 0)
				bw.newLine();
		}
		bw.flush();
	}
}