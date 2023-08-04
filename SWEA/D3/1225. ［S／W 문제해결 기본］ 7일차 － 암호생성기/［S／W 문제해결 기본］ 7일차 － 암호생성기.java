import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int test_case = 1; test_case <= 10; test_case++) { // 테스트 케이스 만큼 반복
			int n = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			Queue<Integer> queue = new LinkedList<Integer>();
			for (int i = 0; i < 8; i++)
				queue.add(Integer.parseInt(st.nextToken()));

			int num = 1;
			while (true) { // 마지막 자리가 0이되면 멈춤
				if (queue.peek() - num <= 0) { // num를 뺐을 때 음수가 되면 0을 추가
					queue.remove();
					queue.add(0);
					break;
				} else {
					queue.add(queue.poll() - num); // 첫 번째 수에서 num를 빼서 뒤에 추가
				}
				num++;
				if (num > 5)
					num = 1;
			}

			sb.append("#");
			sb.append(test_case);
			for (int i = 0; i < 8; i++) {
				sb.append(" ");
				sb.append(queue.poll());
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
