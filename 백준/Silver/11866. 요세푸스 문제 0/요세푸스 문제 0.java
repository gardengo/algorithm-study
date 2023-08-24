import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		LinkedList<Integer> list = new LinkedList<Integer>();
		LinkedList<Integer> answer = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++)
			list.add(i);

		int idx = K - 1;
		sb.append("<");
		sb.append(list.remove(idx));
		while (!list.isEmpty()) {
			sb.append(", ");
			idx += K - 1;
			idx %= list.size();
			sb.append(list.remove(idx));
		}
		sb.append(">");
		System.out.println(sb);
	}

}
