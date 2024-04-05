import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] cnt = new int[M];
        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken()) % M;
            sum = (sum + num) % M;
            cnt[sum]++;
        }

        long answer = cnt[0];
        for (int i = 0; i < M; i++) {
            answer += (long) cnt[i] * (cnt[i] - 1) / 2;
        }

        System.out.println(answer);
    }
}