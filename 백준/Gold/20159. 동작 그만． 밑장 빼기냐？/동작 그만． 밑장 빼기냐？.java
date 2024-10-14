import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] odd_sum = new int[N / 2 + 1];
        int[] even_sum = new int[N / 2 + 1];
        int last = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (i == N - 1)
                last = num;

            if (i % 2 == 0) {
                int idx = i / 2 + 1;
                odd_sum[idx] = num;
                if (idx > 1)
                    odd_sum[idx] += odd_sum[idx - 1];
            } else {
                int idx = (i - 1) / 2 + 1;
                even_sum[idx] = num;
                if (idx > 1)
                    even_sum[idx] += even_sum[idx - 1];
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                int idx = i / 2;
                answer = Math.max(answer, odd_sum[idx] + even_sum[N / 2] - even_sum[idx]);
            } else {
                int idx = (i - 1) / 2 + 1;
                answer = Math.max(answer, odd_sum[idx] + even_sum[N / 2] - even_sum[idx - 1] - last);
            }
        }

        System.out.println(answer);
    }
}
