import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        // 트리셋에 음식을 넣어 오름차순으로 정렬
        PriorityQueue<Long> pq = new PriorityQueue<Long>();
        for(int i = 0; i < scoville.length; i++)
            pq.add((long)scoville[i]);
        
        // 가장 낮은 스코빌 지수가 K 이상일 때까지 음식섞기
        long min = pq.poll();
        while(min < K) {
            if(pq.isEmpty())
                return -1;
            long second = pq.poll();
            long result = min + second * 2;
            pq.add(result);
            answer++;
            min = pq.poll();
        }
        
        return answer;
    }
}