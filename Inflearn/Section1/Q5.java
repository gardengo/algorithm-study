package Section1;

import java.util.Scanner;

public class Q5 { //특정 문자 뒤집기
    public String solution(String str) {
        String answer = "";
        char[] ch = str.toCharArray();
        int lt = 0;
        int rt = str.length() - 1;
        while (lt < rt) {
            if (Character.isAlphabetic(ch[lt]) == false) {
                lt++;
            } else if (Character.isAlphabetic(ch[rt]) == false) {
                rt--;
            } else {
                char tmp = ch[lt];
                ch[lt] = ch[rt];
                ch[rt] = tmp;
                lt++;
                rt--;
            }
        }
        answer = String.valueOf(ch);
        return answer;
    }

    public static void main(String[] args) {
        Q5 T = new Q5();
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        System.out.println(T.solution(str));
    }
}
