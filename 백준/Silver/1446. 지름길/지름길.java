import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            if (end > D)
                continue;
            if (end - start < dist)
                continue;
            pq.offer(new int[]{start, end, dist});
        }

        int[] dp = new int[D + 1];
        for (int i = 1; i <= D; i++)
            dp[i] = i;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (dp[cur[0]] + cur[2] < dp[cur[1]]) {
                dp[cur[1]] = dp[cur[0]] + cur[2];
                for (int i = cur[1] + 1; i <= D; i++) {
                    dp[i] = Math.min(dp[i - 1] + 1, dp[i]);
                }
            }
        }

        System.out.println(dp[D]);
    }
}
