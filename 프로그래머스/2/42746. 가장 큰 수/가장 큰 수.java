import java.util.*;
import java.io.*;

class Solution {
    public String solution(int[] numbers) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
            int sola = a * (int) Math.pow(10, String.valueOf(b).length()) + b;
            int solb = b * (int) Math.pow(10, String.valueOf(a).length()) + a;
            return solb - sola;
        });
        
        for (int i = 0; i < numbers.length; i++)
            pq.add(numbers[i]);
        
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty())
            sb.append(String.valueOf(pq.poll()));
        
        String answer = sb.toString();
        if (answer.charAt(0) == '0')
            answer = "0";
        
        return answer;
    }
}