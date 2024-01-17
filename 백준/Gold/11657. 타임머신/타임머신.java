import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE - 20000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 최대 500
        int M = Integer.parseInt(st.nextToken()); // 최대 6000
        int[] answer = new int[N + 1];
        for (int i = 0; i <= N; i++)
            answer[i] = INF;
        answer[1] = 0;

        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++)
            graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph.get(A).add(new int[]{B, C});
        }

        // 음의 무한루프 -> -1
        // 그게 아니면 각 도시에 걸리는 시간, 해당 도시로 갈 수 없으면 -1
        for (int i = 0; i < N - 1; i++) {
            for (int j = 1; j <= N; j++) {
                if (answer[j] == INF)
                    continue;
                for (int k = 0; k < graph.get(j).size(); k++) {
                    int[] bus = graph.get(j).get(k);
                    if (answer[j] + bus[1] < -60000000) {
                        System.out.println(-1);
                        return;
                    }
                    answer[bus[0]] = Math.min(answer[bus[0]], answer[j] + bus[1]);
                }
            }
        }

        // N번째 루프를 돌려서 값이 갱신되는지 확인
        for (int j = 1; j <= N; j++) {
            if (answer[j] == INF)
                continue;
            for (int k = 0; k < graph.get(j).size(); k++) {
                int[] bus = graph.get(j).get(k);
                if (answer[bus[0]] > answer[j] + bus[1]) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            if (answer[i] == INF)
                answer[i] = -1;
            sb.append(answer[i]).append("\n");
        }

        System.out.println(sb);
    }
}
