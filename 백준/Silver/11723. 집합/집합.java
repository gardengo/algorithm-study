import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int M = Integer.parseInt(br.readLine());

		Set<Integer> S = new HashSet<Integer>();
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			int x = 0;
			if (!(command.equals("all") || command.equals("empty")))
				x = Integer.parseInt(st.nextToken());

			if (command.equals("add")) {
				S.add(x);
			} else if (command.equals("remove")) {
				S.remove(x);
			} else if (command.equals("check")) {
				if (S.contains(x))
					sb.append(1).append("\n");
				else
					sb.append(0).append("\n");
			} else if (command.equals("toggle")) {
				if (S.contains(x))
					S.remove(x);
				else
					S.add(x);
			} else if (command.equals("all")) {
				for (int j = 1; j <= 20; j++)
					S.add(j);
			} else if (command.equals("empty")) {
				S.clear();
			}
		}
		System.out.println(sb);
	}

}
