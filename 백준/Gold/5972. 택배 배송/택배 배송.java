import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++)
            graph.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            ArrayList<int[]> listA = graph.get(A);
            boolean flag = false;
            for (int[] arr : listA) {
                if (arr[0] == B) {
                    arr[1] = Math.min(arr[1], C);
                    flag = true;
                    break;
                }
            }
            ArrayList<int[]> listB = graph.get(B);
            for (int[] arr : listB) {
                if (arr[0] == A) {
                    arr[1] = Math.min(arr[1], C);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                listA.add(new int[]{B, C});
                listB.add(new int[]{A, C});
            }
        }

        boolean[] visited = new boolean[N + 1];
        visited[1] = true;
        PriorityQueue<int[]> que = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (int[] arr : graph.get(1))
            que.add(new int[]{arr[0], arr[1]});

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            if (visited[cur[0]])
                continue;
            visited[cur[0]] = true;
            if (cur[0] == N) {
                System.out.println(cur[1]);
                break;
            }

            for (int[] arr : graph.get(cur[0])) {
                if (visited[arr[0]])
                    continue;
                que.add(new int[]{arr[0], arr[1] + cur[1]});
            }
        }
    }
}