import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        int[] sum = new int[d + 1];
        int cnt = 1;
        sum[c]++;
        int answer = 0;

        for (int i = 0; i < k; i++) {
            int cur = Integer.parseInt(br.readLine());
            arr[i] = cur;
            if (sum[cur] == 0)
                cnt++;
            sum[cur]++;

            answer = Math.max(answer, cnt);
        }

        for (int i = k; i < N; i++) {
            int cur = Integer.parseInt(br.readLine());
            arr[i] = cur;
            if (sum[cur] == 0)
                cnt++;
            sum[cur]++;

            int before = arr[i - k];
            sum[before]--;
            if (sum[before] == 0)
                cnt--;

            answer = Math.max(answer, cnt);
        }

        for (int i = 0; i < k; i++) {
            int cur = arr[i];
            if (sum[cur] == 0)
                cnt++;
            sum[cur]++;

            int before = arr[N - k + i];
            sum[before]--;
            if (sum[before] == 0)
                cnt--;

            answer = Math.max(answer, cnt);
        }

        System.out.println(answer);
    }
}
