import java.util.*;
import java.io.*;

class Solution {
    static char[] arr = {'A', 'E', 'I', 'O', 'U'};
    static int cnt;
    static boolean flag;
    
    public int solution(String word) {
        cnt = -1;
        flag = false;
        
        recur(word, 0);
        
        return cnt;
    }
    
    static void recur(String word, int num) {
        if (flag)
            return;
        if (num > 55555)
            return;
        cnt++;
    
        if(num > 0) {
            StringBuilder sb = new StringBuilder();
            String numStr = String.valueOf(num);
            
            for (int i = 0; i < numStr.length(); i++)
                sb.append(arr[numStr.charAt(i) - '1']);
                
            if (word.equals(sb.toString())) {
                flag = true;
                return;
            }
        }
        
        for (int i = 1; i <= 5; i++)
            recur(word, num * 10 + i);
    }
}