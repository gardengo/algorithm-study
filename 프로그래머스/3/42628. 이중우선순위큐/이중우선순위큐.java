import java.util.*;
import java.io.*;

class Solution {
    public int[] solution(String[] operations) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        for (String oper : operations) {
            char order = oper.charAt(0);
            int num = Integer.parseInt(oper.split(" ")[1]);
            
            if (order == 'I') {
                if (map.containsKey(num)) {
                    map.put(num, map.get(num) + 1);
                } else {
                    map.put(num, 1);
                }
            } else {
                if (map.isEmpty())
                    continue;
                if (num == 1) {
                    int last = map.lastKey();
                    if (map.get(last) == 1) {
                        map.remove(last);
                    } else {
                        map.put(last, map.get(last) - 1);
                    }
                } else {
                    int first = map.firstKey();
                    if (map.get(first) == 1) {
                        map.remove(first);
                    } else {
                        map.put(first, map.get(first) - 1);
                    }
                }
            }
        }
        
        int[] answer = new int[2];
        if (!map.isEmpty()){
            answer[0] = map.lastKey();
            answer[1] = map.firstKey();
        }
        
        return answer;
    }
}