package Section2;

import java.util.ArrayList;
import java.util.Scanner;

public class Q3 { //가위 바위 보
    public ArrayList<Character> solution(int n, int[] A_info, int[] B_info) {
        ArrayList<Character> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) answer.add(RPC(A_info[i], B_info[i]));
        return answer;
    }

    public char RPC(int a, int b) {
        char result = 'A';
        if (a == b) result = 'D';
        else if (a == 1 && b == 2) result = 'B';
        else if (a == 2 && b == 3) result = 'B';
        else if (a == 3 && b == 1) result = 'B';
        return result;
    }

    public static void main(String[] args) {
        Q3 T = new Q3();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] A_info = new int[n];
        int[] B_info = new int[n];
        for (int i = 0; i < n; i++) A_info[i] = sc.nextInt();
        for (int i = 0; i < n; i++) B_info[i] = sc.nextInt();
        for (char x : T.solution(n, A_info, B_info)) System.out.println(x);
    }
}
