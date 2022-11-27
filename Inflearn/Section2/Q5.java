package Section2;

import java.util.Scanner;

public class Q5 { //소수(에라토스테네스 체)
    public int solution(int n) {
        int answer = 0;
        int[] ch = new int[n + 1];
        for (int i = 2; i <= n; i++)
            if (ch[i] == 0) {
                answer++;
                for (int j = i; j <= n; j = j + i) ch[j] = 1;
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
