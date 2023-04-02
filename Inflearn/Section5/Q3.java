package Section5;

import java.util.Scanner;
import java.util.Stack;

public class Q3 { //크레인 인형뽑기(카카오)
    public int solution(int n, int m, int[][] board, int[] moves) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        for (int x : moves) {
            for (int i = 0; i < n; i++) {
                if (board[i][x - 1] != 0) {
                    if (!stack.isEmpty() && stack.peek() == board[i][x - 1]) {
                        stack.pop();
                        answer += 2;
                    } else stack.push(board[i][x - 1]);
                    board[i][x - 1] = 0;
                    break;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        Q3 T = new Q3();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] board = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = sc.nextInt();
        int m = sc.nextInt();
        int[] moves = new int[m];
        for (int i = 0; i < m; i++) moves[i] = sc.nextInt();
        System.out.print(T.solution(n, m, board, moves));
    }
}
