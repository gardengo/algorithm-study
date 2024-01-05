import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine()); // 최대 5

        root:
        for (int t = 0; t < TC; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 지점의 수 - 최대 500
            int M = Integer.parseInt(st.nextToken()); // 도로의 개수 - 최대 2500
            int W = Integer.parseInt(st.nextToken()); // 웜홀의 개수 - 최대 200

            ArrayList<ArrayList<int[]>> road = new ArrayList<>();
            for (int i = 0; i <= N; i++)
                road.add(new ArrayList<>());
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                road.get(S).add(new int[]{E, T});
                road.get(E).add(new int[]{S, T});
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                road.get(S).add(new int[]{E, -T});
            }

            // 음의 순환이 발생하는지 찾는 문제
            // N번 이동이 가능하면 순환이 발생하는 것
            int[] dist = new int[N + 1];
            boolean[][] visited = new boolean[N + 1][N + 1];
            Queue<int[]> que = new ArrayDeque<>();

            for (int i = 1; i <= N; i++) {
                Arrays.fill(dist, Integer.MAX_VALUE);
                dist[i] = 0;
                que.add(new int[]{i, 0});

                while (!que.isEmpty()) {
                    int[] cur = que.poll(); // 위치, 이동횟수
                    if (cur[1] == N) {
                        System.out.println("YES");
                        continue root;
                    }

                    ArrayList<int[]> list = road.get(cur[0]);
                    for (int[] next : list) { // 위치, 시간
                        if (dist[next[0]] > dist[cur[0]] + next[1]) {
                            dist[next[0]] = dist[cur[0]] + next[1];

                            if (!visited[cur[1] + 1][next[0]]) {
                                que.add(new int[]{next[0], cur[1] + 1});
                                visited[cur[1] + 1][next[0]] = true;
                            }
                        }
                    }
                }
            }
            System.out.println("NO");
        }
    }
}
