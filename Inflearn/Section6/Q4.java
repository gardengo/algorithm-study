package Section6;

import java.util.Scanner;

public class Q4 { //Least Recently Used
    public int[] solution(int s, int n, int[] arr) {
        int[] Cache = new int[s];
        for (int x : arr) {
            //Cache Hit
            for (int i = 0; i < s; i++) {
                if (x == Cache[i]) {
                    for (int j = i; j > 0; j--)
                        Cache[j] = Cache[j - 1];
                    Cache[0] = x;
                }
            }
            //Cache Miss
            if (x != Cache[0]) {
                for (int i = s - 1; i > 0; i--) {
                    Cache[i] = Cache[i - 1];
                }
                Cache[0] = x;
            }
        }
        return Cache;
    }

    public static void main(String[] args) {
        Q4 T = new Q4();
        Scanner sc = new Scanner(System.in);
        int s = sc.nextInt();
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        for (int x : T.solution(s, n, arr)) System.out.print(x + " ");
    }
}
