import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int test = 1; test <= T; test++) {
			int k = Integer.parseInt(br.readLine());
			TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>(); // 값, 중복수량

			for (int i = 0; i < k; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				char oper = st.nextToken().charAt(0);
				int n = Integer.parseInt(st.nextToken());

				if (oper == 'I') { // 삽입
					if (map.containsKey(n)) {
						int value = map.get(n);
						map.put(n, value + 1);
					} else
						map.put(n, 1);
				} else { // 삭제
					if (map.isEmpty())
						continue;
					if (n == 1) { // 최댓값 삭제
						int lastValue = map.lastEntry().getValue();
						if (lastValue == 1)
							map.remove(map.lastKey());
						else
							map.put(map.lastKey(), lastValue - 1);
					} else { // 최솟값 삭제
						int firstValue = map.firstEntry().getValue();
						if (firstValue == 1)
							map.remove(map.firstKey());
						else
							map.put(map.firstKey(), firstValue - 1);
					}
				}
			}
			if (map.isEmpty())
				sb.append("EMPTY\n");
			else
				sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
		}
		System.out.println(sb);
	}

}
