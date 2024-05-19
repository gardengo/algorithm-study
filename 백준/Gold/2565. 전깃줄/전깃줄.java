import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            list.add(A * 1000 + B);
        }
        Collections.sort(list);

        int answer = 0;
        Integer[] dp = new Integer[N];
        for (int i = 0; i < N; i++)
            answer = Math.max(answer, solution(i, dp, N, list));
        System.out.println(N - answer);
    }

    private static Integer solution(int x, Integer[] dp, int N, ArrayList<Integer> list) {
        if (dp[x] == null) {
            dp[x] = 1;
            for (int i = x + 1; i < N; i++) {
                if (list.get(x) % 1000 < list.get(i) % 1000)
                    dp[x] = Math.max(dp[x], solution(i, dp, N, list) + 1);
            }
        }
        return dp[x];
    }
}
