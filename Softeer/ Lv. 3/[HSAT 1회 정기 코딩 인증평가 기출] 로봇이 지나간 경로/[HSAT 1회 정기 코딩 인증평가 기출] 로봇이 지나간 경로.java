import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringBuilder sb = new StringBuilder();
      StringTokenizer st = new StringTokenizer(br.readLine());
      int H = Integer.parseInt(st.nextToken());
      int W = Integer.parseInt(st.nextToken());
      char[][] map = new char[H + 2][W + 2];
      for(int i = 1; i <= H; i++) {
        String str = br.readLine();
        for(int j = 1; j <= W; j++)
          map[i][j] = str.charAt(j - 1);
      } // 입력 끝

      // 한붓그리기? -> 한 쪽 끝에서 출발해서 dfs
      // 끝을 찾는 방법? -> # 옆에 #이 1개만 있으면 끝임
      // 왜냐? 직진이 2칸씩 가기 때문에 #은 무조건 떨어짐

      int sx = 0; // 시작 x
      int sy = 0; // 시작 y
      int sd = -1; // 시작 방향 (상우하좌)
      int[] dx = { -1, 0, 1, 0 };
      int[] dy = { 0, 1, 0, -1 };
      int cnt = 0;
      
      root: for(int i = 1; i <= H; i++) {
        for(int j = 1; j <= W;j++) {
          cnt = 0;
          sd = -1;
          
          if(map[i][j] == '#') {
            for(int d = 0; d < 4; d++) {
              if(map[i + dx[d]][j + dy[d]] == '#') {
                cnt++;
                sd = d;
              }
            }
            
            if(cnt == 1) {
              sx = i;
              sy = j;
              break root;
            }
          }
        }
      } // 시작 좌표, 방향 찾기 끝

      sb.append(sx).append(" ").append(sy).append("\n");
      if(sd == 0) 
        sb.append("^\n");
      if(sd == 1) 
        sb.append(">\n");
      if(sd == 2) 
        sb.append("v\n");
      if(sd == 3) 
        sb.append("<\n");

      // 시작 좌표에서 직진, 못하면 돌리기
      int nx = sx;
      int ny = sy;
      int nd = sd;
      while(true) {
        if(map[nx + dx[nd]][ny + dy[nd]] == '#') {
          map[nx][ny] = '.';
          map[nx + dx[nd]][ny + dy[nd]] = '.';
          nx = nx + (dx[nd] * 2);
          ny = ny + (dy[nd] * 2);
          sb.append("A");
        } else { // 더이상 직진을 못하면 새로운 방향을 찾아야함
          if(map[nx + dx[(nd + 1) % 4]][ny + dy[(nd + 1) % 4]] == '#') { // 우회전 확인
            nd = (nd + 1) % 4;
            sb.append("R");
            continue;
          }
          if(map[nx + dx[(nd + 3) % 4]][ny + dy[(nd + 3) % 4]] == '#') { // 좌회전 확인
            nd = (nd + 3) % 4;
            sb.append("L");
            continue;
          }
          // 여기까지 왔으면 끝까지 온 것임
          break;
        }
      }
      
      System.out.println(sb);
    }
}
