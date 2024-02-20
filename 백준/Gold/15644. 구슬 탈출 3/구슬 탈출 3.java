import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static char[][] board;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        int[] posR = new int[2];
        int[] posB = new int[2];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);
                if (board[i][j] == 'R') {
                    posR[0] = i;
                    posR[1] = j;
                    board[i][j] = '.';
                }
                if (board[i][j] == 'B') {
                    posB[0] = i;
                    posB[1] = j;
                    board[i][j] = '.';
                }
            }
        }

        // bfs
        Queue<int[]> que = new ArrayDeque<>();
        que.add(new int[]{posR[0], posR[1], posB[0], posB[1]});

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            if (cur.length > 14) {
                break;
            }
            if (board[cur[2]][cur[3]] == 'O') {
                continue;
            }
            if (board[cur[0]][cur[1]] == 'O') {
                printAnswer(cur);
                return;
            }

            for (int d = 0; d < 4; d++) {
                if (cur.length > 4) {
                    int bdr = cur[cur.length - 1];
                    if (bdr / 2 == d / 2)
                        continue;
                }

                int[] next = move(cur, d);
                que.add(next);
            }
        }

        System.out.println(-1);
    }

    private static int[] move(int[] cur, int dir) {
        int[] nextR, nextB;
        if ((dir % 2 == 0) == (cur[1 - dir / 2] < cur[3 - dir / 2])) {
            nextR = moveBubble(cur[0], cur[1], dir);
            if (board[nextR[0]][nextR[1]] == '.')
                board[nextR[0]][nextR[1]] = 'R';
            nextB = moveBubble(cur[2], cur[3], dir);
            if (board[nextR[0]][nextR[1]] == 'R')
                board[nextR[0]][nextR[1]] = '.';
        } else {
            nextB = moveBubble(cur[2], cur[3], dir);
            if (board[nextB[0]][nextB[1]] == '.')
                board[nextB[0]][nextB[1]] = 'B';
            nextR = moveBubble(cur[0], cur[1], dir);
            if (board[nextB[0]][nextB[1]] == 'B')
                board[nextB[0]][nextB[1]] = '.';
        }

        int[] result = new int[cur.length + 1];
        result[0] = nextR[0];
        result[1] = nextR[1];
        result[2] = nextB[0];
        result[3] = nextB[1];
        for (int i = 4; i < cur.length; i++)
            result[i] = cur[i];
        result[result.length - 1] = dir;
        return result;
    }

    private static int[] moveBubble(int x, int y, int dir) {
        while (board[x + dx[dir]][y + dy[dir]] == '.') {
            x += dx[dir];
            y += dy[dir];
        }
        if (board[x + dx[dir]][y + dy[dir]] == 'O') {
            x += dx[dir];
            y += dy[dir];
        }
        return new int[]{x, y};
    }

    private static void printAnswer(int[] cur) {
        StringBuilder sb = new StringBuilder();
        sb.append(cur.length - 4).append("\n");
        for (int i = 4; i < cur.length; i++) {
            if (cur[i] == 0) {
                sb.append('L');
            } else if (cur[i] == 1) {
                sb.append('R');
            } else if (cur[i] == 2) {
                sb.append('U');
            } else {
                sb.append('D');
            }
        }

        System.out.println(sb);
    }
}
