import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main { // 클래스 시작
	private static int N, M, minDist; // 메인 메소드 밖에서도 사용하기 위해 전역 변수로 선언
	private static boolean[] visited; // 조합에서 사용할 방문 배열
	private static List<Building> house = new ArrayList<Building>(); // 집의 위치를 저장한 리스트
	private static List<Building> chickenHouse = new ArrayList<Building>(); // 치킨집의 위치를 저장한 리스트

	static class Building { // 건물의 정보를 담은 클래스
		int x, y, dist;

		Building(int x, int y) { // 생성자
			this.x = x; // 건물의 x좌표
			this.y = y; // 건물의 y좌표
			this.dist = 2 * N; // 건물의 치킨거리
		}
	}

	public static void main(String[] args) throws Exception { // 메인 시작
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위한 BufferedReader 객체 생성
		StringTokenizer st = new StringTokenizer(br.readLine()); // 첫째 줄의 입력을 공백으로 나눈다
		N = Integer.parseInt(st.nextToken()); // 도시의 크기
		M = Integer.parseInt(st.nextToken()); // 고르는 치킨 집의 수
		int[][] map = new int[N][N]; // 도시의 지도
		for (int i = 0; i < N; i++) { // N개의 행에서
			st = new StringTokenizer(br.readLine()); // 입력을 받아 공백으로 나눈다
			for (int j = 0; j < N; j++) { // N개의 열에
				map[i][j] = Integer.parseInt(st.nextToken()); // 입력한다
				if (map[i][j] == 1) { // 1이면 집
					house.add(new Building(i, j)); // 집 리스트에 추가한다
				}
				if (map[i][j] == 2) // 2면 치킨집
					chickenHouse.add(new Building(i, j)); // 치킨집 리스트에 추가한다
			}
		}

		minDist = N * 2 * house.size(); // 치킨 거리의 최솟값
		visited = new boolean[chickenHouse.size()]; // 치킨집을 조합할 것이므로 치킨집 개수로 생성
		List<Building> output = new ArrayList<Building>(); // 조합한 치킨집의 결과를 담는 리스트
		combination(output, 0, 0, chickenHouse.size(), M); // 치킨집 조합
		System.out.println(minDist); // 최소 치킨 거리 출력
	} // 메인 종료

	private static void combination(List<Building> output, int sum, int start, int n, int r) { // 치킨집을 r개로 조합하는 메소드
		if (r == 0) {
			for (int i = 0; i < house.size(); i++) {
				for (int j = 0; j < output.size(); j++) {
					int dist = Math.abs(house.get(i).x - output.get(j).x) + Math.abs(house.get(i).y - output.get(j).y);
					if (dist < house.get(i).dist)
						house.get(i).dist = dist;
				}
			}
			for (int i = 0; i < house.size(); i++) {
				sum += house.get(i).dist;
				house.get(i).dist = N * 2;
			}
			if (minDist > sum)
				minDist = sum;
			return;
		}
		for (int i = start; i < n; i++) {
			visited[i] = true;
			output.add(chickenHouse.get(i));
			combination(output, sum, i + 1, n, r - 1);
			visited[i] = false;
			output.remove(output.size() - 1);
		}
	}
} // 클래스 종료
