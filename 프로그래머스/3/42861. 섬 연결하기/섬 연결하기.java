import java.util.*;
import java.io.*;

class Solution {
    public int solution(int n, int[][] costs) {
        boolean[] visited = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        int answer = 0;
        
        visited[0] = true;
        for (int i = 0; i < costs.length; i++) {
            if (costs[i][0] == 0)
                pq.add(new int[] {costs[i][1], costs[i][2]});
            if (costs[i][1] == 0)
                pq.add(new int[] {costs[i][0], costs[i][2]});
        }
        
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (visited[cur[0]])
                continue;
            visited[cur[0]] = true;
            answer += cur[1];
            
            for (int i = 0; i < costs.length; i++) {
                if (costs[i][0] == cur[0] && !visited[costs[i][1]])
                    pq.add(new int[] {costs[i][1], costs[i][2]});
                if (costs[i][1] == cur[0] && !visited[costs[i][0]])
                    pq.add(new int[] {costs[i][0], costs[i][2]});
            }
        }
        
        return answer;
    }
}