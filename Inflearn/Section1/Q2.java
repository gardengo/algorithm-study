package Section1;

import java.util.Scanner;

public class Q2 { //대소문자 변환

    public String solution(String st) {
        String answer = "";
        for (char x : st.toCharArray()) {
            if (Character.isLowerCase(x)) answer += Character.toUpperCase(x);
            else answer += Character.toLowerCase(x);
        }
        return answer;
    }

    public static void main(String[] args) {
        Q2 T = new Q2();
        Scanner sc = new Scanner(System.in);
        String st = sc.nextLine();
        System.out.println(T.solution(st));
    }
}
