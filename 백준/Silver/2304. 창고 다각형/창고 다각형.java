import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<int[]> list = new ArrayList<>();
        int highL = 0;
        int highH = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            list.add(new int[]{L, H});
            if (highH < H) {
                highL = L;
                highH = H;
            }
        }
        list.sort((a, b) -> a[0] - b[0]);

        int answer = highH;
        int i = 0;

        int beforeL = 0;
        int beforeH = 0;
        for (; i < N; i++) {
            int[] cur = list.get(i);
            if (cur[1] >= beforeH) {
                answer += (cur[0] - beforeL) * beforeH;
                beforeL = cur[0];
                beforeH = cur[1];
            }
            if (cur[0] == highL) {
                break;
            }
        }

        beforeL = 1000;
        beforeH = 0;
        for (int j = N - 1; j >= i; j--) {
            int[] cur = list.get(j);
            if (cur[1] >= beforeH) {
                answer += (beforeL - cur[0]) * beforeH;
                beforeL = cur[0];
                beforeH = cur[1];
            }
            if (cur[0] == highL) {
                break;
            }
        }

        System.out.println(answer);
    }
}