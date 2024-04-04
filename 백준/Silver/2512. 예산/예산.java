import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int max = 0;
        int min = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        int M = Integer.parseInt(br.readLine());

        int lt = min;
        int rt = max + 1;
        while (lt < rt) {
            int mid = (lt + rt) / 2;

            int add = 0;
            for (int i = 0; i < N; i++) {
                if (arr[i] <= mid)
                    add += arr[i];
                else
                    add += mid;
            }

            if (add > M) {
                rt = mid;
            } else {
                lt = mid + 1;
            }
        }

        System.out.println(lt - 1);
    }
}