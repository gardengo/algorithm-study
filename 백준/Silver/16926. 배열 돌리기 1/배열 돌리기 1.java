import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int count = (Math.min(N, M) + 1) / 2;
		for (int k = 0; k < count; k++) {
			List<Integer> list = new LinkedList<Integer>();
			int i = k;
			int j = k;
			while (true) {
				list.add(arr[i][j]);
				if (i == k && j < M - k - 1) {
					j++;
				} else if (j == M - k - 1 && i < N - k - 1) {
					i++;
				} else if (i == N - k - 1 && j > k) {
					j--;
				} else if (j == k && i > k) {
					i--;
				}
				if (i == k && j == k)
					break;
			}
			for (int r = 0; r < R; r++) {
				list.add(list.get(0));
				list.remove(0);
			}
			i = k;
			j = k;
			int size = list.size();
			for (int s = 0; s < size; s++) {
				arr[i][j] = list.get(s);
				if (i == k && j < M - k - 1) {
					j++;
				} else if (j == M - k - 1 && i < N - k - 1) {
					i++;
				} else if (i == N - k - 1 && j > k) {
					j--;
				} else if (j == k && i > k) {
					i--;
				}
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(arr[i][j]);
				sb.append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
