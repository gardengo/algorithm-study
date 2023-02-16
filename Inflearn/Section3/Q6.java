package Section3;

import java.util.Scanner;

public class Q6 { //최대 길이 연속부분수열
    public int solution(int n, int k, int[] arr) {
        int answer = 0, lp = 0, cnt = 0;
        for (int rp = 0; rp < n; rp++) {
            if (arr[rp] == 0) cnt++;
            while (cnt > k) {
                if (arr[lp] == 0) cnt--;
                lp++;
            }
            answer = Math.max(answer, rp - lp + 1);
        }
        return answer;
    }

    public static void main(String[] args) {
        Q6 T = new Q6();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        System.out.print(T.solution(n, k, arr));
    }
}
