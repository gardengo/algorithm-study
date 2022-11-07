package Section1;

import java.util.Scanner;

public class Q6 { //중복문자제거
    public String solution(String str) {
        String answer = "";
        for (int i = 0; i < str.length(); i++)
            if (i == str.indexOf(str.charAt(i)))
                answer += str.charAt(i);
        return answer;
    }

    public static void main(String[] args) {
        Q6 T = new Q6();
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        System.out.println(T.solution(str));
    }
}
