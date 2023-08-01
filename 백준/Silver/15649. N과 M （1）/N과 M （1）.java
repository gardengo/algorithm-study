import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	private static int N;
	private static int M;
	private static int[] data;
	private static int[] numbers;
	private static boolean[] isSelected;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		data = new int[N];
		numbers = new int[M];
		isSelected = new boolean[N];

		for (int i = 0; i < N; i++)
			data[i] = i + 1;

		recursion(0);
		bw.flush();
	}

	private static void recursion(int num) throws Exception {
		if (num == M) {
			for (int i = 0; i < numbers.length; i++)
				bw.append(numbers[i] + " ");
			bw.append("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			if (isSelected[i])
				continue;
			numbers[num] = data[i];
			isSelected[i] = true;
			recursion(num + 1);
			isSelected[i] = false;
		}
	}

}
