package Section3;

import java.util.Scanner;

public class Q5 { //연속된 자연수의 합
    public int solution(int n) {
        int answer = 0, sum = 0;
        int p1 = (n + 1) / 2;
        int p2 = p1 - 1;
        sum = p1 + p2;
        while (p2 > 0) {
            while (sum <= n) {
                if (sum == n) answer++;
                sum += --p2;
                if (p2 == 0) break;
            }
            while (sum > n) {
                sum -= p1--;
                if (sum == n) answer++;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Q5 T = new Q5();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.print(T.solution(n));
    }
}
