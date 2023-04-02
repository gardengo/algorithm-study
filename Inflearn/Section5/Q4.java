package Section5;

import java.util.Scanner;
import java.util.Stack;

public class Q4 { //후위식 연산(postfix)
    public int solution(String str) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for (char x : str.toCharArray()) {
            if (x == '+') {
                int a = stack.pop();
                int b = stack.pop();
                int c = b + a;
                stack.push(c);
            } else if (x == '-') {
                int a = stack.pop();
                int b = stack.pop();
                int c = b - a;
                stack.push(c);
            } else if (x == '*') {
                int a = stack.pop();
                int b = stack.pop();
                int c = b * a;
                stack.push(c);
            } else if (x == '/') {
                int a = stack.pop();
                int b = stack.pop();
                int c = b / a;
                stack.push(c);
            } else stack.push(x - 48);
        }
        answer = stack.pop();
        return answer;
    }

    public static void main(String[] args) {
        Q4 T = new Q4();
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        System.out.print(T.solution(str));
    }
}
