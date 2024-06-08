import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++)
            graph.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Node(B, i + 1));
            graph.get(B).add(new Node(A, i + 1));
        }

        long[] dist = new long[N + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        dist[1] = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.cost > dist[cur.pos])
                continue;
            for (Node next : graph.get(cur.pos)) {
                long nextCost;
                if (cur.cost <= next.cost) {
                    nextCost = next.cost;
                } else {
                    nextCost = (long) (Math.ceil((double) (cur.cost - next.cost) / M) * M) + next.cost;
                }

                if (dist[next.pos] > nextCost) {
                    dist[next.pos] = nextCost;
                    pq.add(new Node(next.pos, nextCost));
                }
            }
        }
        System.out.println(dist[N]);
    }

    static class Node implements Comparable {
        int pos;
        long cost;

        Node(int pos, long cost) {
            this.pos = pos;
            this.cost = cost;
        }

        public int compareTo(Object o) {
            Node n = (Node) o;
            return Long.compare(cost, n.cost);
        }
    }
}
