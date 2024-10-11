import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            HashMap<Integer, ArrayList<int[]>> map = new HashMap<>();
            for (int i = 1; i <= n; i++)
                map.put(i, new ArrayList<>());

            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                map.get(b).add(new int[]{a, s});
            }

            boolean[] visited = new boolean[n + 1];
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
            pq.offer(new int[]{c, 0});
            int cnt = 0;
            int time = 0;

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                if (visited[cur[0]])
                    continue;

                visited[cur[0]] = true;
                cnt++;
                time = cur[1];

                ArrayList<int[]> list = map.get(cur[0]);
                for (int[] arr : list) {
                    if (visited[arr[0]])
                        continue;
                    pq.offer(new int[]{arr[0], arr[1] + cur[1]});
                }
            }

            sb.append(cnt).append(" ").append(time).append("\n");
        }

        System.out.println(sb);
    }
}
