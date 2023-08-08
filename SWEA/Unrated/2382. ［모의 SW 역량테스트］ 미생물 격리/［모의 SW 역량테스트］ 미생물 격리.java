import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	private static int N, M, K;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수 T

		for (int test_case = 1; test_case <= T; test_case++) { // 테스트 케이스 만큼 반복
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 한 변의 셀의 개수
			M = Integer.parseInt(st.nextToken()); // 격리 시간
			K = Integer.parseInt(st.nextToken()); // 미생물 군집의 개수
			List<Microbe> m = new LinkedList<Microbe>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				m.add(new Microbe(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
						Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}

			for (int i = 0; i < M; i++) {
				for (int j = 0; j < m.size(); j++) {
					move(m.get(j));
					if (m.get(j).num == 0) {
						m.remove(j);
						j--;
					}
				}
				m.sort((m1, m2) -> {
					if (m1.x == m2.x) {
						return Integer.compare(m1.y, m2.y);
					} else {
						return Integer.compare(m1.x, m2.x);
					}
				});
				int size = m.size();
				for (int j = size - 1; j > 0; j--) {
					boolean change = false;
					int[] maxIndexSum = { m.get(j).num, j, m.get(j).num };
					int k = 1;
					for (; k <= j; k++) {
						if (m.get(j).x == m.get(j - k).x && m.get(j).y == m.get(j - k).y) {
							if (m.get(j - k).num > maxIndexSum[0]) {
								maxIndexSum[0] = m.get(j - k).num;
								maxIndexSum[1] = j - k;
							}
							maxIndexSum[2] += m.get(j - k).num;
							change = true;
						} else {
							break;
						}
					}
					if (change) {
						Microbe sum = new Microbe(m.get(maxIndexSum[1]).x, m.get(maxIndexSum[1]).y, maxIndexSum[2],
								m.get(maxIndexSum[1]).d);
						for (int l = 0; l < k; l++) {
							m.remove(j - k + 1);
						}
						m.add(j - k + 1, sum);
						j = j - k + 1;
					}
				}
			}
			int answer = 0;
			for (int i = 0; i < m.size(); i++)
				answer += m.get(i).num;

			sb.append("#").append(test_case).append(" ").append(answer).append("\n");
		}
		System.out.println(sb);
	}

	private static void move(Microbe m) {
		switch (m.d) {
		case 1:
			if (m.x == 0) {
				m.d = 2;
				m.x++;
			} else {
				if (m.x == 1)
					m.num /= 2;
				m.x--;
			}
			break;
		case 2:
			if (m.x == N - 1) {
				m.d = 1;
				m.x--;
			} else {
				if (m.x == N - 2)
					m.num /= 2;
				m.x++;
			}
			break;
		case 3:
			if (m.y == 0) {
				m.d = 4;
				m.y++;
			} else {
				if (m.y == 1)
					m.num /= 2;
				m.y--;
			}
			break;
		case 4:
			if (m.y == N - 1) {
				m.d = 3;
				m.y--;
			} else {
				if (m.y == N - 2)
					m.num /= 2;
				m.y++;
			}
			break;
		}
	}

}

class Microbe {
	int x;
	int y;
	int num; // 미생물 수
	int d; // 방향

	public Microbe(int x, int y, int num, int d) {
		this.x = x;
		this.y = y;
		this.num = num;
		this.d = d;
	}
}