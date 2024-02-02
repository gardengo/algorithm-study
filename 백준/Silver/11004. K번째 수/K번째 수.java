import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(st.nextToken());
            pq.add(num);
        }

        for (int i = K; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            int last = pq.peek();

            if (last > num) {
                pq.poll();
                pq.add(num);
            }
        }


        System.out.println(pq.peek());
    }

}
