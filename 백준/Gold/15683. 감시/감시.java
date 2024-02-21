import java.io.*;
import java.util.*;

public class Main {
    static int N, M, blind, answer;
    static int[][] map;
    static ArrayList<Integer> list;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        blind = N * M;
        map = new int[N][M];
        list = new ArrayList<>();
        ArrayList<Integer> cam5 = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    blind--;
                    if (map[i][j] == 6) {
                    } else if (map[i][j] == 5) {
                        cam5.add(i * 10 + j);
                    } else {
                        list.add(map[i][j] * 100 + i * 10 + j);
                    }
                }
            }
        }

        // 5번은 4방향 고정
        for (int i = 0; i < cam5.size(); i++) {
            int pos = cam5.get(i);
            for (int d = 0; d < 4; d++) {
                int x = pos / 10;
                int y = pos % 10;
                while (x + dx[d] >= 0 && x + dx[d] < N && y + dy[d] >= 0 && y + dy[d] < M) {
                    if (map[x + dx[d]][y + dy[d]] != 6) {
                        if (map[x + dx[d]][y + dy[d]] == 0) {
                            map[x + dx[d]][y + dy[d]] = 5;
                            blind--;
                        }
                        x += dx[d];
                        y += dy[d];
                    } else {
                        break;
                    }
                }
            }
        }

        answer = blind;
        dfs(0);

        System.out.println(answer);
    }

    private static void dfs(int idx) {
        if (idx >= list.size()) {
            answer = Math.min(answer, blind);
            return;
        }

        int cur = list.get(idx);
        int num = cur / 100;
        int x = cur % 100 / 10;
        int y = cur % 10;

        for (int d = 0; d < 4; d++) {
            cam(num, x, y, d, idx, true);
            dfs(idx + 1);
            cam(num, x, y, d, idx, false);

            if (num == 2)
                d++;
        }
    }

    private static void cam(int num, int x, int y, int d, int idx, boolean inst) {
        if (num == 1) {
            cam1(x, y, d, idx, inst);
        } else if (num == 2) {
            cam2(x, y, d, idx, inst);
        } else {
            cam34(num, x, y, d, idx, inst);
        }
    }

    private static void cam1(int x, int y, int d, int idx, boolean inst) {
        while (x + dx[d] >= 0 && x + dx[d] < N && y + dy[d] >= 0 && y + dy[d] < M) {
            if (map[x + dx[d]][y + dy[d]] != 6) {
                if (inst && map[x + dx[d]][y + dy[d]] == 0) {
                    map[x + dx[d]][y + dy[d]] = idx + 7;
                    blind--;
                }
                if (!inst && map[x + dx[d]][y + dy[d]] == idx + 7) {
                    map[x + dx[d]][y + dy[d]] = 0;
                    blind++;
                }
                x += dx[d];
                y += dy[d];
            } else {
                break;
            }
        }
    }

    private static void cam2(int x, int y, int d, int idx, boolean inst) {
        for (int i = d / 2; i < 4; i += 2) {
            int nx = x;
            int ny = y;
            while (nx + dx[i] >= 0 && nx + dx[i] < N && ny + dy[i] >= 0 && ny + dy[i] < M) {
                if (map[nx + dx[i]][ny + dy[i]] != 6) {
                    if (inst && map[nx + dx[i]][ny + dy[i]] == 0) {
                        map[nx + dx[i]][ny + dy[i]] = idx + 7;
                        blind--;
                    }
                    if (!inst && map[nx + dx[i]][ny + dy[i]] == idx + 7) {
                        map[nx + dx[i]][ny + dy[i]] = 0;
                        blind++;
                    }
                    nx += dx[i];
                    ny += dy[i];
                } else {
                    break;
                }
            }
        }
    }

    private static void cam34(int num, int x, int y, int d, int idx, boolean inst) {
        for (int i = d; i < d + num - 1; i++) {
            int nx = x;
            int ny = y;
            while (nx + dx[i % 4] >= 0 && nx + dx[i % 4] < N && ny + dy[i % 4] >= 0 && ny + dy[i % 4] < M) {
                if (map[nx + dx[i % 4]][ny + dy[i % 4]] != 6) {
                    if (inst && map[nx + dx[i % 4]][ny + dy[i % 4]] == 0) {
                        map[nx + dx[i % 4]][ny + dy[i % 4]] = idx + 7;
                        blind--;
                    }
                    if (!inst && map[nx + dx[i % 4]][ny + dy[i % 4]] == idx + 7) {
                        map[nx + dx[i % 4]][ny + dy[i % 4]] = 0;
                        blind++;
                    }
                    nx += dx[i % 4];
                    ny += dy[i % 4];
                } else {
                    break;
                }
            }
        }
    }
}
