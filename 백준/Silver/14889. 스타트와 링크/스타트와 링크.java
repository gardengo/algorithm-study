import java.io.*;
import java.util.*;

public class Main {
    private static int N, answer;
    private static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        answer = 10000;
        boolean[] visited = new boolean[N];
        comb(0, 0, visited);
        System.out.println(answer);
    }

    private static void comb(int depth, int cnt, boolean[] visited) {
        if (cnt == N / 2) {
            calc(visited);
            return;
        }
        if (answer == 0 || depth == N)
            return;

        visited[depth] = true;
        comb(depth + 1, cnt + 1, visited);
        visited[depth] = false;
        comb(depth + 1, cnt, visited);
    }

    private static void calc(boolean[] visited) {
        if (answer == 0)
            return;

        ArrayList<Integer> start = new ArrayList<>();
        ArrayList<Integer> link = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                start.add(i);
            } else {
                link.add(i);
            }
        }

        int sp = 0;
        for (int i = 0; i < N / 2; i++) {
            int first = start.get(i);
            for (int j = 0; j < N / 2; j++) {
                int second = start.get(j);
                sp += map[first][second];
            }
        }

        int lp = 0;
        for (int i = 0; i < N / 2; i++) {
            int first = link.get(i);
            for (int j = 0; j < N / 2; j++) {
                int second = link.get(j);
                lp += map[first][second];
            }
        }

        answer = Math.min(answer, Math.abs(sp - lp));
    }
}