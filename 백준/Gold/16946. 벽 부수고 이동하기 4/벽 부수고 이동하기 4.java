import java.io.*;
import java.util.*;

public class Main {

    public static StringBuilder sb = new StringBuilder();
    public static int N, M, cluster;
    public static int[] dr = {-1, 1, 0, 0};
    public static int[] dc = {0, 0, -1, 1};
    public static int[][] map, count, clusterMap;
    public static boolean[] visitCluster;
    public static boolean[][] visitMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cluster = 2;

        map = new int[N][M];
        count = new int[N][M];
        clusterMap = new int[N][M];
        visitMap = new boolean[N][M];

        for (int r = 0; r < N; r++) {
            String str = br.readLine();
            for (int c = 0; c < M; c++) {
                map[r][c] = str.charAt(c) - '0';
            }
        }

        calculateCounts();
        calculateResult();
        printResult();
    }

    private static void printResult() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                sb.append(map[r][c] % 10);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void calculateResult() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 1) {
                    Set<Integer> set = new HashSet<>();
                    for (int i = 0; i < dr.length; i++) {
                        int nr = r + dr[i];
                        int nc = c + dc[i];
                        if (checkIndex(nr, nc) && !set.contains(clusterMap[nr][nc])) {
                            set.add(clusterMap[nr][nc]);
                            map[r][c] += count[nr][nc];
                        }
                    }
                }
            }
        }
    }

    private static void calculateCounts() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (map[r][c] == 0 && !visitMap[r][c]) {
                    bfs(r, c);
                }
            }
        }
    }

    private static boolean checkIndex(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    private static void bfs(int r, int c) {
        Queue<Pos> queue = new LinkedList<>();
        List<Pos> list = new ArrayList<>();
        int cnt = 1;

        visitMap[r][c] = true;
        Pos startPos = new Pos(r, c);
        queue.add(startPos);
        list.add(startPos);

        while (!queue.isEmpty()) {
            Pos tmp = queue.poll();

            for (int i = 0; i < dr.length; i++) {
                int nr = tmp.r + dr[i];
                int nc = tmp.c + dc[i];
                if (checkIndex(nr, nc) && map[nr][nc] == 0 && !visitMap[nr][nc]) {
                    cnt++;
                    visitMap[nr][nc] = true;
                    Pos next = new Pos(nr, nc);
                    queue.add(next);
                    list.add(next);
                }
            }
        }

        for (Pos pos : list) {
            count[pos.r][pos.c] = cnt;
            clusterMap[pos.r][pos.c] = cluster;
        }
        cluster++;
    }

    private static class Pos {
        int r, c;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}