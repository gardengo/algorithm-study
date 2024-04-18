import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 무게가 가벼운순, 가격이 비싼 순으로 정렬
        PriorityQueue<int[]> jewel = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken()); // 무게
            int v = Integer.parseInt(st.nextToken()); // 가격
            jewel.add(new int[]{m, v});
        }

        // 가방은 가벼운 순으로 정렬
        ArrayList<Integer> bag = new ArrayList<>();
        for (int i = 0; i < K; i++)
            bag.add(Integer.parseInt(br.readLine()));
        Collections.sort(bag);

        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < K; i++) {
            int c = bag.get(i);

            while (!jewel.isEmpty()) {
                int[] cur = jewel.peek();
                if (cur[0] <= c) {
                    pq.add(cur[1]);
                    jewel.poll();
                } else {
                    break;
                }
            }

            if (!pq.isEmpty())
                answer += pq.poll();
        }

        System.out.println(answer);
    }
}