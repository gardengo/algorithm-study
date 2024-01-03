import java.util.*;

public class Solution {
    public ArrayList<Integer> solution(int []arr) {
        ArrayList<Integer> answer = new ArrayList<Integer>();
        int before = -1;
        for(int i = 0; i < arr.length; i++) {
            if(before != arr[i])
                answer.add(arr[i]);
            before = arr[i];
        }

        return answer;
    }
}