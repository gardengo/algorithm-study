import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < K; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= V; i++)
                graph.add(new ArrayList<>());

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int v1 = Integer.parseInt(st.nextToken());
                int v2 = Integer.parseInt(st.nextToken());
                graph.get(v1).add(v2);
                graph.get(v2).add(v1);
            }

            boolean answer = true;
            int[] type = new int[V + 1];
            boolean[] visited = new boolean[V + 1];
            Queue<Integer> que = new ArrayDeque<>();

            root:
            for (int start = 1; start <= V; start++) {
                if (!visited[start]) {
                    que.add(start);
                    type[start] = 1;
                }

                while (!que.isEmpty()) {
                    int cur = que.poll();
                    visited[cur] = true;
                    for (int next : graph.get(cur)) {
                        if (type[cur] == 1) {
                            if (type[next] == 1) {
                                answer = false;
                                break root;
                            }
                            type[next] = 2;
                        } else {
                            if (type[next] == 2) {
                                answer = false;
                                break root;
                            }
                            type[next] = 1;
                        }
                        if (!visited[next])
                            que.add(next);
                    }
                }
            }

            if (answer) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

    }
}