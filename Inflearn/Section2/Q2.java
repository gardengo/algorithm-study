package Section2;

import java.util.Scanner;

public class Q2 { //보이는 학생
    public int solution(int n, int[] arr) {
        int answer = 0;
        int max = 0;
        for (int i = 0; i < n; i++)
            if (max < arr[i]) {
                max = arr[i];
                answer++;
            }
        return answer;
    }

    public static void main(String[] args) {
        Q2 T = new Q2();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        System.out.print(T.solution(n, arr));
    }
}
