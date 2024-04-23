import java.io.*;
import java.util.*;

public class Main {
    private static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++)
                pq.add(Long.parseLong(st.nextToken()));

            long sum = 1;
            while (!pq.isEmpty()) {
                long first = pq.poll();
                if (pq.isEmpty())
                    break;
                long second = pq.poll();
                long mix = first * second;
                pq.add(mix);
                sum = (sum * (mix % MOD)) % MOD;
            }

            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }
}