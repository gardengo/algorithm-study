import java.util.*;
import java.io.*;

class Solution {
    public ArrayList<Integer> solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();

        int time = 0;
        int count = 0;
        for (int i = 0; i < progresses.length; i++) {
            int cost = (int) Math.ceil((100d - progresses[i]) / speeds[i]) - time;
            if (cost > 0) {
                if (count > 0)
                    answer.add(count);
                time += cost;
                count = 1;
            } else {
                count++;
            }
        }
        answer.add(count);
        
        return answer;
    }
}