import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N + 1];
        int[] cnt = new int[M];
        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken()) % M;
            sum = (sum + arr[i]) % M;
            cnt[sum]++;
        }

        long answer = 0;
        for (int i = 1; i <= N; i++) {
            answer += cnt[0];
            cnt[arr[i]]--;
            moveArr(cnt, arr[i], M);
        }

        System.out.println(answer);
    }

    private static void moveArr(int[] cnt, int n, int M) {
        int[] tmp = new int[n];
        for (int i = 0; i < n; i++)
            tmp[i] = cnt[i];

        for (int i = 0; i < M - n; i++)
            cnt[i] = cnt[i + n];
        for (int i = 0; i < n; i++)
            cnt[i + M - n] = tmp[i];
    }
}