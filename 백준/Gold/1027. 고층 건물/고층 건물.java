import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        long[] building = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            building[i] = Long.parseLong(st.nextToken());

        int[] count = new int[N];
        double maxAngle = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            maxAngle = Integer.MIN_VALUE;
            for (int j = i + 1; j < N; j++) {
                double curAngle = (double) (building[j] - building[i]) / (j - i);
                if (curAngle > maxAngle) {
                    count[i]++;
                    maxAngle = curAngle;
                }
            }
            maxAngle = Integer.MIN_VALUE;
            for (int j = i - 1; j >= 0; j--) {
                double curAngle = (double) (building[j] - building[i]) / (i - j);
                if (curAngle > maxAngle) {
                    count[i]++;
                    maxAngle = curAngle;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++)
            answer = Math.max(answer, count[i]);
        sb.append(answer);

        System.out.println(sb);
    }
}
