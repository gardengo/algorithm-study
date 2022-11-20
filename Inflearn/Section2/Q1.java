package Section2;

import java.util.Scanner;

public class Q1 { //큰 수 출력하기
    public String solution(int n, int[] num) {
        String answer = "";
        int x = 0;
        for (int i = 0; i < n; i++) {
            if (num[i] > x) answer += num[i] + " ";
            x = num[i];
        }
        return answer;
    }

    public static void main(String[] args) {
        Q1 T = new Q1();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] num = new int[n];
        for (int i = 0; i < n; i++) num[i] = sc.nextInt();
        System.out.println(T.solution(n, num));
    }
}
