package Section4;

import java.util.Collections;
import java.util.Scanner;
import java.util.TreeSet;

public class Q5 { //K번째 큰 수
    public int solution(int n, int k, int[] arr) {
        int answer = -1;
        TreeSet<Integer> Tset = new TreeSet<>(Collections.reverseOrder());
        for (int i = 0; i < n - 2; i++)
            for (int j = i + 1; j < n - 1; j++)
                for (int l = j + 1; l < n; l++)
                    Tset.add(arr[i] + arr[j] + arr[l]);
        int cnt = 0;
        for (int x : Tset)
            if (++cnt == k) answer = x;
        return answer;
    }

    public static void main(String[] args) {
        Q5 T = new Q5();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        System.out.print(T.solution(n, k, arr));
    }
}
