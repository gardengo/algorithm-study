package Section5;

import java.util.Scanner;
import java.util.Stack;

public class Q2 { //괄호문자제거
    public String solution(String str) {
        String answer = "";
        Stack<Character> stack = new Stack<>();
        for (char x : str.toCharArray()) {
            if (x == ')') {
                while (stack.pop() != '(') ;
            } else stack.push(x);
        }
        for (char x : stack) answer += x;
        return answer;
    }

    public static void main(String[] args) {
        Q2 T = new Q2();
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        System.out.print(T.solution(str));
    }
}
