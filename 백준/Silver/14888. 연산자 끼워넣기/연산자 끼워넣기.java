import java.io.*;
import java.util.*;

public class Main {
    static int N, min, max;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        int[] op = new int[4];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            arr[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++)
            op[i] = Integer.parseInt(st.nextToken());

        // dfs로 완탐 ㄱㄱ
        max = -1000000000;
        min = 1000000000;
        dfs(1, arr[0], op);

        System.out.println(max);
        System.out.println(min);
    }

    public static void dfs(int depth, int sum, int[] op) {
        if (depth == N) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
        }
        for (int d = 0; d < 4; d++) {
            if (op[d] == 0)
                continue;
            int[] op2 = Arrays.copyOf(op, 4);
            op2[d]--;

            if (d == 0) {
                dfs(depth + 1, sum + arr[depth], op2);
            } else if (d == 1) {
                dfs(depth + 1, sum - arr[depth], op2);
            } else if (d == 2) {
                dfs(depth + 1, sum * arr[depth], op2);
            } else {
                dfs(depth + 1, sum / arr[depth], op2);
            }
        }
    }
}
