import java.util.*;
import java.io.*;

class Solution {
    public int solution(String name) {
        int length = name.length();
        int sum1 = -1;
        int sum2 = -1;
        int sum3 = -1;
        
        for (int i = length - 1; i > 0; i--) {
            if (name.charAt(i) == 'A') {
                sum1--;
            } else {
                break;
            }
        }
        
        for (int i = 1; i < length; i++) {
            if (name.charAt(i) == 'A') {
                sum2--;
            } else {
                break;
            }
        }
        
        int mcnt = 0;
        int ccnt = 0;
        int end = 0;
        for (int i = 1; i < length - 1; i++) {
            if (name.charAt(i) == 'A') {
                ccnt++;
                if (mcnt < ccnt) {
                    mcnt = ccnt;
                    end = i;
                }
            } else {
                ccnt = 0;
            }
        }
        sum3 -= mcnt;
        int start = end - mcnt + 1;
        
        for (int i = 0; i < length; i++) {
            char ch = name.charAt(i);
            sum1 += Math.min(ch - 'A', 26 - ch + 'A') + 1;
            sum2 += Math.min(ch - 'A', 26 - ch + 'A') + 1;
            sum3 += Math.min(ch - 'A', 26 - ch + 'A') + 1;
        }
        sum3 += Math.min(start - 1, length - end - 1);
        
        return Math.min(Math.min(sum1, sum2), sum3);
    }
}