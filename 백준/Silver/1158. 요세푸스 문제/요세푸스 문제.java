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
		int K = Integer.parseInt(st.nextToken());

		List<Integer> list = new LinkedList<Integer>();
		for (int i = 1; i <= N; i++)
			list.add(i);

		int tmp = K - 1;
		sb.append("<");
		while (true) {
			sb.append(list.remove(tmp));
			if (list.isEmpty())
				break;
			tmp = (tmp + K - 1) % list.size();
			sb.append(",");
			sb.append(" ");
		}
		sb.append(">");
		System.out.println(sb);
	}

}
