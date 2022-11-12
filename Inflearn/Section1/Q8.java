package Section1;

import java.util.Scanner;

public class Q8 { //유효한 팰린드롬
    public String solution(String str) {
        String answer = "YES";
        str = str.toLowerCase().replaceAll("[^a-z]", "");
        String rev = new StringBuilder(str).reverse().toString();
        if (!str.equals(rev)) answer = "NO";
        return answer;
    }

    public static void main(String[] args) {
        Q8 T = new Q8();
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(T.solution(str));
    }
}
