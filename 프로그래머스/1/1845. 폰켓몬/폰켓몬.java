import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int N = nums.length;
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < N; i++)
            set.add(nums[i]);
        int size = set.size();
        if(size < N / 2)
            return size;
        else
            return N / 2;
    }
}