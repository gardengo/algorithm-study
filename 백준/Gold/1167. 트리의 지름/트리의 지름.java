import java.io.*;
import java.util.*;

public class Main {
    private static int top, answer;
    private static ArrayList<ArrayList<int[]>> graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int V = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        for (int i = 0; i <= V; i++)
            graph.add(new ArrayList<>());

        int start = 0;
        int cnt = 0;
        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            ArrayList<int[]> list = graph.get(s);

            while (true) {
                int node = Integer.parseInt(st.nextToken());
                if (node == -1)
                    break;
                int dist = Integer.parseInt(st.nextToken());
                list.add(new int[]{node, dist});
            }

            if (cnt < list.size()) {
                cnt = list.size();
                start = s;
            }
        }

        // 트리라는 자료구조 특성상 루프는 불가능
        // 가장 많은 노드가 연결된 지점을 루트노드로 가정하고 탐색
        // 거리가 가장 먼 2지점을 찾아서 더한다.
        top = 0;
        answer = 0;
        dfs(start, 0, 0);
        dfs(top, 0, 0);

        System.out.println(answer);
    }

    private static void dfs(int cur, int before, int cost) {
        if (cost > answer) {
            answer = cost;
            top = cur;
        }

        ArrayList<int[]> list = graph.get(cur);
        for (int i = 0; i < list.size(); i++) {
            int[] next = list.get(i);
            if (next[0] == before)
                continue;
            dfs(next[0], cur, cost + next[1]);
        }
    }
}