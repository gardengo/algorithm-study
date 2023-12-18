import java.io.*;
import java.util.*;

public class Main {
  static int n, m, answer;
  static int[][] map, target;
  static int[] dx = { -1, 1, 0 ,0 };
  static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      map = new int[n + 2][n + 2];
      for(int i = 0; i < n + 1; i++) { // 가장자리 벽으로 채우기
        map[i][0] = 1;
        map[i][n + 1] = 1;
        map[0][i] = 1;
        map[n + 1][i] = 1;
      }
      for(int i = 1; i <= n; i++) {
        st = new StringTokenizer(br.readLine());
        for(int j = 1; j <= n; j++)
          map[i][j] = Integer.parseInt(st.nextToken());
      }
      target = new int[m][2]; // 방문해야 할 위치
      for(int i = 0; i < m; i++) {
        st = new StringTokenizer(br.readLine());
        target[i][0] = Integer.parseInt(st.nextToken());
        target[i][1] = Integer.parseInt(st.nextToken());
      }
      // 입력 종료

      // dfs를 돌려서 전체 경우를 탐색하고 순서대로 방문한 횟수만 카운트
      answer = 0;
      boolean[][] visited = new boolean[n + 2][n + 2];
      dfs(target[0][0], target[0][1], visited, 1);
      
      System.out.println(answer);
    }

  public static void dfs(int x, int y, boolean[][] visited, int cnt) {
    if(target[cnt][0] == x && target[cnt][1] == y)
      cnt++;
    if(cnt == m) {
      answer++;
      return;
    }

    for(int d = 0; d < 4; d++) {
      if(map[x + dx[d]][y + dy[d]] == 1 || visited[x + dx[d]][y + dy[d]]) 
        continue;
      
      visited[x][y] = true;
      dfs(x + dx[d], y + dy[d], visited, cnt);
      visited[x][y] = false;
    }
  }
}
