import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// String으로 정렬되는 Map 생성
		TreeMap<String, Integer> smap = new TreeMap<String, Integer>();
		for (int i = 1; i <= N; i++)
			smap.put(br.readLine(), i);

		// index로 정렬되는 Map 생성
		TreeMap<Integer, String> imap = new TreeMap<Integer, String>();
		int max = 0;

		for (int i = 0; i < N - 1; i++) {
			int cnt = 0;
			String cur = smap.firstKey();
			int curidx = smap.remove(cur);
			String next = smap.firstKey();

			for (int j = 0; j < Math.min(cur.length(), next.length()); j++) {
				if (cur.charAt(j) == next.charAt(j))
					cnt++;
				else
					break;
			}

			if (cnt > max) {
				max = cnt;
				imap.clear();
				imap.put(curidx, cur);
				imap.put(smap.get(next), next);
			} else if (cnt != 0 && cnt == max) {
				boolean diff = false; // 다른 문자열인지
				String before = imap.get(imap.firstKey());

				for (int j = 0; j < max; j++) {
					if (before.charAt(j) != next.charAt(j)) {
						diff = true;
						break;
					}
				}

				if (diff) {
					imap.put(curidx, cur);
					imap.put(smap.get(next), next);
				} else {
					imap.put(smap.get(next), next);
				}
			}
		}

		String first = imap.remove(imap.firstKey());
		String second = "";
		root: while (true) {
			second = imap.remove(imap.firstKey());
			for (int i = 0; i < max; i++) {
				if (first.charAt(i) != second.charAt(i))
					continue root;
			}
			break;
		}

		System.out.println(first);
		System.out.println(second);
	}

}
