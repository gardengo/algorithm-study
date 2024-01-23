import java.util.*;

class Solution {
    static HashSet<Integer> set;
    
    public int solution(String numbers) {
        set = new HashSet<>();
        boolean[] visited = new boolean[numbers.length()];
        comb(numbers, "", 0, visited); 
        
        return set.size();
    }
    
    public void comb(String numbers, String result, int depth, boolean[] visited) {
        if(depth == numbers.length()) {
            if(result != "" && isPrime(Integer.parseInt(result)))
                set.add(Integer.parseInt(result));
            return;
        }
        
        comb(numbers, result, depth + 1, visited);
        for(int i = 0; i < numbers.length(); i++) {
            if(visited[i])
                continue;
            visited[i] = true;
            comb(numbers, result + numbers.charAt(i), depth + 1, visited);
            visited[i] = false;
        }
    }

    
    public boolean isPrime(int num) {
        if(num == 0 || num == 1)
            return false;
        for(int i = 2; i <= Math.sqrt(num); i++) {
            if(num % i == 0)
                return false;
        }
        return true;
    }
}