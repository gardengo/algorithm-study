package Section1;

import java.util.Scanner;

public class Q12 { //문자열 압축
    public String solution(int num, String str) {
        String answer = "";
        String binary = "";
        for (char x : str.toCharArray()) {
            if (x == '#') binary += '1';
            else if (x == '*') binary += '0';
        }
        for (int i = 0; i < num; i++) {
            String text = binary.substring(i * 7, (i + 1) * 7);
            int word = 0;
            for (int j = 0; j < 7; j++)
                word += Math.pow(2, (6 - j)) * Integer.parseInt(Character.toString(text.charAt(j)));
            answer += (char)word;
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
