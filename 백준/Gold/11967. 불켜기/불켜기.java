import java.io.*;
import java.util.*;

public class Main {
    private static final int[] dx = {-1, 1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] map = new boolean[N + 1][N + 1];
        ArrayList<int[]>[][] graph = new ArrayList[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++)
            for (int j = 0; j < N + 1; j++)
                graph[i][j] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[x][y].add(new int[]{a, b});
        }

        Queue<int[]> que = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 1][N + 1];
        List<int[]> visitList = new ArrayList<>();
        map[1][1] = true;
        visited[1][1] = true;
        que.add(new int[]{1, 1});
        visitList.add(new int[]{1, 1});

        int answer = 1;
        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int x = cur[0];
            int y = cur[1];

            boolean switchON = false;
            for (int[] next : graph[x][y]) {
                if (!map[next[0]][next[1]]) {
                    map[next[0]][next[1]] = true;
                    answer++;
                    switchON = true;
                }
            }
            if(switchON) {
                que.clear();
                que.addAll(visitList);
            }

            for (int d = 0; d < 4; d++) {
                if (x + dx[d] < 1 || x + dx[d] > N || y + dy[d] < 1 || y + dy[d] > N)
                    continue;
                if (map[x + dx[d]][y + dy[d]] && !visited[x + dx[d]][y + dy[d]]) {
                    visited[x + dx[d]][y + dy[d]] = true;
                    que.add(new int[]{x + dx[d], y + dy[d]});
                    visitList.add(new int[]{x + dx[d], y + dy[d]});
                }
            }
        }

        System.out.println(answer);
    }
}