import java.util.*;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> map = new HashMap<>();
        
        for(int i = 0; i < completion.length; i++) {
            if(map.containsKey(completion[i])) {
                int cnt = map.get(completion[i]);
                map.put(completion[i], cnt + 1);
            } else {
                map.put(completion[i], 1);
            }
        }
        
        for(int i = 0; i < participant.length; i++) {
            if(map.containsKey(participant[i])) {
                int cnt = map.get(participant[i]);
                if(cnt == 1)
                    map.remove(participant[i]);
                else
                    map.put(participant[i], cnt - 1);
            } else {
                return participant[i];
            }
        }
        
        return answer;
    }
}