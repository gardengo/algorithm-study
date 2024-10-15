import java.util.*;
import java.io.*;

class Solution {
    public int solution(int n, int[][] edge) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) 
            graph.add(new ArrayList<>());

        for (int i = 0; i < edge.length; i++) {
            graph.get(edge[i][0]).add(edge[i][1]);
            graph.get(edge[i][1]).add(edge[i][0]);
        }

        boolean[] visited = new boolean[n + 1];
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[] {1, 0});
        visited[1] = true;
        int dist = 0;
        int answer = 0;

        while(!que.isEmpty()) {
            int[] cur = que.poll();
            if(cur[1] > dist) {
                dist = cur[1];
                answer = 1;
            } else {
                answer++;
            }

            ArrayList<Integer> list =  graph.get(cur[0]);
            for (int i = 0; i < list.size(); i++) {
                int next = list.get(i);
                if (!visited[next]) {
                    que.add(new int[] {next, dist + 1});
                    visited[next] = true;
                }
            }
        }

        return answer;
    }
}