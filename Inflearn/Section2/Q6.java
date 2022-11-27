package Section2;

import java.util.ArrayList;
import java.util.Scanner;

public class Q6 { //뒤집은 소수
    public boolean isPrime(int num) {
        if (num == 1) return false;
        for (int i = 2; i < num; i++)
            if (num % i == 0) return false;
        return true;
    }

    public ArrayList<Integer> solution(int n, int[] arr) {
        ArrayList<Integer> answer = new ArrayList<>();
        int[] rev = new int[n];
        for (int i = 0; i < n; i++) {
            String tmp = new StringBuffer(Integer.toString(arr[i])).reverse().toString();
            rev[i] = Integer.parseInt(tmp);
            if (isPrime(rev[i])) answer.add(rev[i]);
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
