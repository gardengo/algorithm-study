import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 팀의 개수
            int k = Integer.parseInt(st.nextToken()); // 문제의 개수
            int t = Integer.parseInt(st.nextToken()); // 팀의 id
            int m = Integer.parseInt(st.nextToken()); // 로그의 개수

            int[][] score = new int[n + 1][k + 1];
            int[] submit = new int[n + 1];
            int[] last = new int[n + 1];
            for (int idx = 1; idx <= m; idx++) {
                st = new StringTokenizer(br.readLine());
                int i = Integer.parseInt(st.nextToken()); // 팀 id
                int j = Integer.parseInt(st.nextToken()); // 문제 번호
                int s = Integer.parseInt(st.nextToken()); // 점수
                score[i][j] = Math.max(score[i][j], s);
                submit[i]++;
                last[i] = idx;
            }

            ArrayList<int[]> list = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                int sum = 0;
                for (int j = 0; j <= k; j++)
                    sum += score[i][j];
                list.add(new int[]{i, sum, submit[i], last[i]});
            }
            list.sort((a, b) -> {
                if (a[1] == b[1]) {
                    if (a[2] == b[2]) {
                        return a[3] - b[3];
                    } else {
                        return a[2] - b[2];
                    }
                } else {
                    return b[1] - a[1];
                }
            });

            for (int i = 0; i <= n; i++) {
                if (list.get(i)[0] == t) {
                    sb.append(i + 1).append("\n");
                }
            }
        }

        System.out.println(sb);
    }
}