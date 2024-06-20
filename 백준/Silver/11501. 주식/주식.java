import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            int[] max = new int[N];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                for (int j = i; j >= 0; j--) {
                    if (arr[i] <= max[j])
                        break;
                    max[j] = arr[i];
                }
            }

            long sum = 0;
            for (int i = 0; i < N; i++)
                sum += max[i] - arr[i];
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }

}