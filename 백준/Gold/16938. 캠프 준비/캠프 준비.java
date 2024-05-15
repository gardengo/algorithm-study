import java.io.*;
import java.util.*;

public class Main {
    private static int N, L, R, X, answer;
    private static int[] A;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());

        answer = 0;
        solution(0, 0, 0, 1000000);
        System.out.println(answer);
    }

    private static void solution(int index, long sum, int top, int low) {
        if (index == N) {
            if (L <= sum && sum <= R && top - low >= X)
                answer++;
            return;
        }
        solution(index + 1, sum, top, low);
        solution(index + 1, sum + A[index], Math.max(top, A[index]), Math.min(low, A[index]));
    }
}
