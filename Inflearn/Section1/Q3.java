package Section1;

import java.util.Scanner;

public class Q3 { //문장 속 단어

    public String solution(String st) {
        String answer = "";
        int m = 0;
        String[] s = st.split(" ");
        for (String x : s) {
            int len = x.length();
            if (m < len) {
                m = len;
                answer = x;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Q3 T = new Q3();
        Scanner sc = new Scanner(System.in);
        String st = sc.nextLine();
        System.out.println(T.solution(st));
    }
}
