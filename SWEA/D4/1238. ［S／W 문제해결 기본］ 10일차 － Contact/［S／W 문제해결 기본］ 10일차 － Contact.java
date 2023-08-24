import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution { // 클래스 시작

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader((new InputStreamReader(System.in))); // 입력을 받기 위한 BufferedReader 객체 생성
		StringBuilder sb = new StringBuilder(); // 출력을 위한 StringBuilder 객체 생성

		for (int test_case = 1; test_case <= 10; test_case++) { // 테스트 케이스 시작
			StringTokenizer st = new StringTokenizer(br.readLine().trim()); // 한 줄을 받아 공백으로 나눈다
			int n = Integer.parseInt(st.nextToken()); // 입력 받는 데이터의 길이
			int start = Integer.parseInt(st.nextToken()); // 연락 시작점
			LinkedList<LinkedList<Integer>> graph = new LinkedList<LinkedList<Integer>>(); // 연결을 나타내는 그래프
			for (int i = 0; i <= 100; i++) // 0부터 100까지 원소 추가
				graph.add(new LinkedList<Integer>());

			st = new StringTokenizer(br.readLine().trim()); // n개의 데이터를 입력받는다
			while (st.hasMoreTokens()) { // st에 토큰이 없을 때까지 그래프에 경로를 추가한다
				graph.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));
			}

			boolean[] visited = new boolean[101]; // 이미 연락한 사람에게 다시 연락 x
			Queue<int[]> que = new ArrayDeque<int[]>(); // bfs를 위한 큐 생성
			que.offer(new int[] { start, 0 }); // 시작점과 depth 0으로 설정
			visited[start] = true; // 시작점 방문
			int maxNum = 0; // 가장 마지막 연락한 사람 중 높은 번호
			int depth = 0; // bfs의 깊이

			while (!que.isEmpty()) { // 큐가 비어있을 때까지 반복
				int[] cur = que.poll(); // 현재 사람
				if (depth < cur[1]) { // 현재 depth가 기존 depth보다 크면
					depth = cur[1]; // depth를 바꾼다
					maxNum = cur[0]; // 최대값도 바꾼다
				} else if (depth == cur[1]) { // depth가 같으면
					maxNum = cur[0] > maxNum ? cur[0] : maxNum; // 최대값을 비교해서 갱신
				}

				for (int i = 0; i < graph.get(cur[0]).size(); i++) { // 현재 사람이 연락할 수 있는 사람들
					int next = graph.get(cur[0]).get(i); // 다음 사람의 번호와 depth
					if (!visited[next]) { // 연락한 적 없으면
						visited[next] = true; // 연락한다
						que.offer(new int[] { next, cur[1] + 1 }); // 큐에 추가한다
					}
				}
			}
			sb.append("#").append(test_case).append(" ").append(maxNum).append("\n");
		}
		System.out.println(sb);
	} // 메인 종료

} // 클래스 종료
