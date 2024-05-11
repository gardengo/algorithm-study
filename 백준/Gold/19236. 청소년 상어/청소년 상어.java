import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][][] map = new int[4][4][2];
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                map[i][j][0] = a;
                map[i][j][1] = b - 1;
            }
        }

        answer = map[0][0][0];
        map[0][0][0] = -1;
        moveFish(map);
        moveShark(map, 0, 0, answer);

        System.out.println(answer);
    }

    public static void moveFish(int[][][] map) {
        for (int a = 1; a <= 16; a++) {
            int x = -1;
            int y = -1;
            int b = -1;

            search:
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (map[i][j][0] == a) {
                        x = i;
                        y = j;
                        b = map[i][j][1];
                        break search;
                    }
                }
            }
            if (x == -1)
                continue;

            for (int d = 0; d < 8; d++) {
                int dir = (b + d) % 8;
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4)
                    continue;
                if (map[nx][ny][0] == -1)
                    continue;

                if (map[nx][ny][0] > 0) {
                    int tmpx = map[nx][ny][0];
                    int tmpy = map[nx][ny][1];
                    map[nx][ny][0] = map[x][y][0];
                    map[nx][ny][1] = dir;
                    map[x][y][0] = tmpx;
                    map[x][y][1] = tmpy;
                    break;
                } else {
                    map[nx][ny][0] = map[x][y][0];
                    map[nx][ny][1] = dir;
                    map[x][y][0] = 0;
                    map[x][y][1] = 0;
                    break;
                }
            }
        }
    }

    public static void moveShark(int[][][] map, int sx, int sy, int sum) {
        int dir = map[sx][sy][1];
        for (int r = 1; r <= 3; r++) {
            int nx = sx + dx[dir] * r;
            int ny = sy + dy[dir] * r;

            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4)
                break;
            if (map[nx][ny][0] == 0)
                continue;
            
            int[][][] copyMap = new int[4][4][2];
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++)
                    for (int k = 0; k < 2; k++)
                        copyMap[i][j][k] = map[i][j][k];

            int cnt = copyMap[nx][ny][0];
            copyMap[nx][ny][0] = -1;
            copyMap[sx][sy][0] = 0;
            copyMap[sx][sy][1] = 0;

            moveFish(copyMap);
            moveShark(copyMap, nx, ny, sum + cnt);
        }
        answer = Math.max(answer, sum);
    }
}
