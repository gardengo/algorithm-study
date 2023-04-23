package Section6;

import java.util.Arrays;
import java.util.Scanner;

public class Q5 { //중복 확인
    public char solution(int n, int[] arr) {
        char answer = 'U';
        Arrays.sort(arr);
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                answer = 'D';
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Q5 T = new Q5();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        System.out.print(T.solution(n, arr));
    }
}

