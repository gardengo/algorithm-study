import java.util.*;
import java.io.*;

class Solution {
    public long solution(int n, int[] times) {
        long lt = 0;
        long rt = Long.MAX_VALUE;
        long mid, output;
        
        while (lt < rt) {
            mid = lt / 2 + rt / 2;
            if (lt % 2 == 1 && rt % 2 == 1)
                mid++;
            output = search(mid, times);
            
            if (output >= n) {
                rt = mid;
            } else {
                lt = mid + 1;
            }
        }
        
        return rt;
    }
    
    static long search(long cur, int[] times) {
        long sum = 0;
        for (int time : times)
            sum += cur / time;
        if (sum < 0)
            sum = Long.MAX_VALUE;
        return sum;
    }
}