import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, answer;
    private static int[][] map;
    private static int[] dy = {-1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        answer = Integer.MAX_VALUE;
        for (int i = 0; i < M; i++)
            solution(0, i, -1, map[0][i]);
        System.out.println(answer);
    }

    private static void solution(int x, int y, int bd, int sum) {
        if (x == N - 1) {
            answer = Math.min(answer, sum);
            return;
        }
        if (answer < sum)
            return;

        for (int d = 0; d < 3; d++) {
            if (bd == d)
                continue;
            if (y + dy[d] < 0 || y + dy[d] >= M)
                continue;
            solution(x + 1, y + dy[d], d, sum + map[x + 1][y + dy[d]]);
        }
    }
}
