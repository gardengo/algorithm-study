import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { // 클래스 시작

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader((new InputStreamReader(System.in))); // 입력을 받기 위한 BufferedReader 객체 생성
		StringBuilder sb = new StringBuilder(); // 출력을 위한 StringBuilder 객체 생성
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫 줄을 입력받아 공백으로 나눈다
		int N = Integer.parseInt(st.nextToken()); // 학생의 수
		int M = Integer.parseInt(st.nextToken()); // 키를 비교환 횟수
		ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>(); // 키를 비교한 그래프
		graph.add(new ArrayList<Integer>()); // 1번 인덱스부터 사용할 것이므로 0에 추가
		for (int i = 1; i <= N; i++) // N개의 노드별로
			graph.add(new ArrayList<Integer>()); // 리스트를 만들어준다
		int[] degree = new int[N + 1]; // N명의 학생의 진입차수
		for (int i = 0; i < M; i++) { // 다음 M개의 줄에서 입력을 받는다
			st = new StringTokenizer(br.readLine()); // 한 줄에서 입력을 받고
			int front = Integer.parseInt(st.nextToken()); // 앞에 서는 사람
			int back = Integer.parseInt(st.nextToken()); // 뒤에 서는 사람
			graph.get(front).add(back); // 앞에 서야하는 사람의 index에 뒤에 서야 하는 사람의 번호를 추가한다
			degree[back]++; // 뒤에 서는 사람의 진입차수를 +1
		} // 입력 종료

		Queue<Integer> que = new ArrayDeque<Integer>(); // 정렬을 위한 queue 생성
		for (int i = 1; i <= N; i++) // N명을 검사하여
			if (degree[i] == 0) // 진입차수가 0이면
				que.offer(i); // 큐에 추가한다

		while (!que.isEmpty()) { // 큐가 빌 때 까지
			int index = que.poll(); // 큐에서 원소 하나를 뺀다
			sb.append(index).append(" "); // sb에 추가

			ArrayList<Integer> list = graph.get(index); // index에 위치한 리스트
			for (int i = 0; i < list.size(); i++) { // list 전체를 탐색
				if (--degree[list.get(i)] == 0) // 진입차수를 1씩 빼고 0이되면
					que.offer(list.get(i)); // 큐에 추가한다
			}
		}
		System.out.println(sb); // 결과 출력
	} // 메인 종료

} // 클래스 종료
