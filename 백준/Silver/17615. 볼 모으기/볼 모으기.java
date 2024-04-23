import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        int[] cnt = new int[4];
        boolean[] visited = new boolean[4];

        for (int i = 0; i < N; i++) {
            if (str.charAt(i) == 'B')
                visited[0] = true;
            if (str.charAt(i) == 'R')
                visited[1] = true;
            if (str.charAt(N - 1 - i) == 'B')
                visited[2] = true;
            if (str.charAt(N - 1 - i) == 'R')
                visited[3] = true;

            if (str.charAt(i) == 'R' && visited[0]) {
                cnt[0]++;
            }
            if (str.charAt(i) == 'B' && visited[1]) {
                cnt[1]++;
            }
            if (str.charAt(N - 1 - i) == 'R' && visited[2]) {
                cnt[2]++;
            }
            if (str.charAt(N - 1 - i) == 'B' && visited[3]) {
                cnt[3]++;
            }
        }

        int answer = 1000000;
        for (int i = 0; i < 4; i++) {
            answer = Math.min(answer, cnt[i]);
        }

        System.out.println(answer);
    }
}