import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] h = new int[N];
        for (int i = 0; i < N; i++)
            h[i] = Integer.parseInt(br.readLine());

        int[] cnt = new int[N];
        Stack<int[]> stack = new Stack<>(); // 높이, 인덱스
        for (int i = N - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek()[0] < h[i]) {
                int[] cur = stack.pop();
                cnt[i] += cnt[cur[1]] + 1;
            }
            stack.push(new int[]{h[i], i});
        }

        long answer = 0;
        for (int i = 0; i < N; i++)
            answer += cnt[i];

        System.out.println(answer);
    }
}
