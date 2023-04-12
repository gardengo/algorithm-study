package Section5;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Q6 { //공주 구하기
    public int solution(int n, int k) {
        int answer = 0;
        Queue<Integer> Q = new LinkedList<>();
        for (int i = 1; i < n + 1; i++) Q.offer(i);
        while (Q.size() > 1) {
            for (int i = 0; i < k - 1; i++)
                Q.offer(Q.poll());
            Q.poll();
        }
        answer = Q.poll();
        return answer;
    }

    public static void main(String[] args) {
        Q6 T = new Q6();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        System.out.print(T.solution(n, k));
    }
}
