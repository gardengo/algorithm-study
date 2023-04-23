package Section6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Q6 { //장난꾸러기
    public ArrayList<Integer> solution(int n, int[] arr) {
        int[] tmp = arr.clone();
        Arrays.sort(tmp);
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (arr[i] != tmp[i]) answer.add(i + 1);
        }
        return answer;
    }

    public static void main(String[] args) {
        Q6 T = new Q6();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();
        for (int x : T.solution(n, arr)) System.out.print(x + " ");
    }
}

