package Section2;

import java.util.Scanner;

public class Q10 { //봉우리
    public int solution(int n, int[][] arr) {
        int answer = 0;
        int[][] new_arr = new int[n + 2][n + 2];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                new_arr[i + 1][j + 1] = arr[i][j];

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= n; j++) {
                if (new_arr[i][j] > new_arr[i - 1][j]
                        && new_arr[i][j] > new_arr[i][j - 1]
                        && new_arr[i][j] > new_arr[i + 1][j]
                        && new_arr[i][j] > new_arr[i][j + 1])
                    answer++;
            }
        return answer;
    }

    public static void main(String[] args) {
        Q10 T = new Q10();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                arr[i][j] = sc.nextInt();
        System.out.print(T.solution(n, arr));
    }
}
