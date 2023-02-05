package Section2;

import java.util.Scanner;

public class Q11 { //임시반장 정하기
    public int solution(int n, int[][] arr) {
        int answer = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++)
                for (int k = 0; k < 5; k++)
                    if (arr[i][k] == arr[j][k]) {
                        cnt++;
                        break;
                    }
            if (max < cnt) {
                max = cnt;
                answer = i + 1;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Q11 T = new Q11();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][5];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < 5; j++)
                arr[i][j] = sc.nextInt();
        System.out.print(T.solution(n, arr));
    }
}
