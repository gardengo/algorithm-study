import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());
            int[] alpha = new int[26];
            for (char ch : W.toCharArray())
                alpha[ch - 'a']++;

            int min = 10000;
            int max = 0;
            for (int i = 0; i <= W.length() - K; i++) {
                if (alpha[W.charAt(i) - 'a'] < K)
                    continue;
                int cnt = 0;
                for (int j = 0; j < W.length() - i; j++) {
                    if (W.charAt(i + j) == W.charAt(i)) {
                        cnt++;
                        if (cnt == K) {
                            min = Math.min(min, j + 1);
                            max = Math.max(max, j + 1);
                            break;
                        }
                    }
                }
            }
            if (min == 10000 && max == 0) {
                sb.append(-1).append("\n");
            } else {
                sb.append(min).append(" ").append(max).append("\n");
            }
        }

        System.out.println(sb);
    }
}
