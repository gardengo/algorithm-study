package Section1;

import java.util.Scanner;

public class Q11 { //문자열 압축
    public String solution(String str) {
        String answer = "";
        char[] ch = str.toCharArray();
        int i = 0;
        while (i < ch.length) {
            int count = 0;
            for (int j = i; j < ch.length; j++) {
                if (ch[i] == ch[j]) count++;
                else break;
            }
            if (count == 1) answer += ch[i];
            else answer = answer + ch[i] + count;
            i += count;
        }
        return answer;
    }

    public static void main(String[] args) {
        Q11 T = new Q11();
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        System.out.println(T.solution(str));
    }
}
