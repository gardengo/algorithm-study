import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        String nStr = Integer.toString(N);
        int M = nStr.length();

        boolean[][] visited = new boolean[K + 1][(int) Math.pow(10, M)];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            } else {
                return a[0] - b[0];
            }
        });

        pq.offer(new int[]{0, N});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (cur[0] == K) {
                System.out.println(cur[1]);
                return;
            }

            for (int i = 0; i < M; i++) {
                for (int j = i + 1; j < M; j++) {
                    if (!canSwap(cur[1], i, j))
                        continue;

                    int result = swapNum(cur[1], i, j);
                    if (visited[cur[0] + 1][result])
                        continue;
                    visited[cur[0] + 1][result] = true;
                    pq.offer(new int[]{cur[0] + 1, result});
                }
            }
        }

        System.out.println(-1);
    }

    private static boolean canSwap(int num, int i, int j) {
        String str = Integer.toString(num);
        char second = str.charAt(j);
        return i != 0 || second != '0';
    }

    private static int swapNum(int num, int i, int j) {
        String str = Integer.toString(num);
        char first = str.charAt(i);
        char second = str.charAt(j);

        StringBuilder sb = new StringBuilder();
        sb.append(str.substring(0, i));
        sb.append(second);
        sb.append(str.substring(i + 1, j));
        sb.append(first);
        sb.append(str.substring(j + 1));

        return Integer.parseInt(sb.toString());
    }
}
