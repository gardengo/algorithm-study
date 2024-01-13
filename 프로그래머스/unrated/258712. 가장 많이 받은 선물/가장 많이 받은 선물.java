import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        // 우선 순위를 비교하기 위해서는 선물 기록과 선물지수를 알아야 함
        StringTokenizer st;
        int n = friends.length;
        int[][] history = new int[n][n]; // 선물 기록
        int[] point = new int[n]; // 선물 지수
        int[] present = new int[n]; // 받을 선물
        int answer = 0;

        // 이름을 index로 매핑하기 위해 맵을 만들자
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(int i = 0; i < n; i++){
            String name = friends[i];
            map.put(name, i);
        }

        // gifts 배열에서 하나씩 빼서 history를 채우자
        for(String gift : gifts){
            st = new StringTokenizer(gift);
            int give = map.get(st.nextToken());
            int take = map.get(st.nextToken());
            history[give][take]++;
        }

        // history 테이블에서 (행의 합 - 열의 합)으로 선물 지수를 구할 수 있다
        for(int i = 0; i < n; i++){
            int totalGive = 0;
            int totalTake = 0;
            for(int j = 0; j < n; j++){
                totalGive += history[i][j];
                totalTake += history[j][i];
            }
            point[i] = totalGive - totalTake;
        }

        // 2명씩 조합해서 조건을 비교한다
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                // 선물 기록 비교
                if(history[i][j] > history[j][i]) {
                    present[i]++;
                } else if(history[i][j] < history[j][i]) {
                    present[j]++;
                } else {
                    // 선물 지수 비교
                    if(point[i] > point[j]) {
                        present[i]++;
                    } else if(point[i] < point[j]) {
                        present[j]++;
                    }
                }
            }
        }

        // 받을 선물 중 가장 큰 수를 구하자
        for(int pres : present)
            answer = pres > answer ? pres : answer;

        return answer;
    }
}