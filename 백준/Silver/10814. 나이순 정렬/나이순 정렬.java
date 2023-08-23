import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	private static class Member {
		private int age;
		private String name;

		public Member(int age, String name) {
			this.age = age;
			this.name = name;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		Member[] member = new Member[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int age = Integer.parseInt(st.nextToken());
			String name = st.nextToken();
			member[i] = new Member(age, name);
		}

		Arrays.sort(member, (a, b) -> Integer.compare(a.age, b.age));

		for (int i = 0; i < N; i++)
			sb.append(member[i].age).append(" ").append(member[i].name).append("\n");
		System.out.println(sb);
	}

}
