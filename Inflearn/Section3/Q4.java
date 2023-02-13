package Section3;

import java.util.Scanner;

public class Q4 { //연속 부분수열
    public int solution(int n, int m, int[] arr) {
        int answer = 0, sum = 0, p1 = 0;
        for (int p2 = 0; p2 < n; p2++) {
            sum += arr[p2];
            while (sum > m) sum -= arr[p1++];
            if (sum == m) answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        Q4 T = new Q4();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        System.out.print(T.solution(n, m, arr));
    }
}
