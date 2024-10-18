import java.util.*;
import java.io.*;

class Solution {
    public int solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        Queue<Integer> que = new ArrayDeque<>();
        int network = 0;
        
        for (int i = 0; i < n; i++) {
            if(!visited[i]) {
                que.add(i);
                visited[i] = true;
                network++;
            }
            
            while (!que.isEmpty()) {
                int cur = que.poll();
                for (int j = i; j < n; j++) {
                    if (!visited[j] && computers[cur][j] == 1) {
                        que.add(j);
                        visited[j] = true;
                    }
                }
            }
        }
        
        return network;
    }
}