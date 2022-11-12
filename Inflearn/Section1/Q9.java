package Section1;

import java.util.Scanner;

public class Q9 { //숫자만 추출
    public int solution(String str) {
        String answer = "";
        for(char x: str.toCharArray())
            if(Character.isDigit(x)) answer += x;
        return Integer.parseInt(answer);
    }

    public static void main(String[] args) {
        Q9 T = new Q9();
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(T.solution(str));
    }
}
