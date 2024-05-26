import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] top = new int[H + 1];
        int[] bottom = new int[H + 1];
        for (int i = 0; i < N / 2; i++) {
            int obs1 = Integer.parseInt(br.readLine());
            int obs2 = Integer.parseInt(br.readLine());
            bottom[obs1]++;
            top[obs2]++;
        }

        int sum = 0;
        for (int i : bottom)
            sum += i;
        int min = sum;
        int cnt = 1;

        for (int height = 2; height <= H; height++) {
            sum -= bottom[height - 1];
            sum += top[H - height + 1];
            if (min > sum) {
                min = sum;
                cnt = 1;
            } else if (min == sum) {
                cnt++;
            }
        }

        System.out.println(min + " " + cnt);
    }
}
