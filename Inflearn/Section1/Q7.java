package Section1;

import java.util.Scanner;

public class Q7 { //회문 문자열
    public String solution(String str) {
        String answer = "YES";
        char ch[] = str.toLowerCase().toCharArray();
        for (int i = 0; i < ch.length / 2; i++)
            if (ch[i] != ch[ch.length - i - 1])
                answer = "NO";
        return answer;
    }

    public static void main(String[] args) {
        Q7 T = new Q7();
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        System.out.println(T.solution(str));
    }
}
