import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        if (N >= K) {
            System.out.println(N - K);
            return;
        }

        // 10만의 배열에 걸리는 시간을 기록하면? 메모리 4MB
        int[] cost = new int[100001];
        Arrays.fill(cost, 100000);

        Queue<int[]> que = new ArrayDeque<>();
        int pos = N;
        teleport(que, cost, pos, 0);

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            if (cur[0] == K) {
                System.out.println(cur[1]);
                break;
            }

            if (cur[0] < 100000) {
                pos = cur[0] + 1;
                teleport(que, cost, pos, cur[1] + 1);
            }
            if (cur[0] > 0 && cost[cur[0] - 1] > cur[1] + 1) {
                pos = cur[0] - 1;
                teleport(que, cost, pos, cur[1] + 1);
            }
        }
    }

    public static void teleport(Queue<int[]> que, int[] cost, int pos, int time) {
        while (pos <= 100000) {
            if (cost[pos] > time) {
                cost[pos] = time;
                que.add(new int[]{pos, time});
            }
            if (pos == 0)
                break;
            pos *= 2;
        }
    }

}
