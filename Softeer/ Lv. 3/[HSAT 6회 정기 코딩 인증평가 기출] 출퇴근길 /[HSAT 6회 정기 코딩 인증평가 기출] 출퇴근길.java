import java.io.*;
import java.util.*;

public class Main {
  static int S, T;
  static ArrayList<ArrayList<Integer>> graph;
  static boolean[] canMid, canEnd, resultS, resultT, visited;

    public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      graph = new ArrayList<ArrayList<Integer>>();
      for(int i = 0; i <= n; i++)
        graph.add(new ArrayList<Integer>());
      for(int i = 0; i < m; i++) {
        st = new StringTokenizer(br.readLine());
        graph.get(Integer.parseInt(st.nextToken())).add(Integer.parseInt(st.nextToken()));
      }
      st = new StringTokenizer(br.readLine());
      S = Integer.parseInt(st.nextToken()); // 시작
      T = Integer.parseInt(st.nextToken()); // 종료

      // 시작노드 -> 중간노드 가능? bfs로 갈 수 있는지 확인
      // 중간노드 -> 종료노드 가능? dfs로 갈 수 있는지 확인
      canMid = new boolean[n + 1];
      canEnd = new boolean[n + 1];
      resultS = new boolean[n + 1];
      resultT = new boolean[n + 1];
      visited = new boolean[n + 1];
      
      Queue<Integer> que = new ArrayDeque<Integer>();
      que.offer(S);
      while(!que.isEmpty()) {
        // 현재 노드를 방문하지 않았으면 방문 처리
        int cur = que.poll();
        if(canMid[cur])
          continue;
        canMid[cur] = true;

        // 현재 노드에서 갈 수 있는 노드들 중 방문하지 않은 노드만 방문
        for(int i = 0; i < graph.get(cur).size(); i++) {
          int next = graph.get(cur).get(i);
          if(next == T)
            continue;
          if(!canMid[next])
            que.offer(next);
        }
      }
      canMid[S] = false; // 시작 노드 false 처리
      
      // canMid 배열에 갈 수 있는 중간 노드가 모였다
      // 중간 노드에서 종료 노드를 갈 수 있는 지 dfs를 하자
      for(int i = 1; i <= n; i++) {
        if(canEnd[i])
          continue;
        if(canMid[i]) {
          Arrays.fill(visited, false);
          visited[i] = true;
          dfs(i, 0, T, visited);
        }
      }
        
      // canMid와 canEnd를 비교해서 진짜 갈 수 있는 노드를 찾자
      for(int i = 1; i <= n; i++) {
        if(canMid[i] && canEnd[i])
          resultS[i] = true;
      }

      // 출근길에 방문할 수 있는 노드를 모두 구했다
      // 퇴근길도 같은 방식으로 구해준다
      canMid = new boolean[n + 1];
      canEnd = new boolean[n + 1];
      Arrays.fill(visited, false);
      que.clear();
      
      que.offer(T);
      while(!que.isEmpty()) {
        // 현재 노드를 방문하지 않았으면 방문 처리
        int cur = que.poll();
        if(canMid[cur])
          continue;
        canMid[cur] = true;

        // 현재 노드에서 갈 수 있는 노드들 중 방문하지 않은 노드만 방문
        for(int i = 0; i < graph.get(cur).size(); i++) {
          int next = graph.get(cur).get(i);
          if(next == S)
            continue;
          if(!canMid[next])
            que.offer(next);
        }
      }
      canMid[T] = false; // 시작 노드 false 처리

      for(int i = 1; i <= n; i++) {
        if(canEnd[i])
          continue;
        if(canMid[i]) {
          Arrays.fill(visited, false);
          visited[i] = true;
          dfs(i, 0, S, visited);
        }
      }
          
      for(int i = 1; i <= n; i++) {
        if(canMid[i] && canEnd[i])
          resultT[i] = true;
      }
      
      // 완성된 두 배열을 비교하여 중복되는 지점을 찾는다
      int answer = 0;
      for(int i = 1; i <= n; i++) {
        if(resultS[i] && resultT[i])
          answer++;
      }
      
      System.out.println(answer);
    }
  
  public static void dfs(int cur, int before, int end, boolean[] visited) {
    if(cur == end) { // 종료 노드에 도착하면 성공
      canEnd[before] = true;
      return;
    }
    if(canEnd[cur]) { // 현재 노드가 갈 수 있으면 이전 노드도 가능
      canEnd[before] = true;
      return;
    }

    for(int i = 0; i < graph.get(cur).size(); i++) {
      int next = graph.get(cur).get(i);
      if(visited[next])
        continue;

      visited[next] = true;
      dfs(next, cur, end, visited);
      
      if(canEnd[cur])
        break;
      if(canEnd[next]) {
        canEnd[cur] = true;
        break;
      }
    }
  }
}
