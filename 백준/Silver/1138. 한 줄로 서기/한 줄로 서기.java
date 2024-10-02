import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            int num = Integer.parseInt(st.nextToken());

            int cnt = 0;
            for (int j = 0; j < N; j++) {
                if (arr[j] == 0)
                    cnt++;
                if (num + 1 == cnt) {
                    arr[j] = i;
                    break;
                }
            }
        }

        for (int i = 0; i < N; i++)
            sb.append(arr[i]).append(" ");
        System.out.println(sb);
    }
}
