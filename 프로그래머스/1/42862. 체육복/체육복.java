import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < reserve.length; i++)
            set.add(reserve[i]);
        
        boolean[] visited = new boolean[lost.length];
        int success = 0;
        Arrays.sort(lost);
        
        for(int i = 0; i < lost.length; i++) {
            if(set.contains(lost[i])) {
                success++;
                set.remove(lost[i]);
                visited[i] = true;
            }
        }
        
        for(int i = 0; i < lost.length; i++) {
            if(visited[i])
                continue;
            if(set.contains(lost[i] - 1)) {
                success++;
                set.remove(lost[i] - 1);
                visited[i] = true;
                continue;
            }
            if(set.contains(lost[i] + 1)) {
                success++;
                set.remove(lost[i] + 1);
                visited[i] = true;
            }
        }
        
        return n - lost.length + success;
    }
}