package Section5;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q7 { //교육과정 설계
    public String solution(String st1, String st2) {
        String answer = "YES";
        Queue<Character> Q = new LinkedList<>();
        for (char x : st1.toCharArray()) Q.offer(x);
        for (char x : st2.toCharArray()) {
            if (Q.contains(x)) {
                if (Q.peek() == x) Q.poll();
                else return "NO";
            }
        }
        if (!Q.isEmpty()) return "NO";
        return answer;
    }

    public static void main(String[] args) {
        Q7 T = new Q7();
        Scanner sc = new Scanner(System.in);
        String st1 = sc.next();
        String st2 = sc.next();
        System.out.print(T.solution(st1, st2));
    }
}
