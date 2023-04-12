package Section5;

import java.util.Scanner;
import java.util.Stack;

public class Q5 { //쇠막대기
    public int solution(String str) {
        int answer = 0;
        Stack<Character> stack = new Stack<>();
        char p = ' ';
        for (char x : str.toCharArray()) {
            if (x == ')') {
                stack.pop();
                if (p == '(') {
                    answer += stack.size();
                } else {
                    answer++;
                }
                p = x;
            } else {
                stack.push(x);
                p = x;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Q5 T = new Q5();
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        System.out.print(T.solution(str));
    }
}
