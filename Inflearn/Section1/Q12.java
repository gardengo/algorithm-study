package Section1;

import java.util.Scanner;

public class Q12 { //문자열 압축
    public String solution(int num, String str) {
        String answer = "";
        for (int i = 0; i < num; i++) {
            String text = str.substring(i * 7, (i + 1) * 7).replace('#', '1').replace('*', '0');
            int word = Integer.parseInt(text, 2);
            answer += (char) word;
        }
        return answer;
    }

    public static void main(String[] args) {
        Q12 T = new Q12();
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        String str = sc.next();
        System.out.println(T.solution(num, str));
    }
}
