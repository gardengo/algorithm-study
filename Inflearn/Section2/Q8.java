package Section2;

import java.util.Scanner;

public class Q8 { //등수구하기
    public int[] solution(int n, int[] arr) {
        int[] answer = new int[n];
        for (int i = 0; i < n; i++) {
            int count = 1;
            for (int j = 0; j < n; j++)
                if (arr[i] < arr[j]) count++;
            answer[i] = count;
        }
        return answer;
    }

    public static void main(String[] args) {
        Q8 T = new Q8();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        for (int x : T.solution(n, arr)) System.out.print(x + " ");
    }
}
