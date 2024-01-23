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

        answer = 0;
        answer += map[0][0][0];
        map[0][0][0] = 17; // 상어
        moveFish(map);
        moveShark(map, 0, 0, answer);

        System.out.println(answer);
    }

    public static void moveFish(int[][][] map) {
        // 1번 물고기부터 16번 물고기까지 찾아보자!
        int cur = 1;
        while (cur <= 16) {
            int x = -1;
            int y = -1;
            int dir = -1;

            // 물고기 위치 찾기
            search:
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (map[i][j][0] == cur) {
                        x = i;
                        y = j;
                        dir = map[i][j][1];
                        break search;
                    }
                }
            }
            // 물고기를 못찾았으면 이미 잡아먹혀서 없는 것
            if (x == -1) {
                cur++;
                continue;
            }

            int cnt = 0;
            // 물고기 이동하기
            while (true) {
                // 이동할 수 없으면 이동 x
                dir = dir % 8;
                cnt++;
                if (cnt > 8)
                    break;
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                // 맵 끝이면 방향 바꾸기
                if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4) {
                    dir++;
                    continue;
                }
                // 상어 만나면 방향 바꾸기
                if (map[nx][ny][0] == 17) {
                    dir++;
                    continue;
                }
                // 이동 방향에 다른 물고기가 있으면 swap
                if (map[nx][ny][0] != 0) {
                    int tmp0 = map[nx][ny][0];
                    int tmp1 = map[nx][ny][1];
                    map[nx][ny][0] = map[x][y][0];
                    map[nx][ny][1] = dir;
                    map[x][y][0] = tmp0;
                    map[x][y][1] = tmp1;
                    break;
                }
                // 아무것도 없으면 그냥 이동
                if (map[nx][ny][0] == 0) {
                    map[nx][ny][0] = map[x][y][0];
                    map[nx][ny][1] = dir;
                    map[x][y][0] = 0;
                    map[x][y][1] = 0;
                    break;
                }
            }
            cur++;
        }
    }

    public static void moveShark(int[][][] map, int sx, int sy, int sum) {
        int dir = map[sx][sy][1];
        for (int r = 1; r < 4; r++) {
            int nx = sx + dx[dir] * r;
            int ny = sy + dy[dir] * r;
            // 맵 끝이면 이동 불가
            if (nx < 0 || nx >= 4 || ny < 0 || ny >= 4)
                break;
            // 물고기가 없으면 이동 불가
            if (map[nx][ny][0] == 0)
                continue;

            // 이동, 물고기 잡아먹기!
            int[][][] copyMap = new int[4][4][2];
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++)
                    for (int k = 0; k < 2; k++)
                        copyMap[i][j][k] = map[i][j][k];

            int size = copyMap[nx][ny][0];
            copyMap[nx][ny][0] = 17;
            copyMap[sx][sy][0] = 0;
            copyMap[sx][sy][1] = 0;

            moveFish(copyMap);
            moveShark(copyMap, nx, ny, sum + size);
        }
        answer = Math.max(answer, sum);
    }
}
