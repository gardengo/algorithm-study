import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int k = (int) (Math.log(N / 3) / Math.log(2));

        char[][] arr = new char[N][2 * N - 1];
        for (int i = 0; i < N; i++)
            Arrays.fill(arr[i], ' ');

        for (int i = 0; i < 5; i++)
            arr[0][i] = '*';
        arr[1][1] = '*';
        arr[1][3] = '*';
        arr[2][2] = '*';

        recursion(k, 0, arr);

        StringBuilder answer = new StringBuilder();
        for (int i = N - 1; i >= 0; i--) {
            for (int j = 2 * N - 2; j >= 0; j--)
                answer.append(arr[i][j]);
            answer.append("\n");
        }

        System.out.println(answer);
    }

    private static void recursion(int k, int r, char[][] arr) {
        if (k == r)
            return;

        int size = 3 * (int) Math.pow(2, r);
        for (int i = 0; i < size; i++) {
            for (int j = 2 * size; j < 4 * size - 1; j++)
                arr[i][j] = arr[i][j - 2 * size];
        }
        for (int i = size; i < 2 * size; i++) {
            for (int j = size; j < 3 * size - 1; j++)
                arr[i][j] = arr[i - size][j - size];
        }

        recursion(k, r + 1, arr);
    }
}