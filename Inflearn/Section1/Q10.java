package Section1;

import java.util.Scanner;

public class Q10 { //가장 짧은 문자거리
    public String solution(String str, char ch) {
        String answer = "";
        for (int i = 0; i < str.length(); i++) {
            int po1 = 0, po2 = 0, tmp;
            while (ch != str.charAt(i + po1)) {
                if (i + po1 < str.length()) po1++;
                if (i + po1 == str.length()) {
                    po1 = str.length();
                    break;
                }
            }
            while (ch != str.charAt(i - po2)) {
                if (i - po2 > 0) po2++;
                if (i - po2 == 0 && ch != str.charAt(i - po2)) {
                    po2 = str.length();
                    break;
                }
            }
            if (po1 < po2) tmp = po1;
            else tmp = po2;
            answer = answer + Integer.toString(tmp) + " ";
        }
        return answer;
    }

    public static void main(String[] args) {
        Q10 T = new Q10();
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        char ch = sc.next().charAt(0);
        System.out.println(T.solution(str, ch));
    }
}
