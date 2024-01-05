class Solution {
    static int answer;
    
    public int solution(int[] numbers, int target) {
        answer = 0;
        // 방법의 수를 모두 찾아야하므로 완탐
        // DFS를 사용해서 백트래킹을 하자
        int remain = 0;
        for(int i = 0; i < numbers.length; i++)
            remain += numbers[i];   
        dfs(numbers, target, 0, 0, remain);
        
        return answer;
    }
    
    static void dfs(int[] numbers, int target, int sum, int cur, int remain) {
        // 마지막 숫자까지 도착하면 타겟과 비교하고 종료
        if(cur == numbers.length) {
            if(target == sum)
                answer++;
            return;
        }
        
        // 현재까지의 합이 타겟보다 낮은데 남은 수를 더해도 낮거나
        // 현재까지의 합이 타겟보다 큰데 남은 수를 빼도 큰 경우 멈춘다
        if(sum < target && sum + remain < target)
            return;
        if(sum > target && sum - remain > target)
            return;
        
        // 현재의 수를 합에 더하고 빼준다
        dfs(numbers, target, sum + numbers[cur], cur + 1, remain - numbers[cur]);
        dfs(numbers, target, sum - numbers[cur], cur + 1, remain - numbers[cur]);
    }
}