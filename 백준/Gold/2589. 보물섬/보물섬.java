import java.io.*;
import java.util.*;

public class Main {
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int L = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[L][W];
        for (int i = 0; i < L; i++) {
            String str = br.readLine();
            for (int j = 0; j < W; j++) {
                if (str.charAt(j) == 'L')
                    map[i][j] = true;
            }
        }

        int answer = 0;
        Queue<int[]> que = new ArrayDeque<>();
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < W; j++) {
                if (map[i][j]) {
                    boolean[][] visited = new boolean[L][W];
                    visited[i][j] = true;
                    que.add(new int[]{i, j, 0});

                    while (!que.isEmpty()) {
                        int[] cur = que.poll();
                        answer = Math.max(answer, cur[2]);

                        for (int d = 0; d < 4; d++) {
                            if (cur[0] + dx[d] < 0 || cur[0] + dx[d] >= L || cur[1] + dy[d] < 0 || cur[1] + dy[d] >= W)
                                continue;
                            if (visited[cur[0] + dx[d]][cur[1] + dy[d]] || !map[cur[0] + dx[d]][cur[1] + dy[d]])
                                continue;
                            visited[cur[0] + dx[d]][cur[1] + dy[d]] = true;
                            que.add(new int[]{cur[0] + dx[d], cur[1] + dy[d], cur[2] + 1});
                        }
                    }
                }
            }
        }

        System.out.println(answer);
    }
}