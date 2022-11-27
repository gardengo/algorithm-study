package Section2;

import java.util.ArrayList;
import java.util.Scanner;

public class Q4 { //피보나치 수열
    public ArrayList<Integer> solution(int n) {
        ArrayList<Integer> answer = new ArrayList<>();
        answer.add(1);
        answer.add(1);
        for (int i = 2; i < n; i++) answer.add(answer.get(i - 2) + answer.get(i - 1));
        return answer;
    }

    public static void main(String[] args) {
        Q4 T = new Q4();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int x : T.solution(n)) System.out.print(x + " ");
    }
}
