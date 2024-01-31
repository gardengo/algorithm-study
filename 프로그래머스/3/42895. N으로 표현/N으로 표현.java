import java.util.*;

class Solution {
    public int solution(int N, int number) {
        HashMap<Integer, Integer> map = new HashMap<>();
        calc(N, 0, 0, map);
        
        int answer = -1;
        if(map.containsKey(number)) 
            answer = map.get(number);
        if(answer > 8)
            answer = -1;
        
        return answer;
    }
    
    public void calc(int N, int cur, int cnt, HashMap<Integer, Integer> map) {
        if(cnt > 8)
            return;
        
        if(map.containsKey(cur)) {
            int before = map.get(cur);
            map.put(cur, Math.min(before, cnt));
        } else {
            map.put(cur, cnt);
        }
        
        int num = 0;
        for(int i = 0; i < 8; i++) {
            num += N * Math.pow(10, i);
            calc(N, cur + num, cnt + i + 1, map);
            calc(N, cur - num, cnt + i + 1, map);
            calc(N, cur * num, cnt + i + 1, map);
            calc(N, cur / num, cnt + i + 1, map);
        }
    }
}