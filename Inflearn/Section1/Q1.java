package Section1;

import java.util.Scanner;

public class Q1 { //문자찾기

    public int solution(String st, char ch) {
        int answer = 0;
        st = st.toUpperCase();
        ch = Character.toUpperCase(ch);
//        for (int i = 0; i < st.length(); i++)
//            if (st.charAt(i) == ch) answer++;
        for (char x : st.toCharArray()) if (x == ch) answer++;
        return answer;
    }

    public static void main(String[] args) {
        Q1 T = new Q1();
        Scanner sc = new Scanner(System.in);
        String st = sc.nextLine(); //문자열 입력
        char ch = sc.next().charAt(0); //문자 입력
        System.out.println(T.solution(st, ch));
    }
}
