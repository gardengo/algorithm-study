import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            pq.add(new int[]{a, b});
        }

        PriorityQueue<Integer> select = new PriorityQueue<>();

        int time = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (select.isEmpty()) {
                select.add(cur[1]);
                time++;
            } else {
                if (time < cur[0]) {
                    select.add(cur[1]);
                    time++;
                } else {
                    if (select.peek() < cur[1]) {
                        select.poll();
                        select.add(cur[1]);
                    }
                }
            }
        }

        int answer = 0;
        while (!select.isEmpty())
            answer += select.poll();
        System.out.println(answer);
    }
}
