import java.io.*;
import java.util.*;

public class Main {
  static int N, T;
  static int[][][] cross;
  static HashSet<Integer> set;
  static int[] dx = { 0, -1, 0, 1 };
  static int[] dy = { 1, 0, -1, 0 };

    public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N = Integer.parseInt(st.nextToken()); // 교차로 크기
      T = Integer.parseInt(st.nextToken()); // 시간
      cross = new int[N][N][4]; // 교차로의 신호
      for(int i = 0; i < N; i++) {
        for(int j = 0; j < N; j++) {
          st = new StringTokenizer(br.readLine());
          for(int k = 0; k < 4; k++)
            cross[i][j][k] = Integer.parseInt(st.nextToken());
        }
      } // 입력 종료

      set = new HashSet<Integer>(); // 방문한 교차로를 중복 없이 저장
      int time = 0; // 현재시간
      int nx = 0; // 현재 x
      int ny = 0; // 현재 y
      int nd = 1; // 현재 방향 (우상좌하)

      move(nx, ny, nd, time);

      System.out.println(set.size());
    }


  public static void move(int nx, int ny, int nd, int time) {
    if(time > T)
      return;
    set.add(nx * 100 + ny);
    // 방향과 신호를 어떻게 조합할 것인가?
    // -1%4로 방향을 구분하고
    // -1/4로 신호를 구분하자
    
    int sign = cross[nx][ny][time % 4]; // 신호 : 1 ~ 12
    int dir = (sign - 1) % 4; // 신호 방향 : 0 ~ 3
    int type = (sign - 1) / 4; // 신호 종류 : 0 ~ 2
    
    if(dir != nd) // 차량의 방향과 신호의 방향이 다르면 이동 불가
      return;
    if(type == 0) { // 직진, 좌회전, 우회전
      if(0 <= nx + dx[nd] && nx + dx[nd] < N && 0 <= ny + dy[nd] && ny + dy[nd] < N)
        move(nx + dx[nd], ny + dy[nd], nd, time + 1);
      if(0 <= nx + dx[(nd + 1) % 4] && nx + dx[(nd + 1) % 4] < N && 0 <= ny + dy[(nd + 1) % 4] && ny + dy[(nd + 1) % 4] < N)
        move(nx + dx[(nd + 1) % 4], ny + dy[(nd + 1) % 4], (nd + 1) % 4, time + 1);
      if(0 <= nx + dx[(nd + 3) % 4] && nx + dx[(nd + 3) % 4] < N && 0 <= ny + dy[(nd + 3) % 4] && ny + dy[(nd + 3) % 4] < N)
        move(nx + dx[(nd + 3) % 4], ny + dy[(nd + 3) % 4], (nd + 3) % 4, time + 1);
    } else if(type == 1) { // 직진, 좌회전
      if(0 <= nx + dx[nd] && nx + dx[nd] < N && 0 <= ny + dy[nd] && ny + dy[nd] < N)
        move(nx + dx[nd], ny + dy[nd], nd, time + 1);
      if(0 <= nx + dx[(nd + 1) % 4] && nx + dx[(nd + 1) % 4] < N && 0 <= ny + dy[(nd + 1) % 4] && ny + dy[(nd + 1) % 4] < N)
        move(nx + dx[(nd + 1) % 4], ny + dy[(nd + 1) % 4], (nd + 1) % 4, time + 1);
    } else { // 직진, 우회전
      if(0 <= nx + dx[nd] && nx + dx[nd] < N && 0 <= ny + dy[nd] && ny + dy[nd] < N)
        move(nx + dx[nd], ny + dy[nd], nd, time + 1);
      if(0 <= nx + dx[(nd + 3) % 4] && nx + dx[(nd + 3) % 4] < N && 0 <= ny + dy[(nd + 3) % 4] && ny + dy[(nd + 3) % 4] < N)
        move(nx + dx[(nd + 3) % 4], ny + dy[(nd + 3) % 4], (nd + 3) % 4, time + 1);
    }
    
  }
}
