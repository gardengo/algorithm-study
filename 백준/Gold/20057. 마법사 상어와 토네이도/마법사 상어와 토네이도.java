import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[] mx = {-1, 1, -2, 2, -1, 1, -1, 1, 0};
    static int[] my = {1, 1, 0, 0, 0, 0, -1, -1, -2};
    static int[] percent = {1, 1, 2, 2, 7, 7, 10, 10, 5};
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] A = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                A[i][j] = Integer.parseInt(st.nextToken());
        }

        // 중앙에서 시작해서 (0,0)까지 이동
        int sx = N / 2;
        int sy = N / 2;
        int step = 2;
        int dir = 0;
        answer = 0;

        // (0, N - 1)까지 토네이도 반복 이동
        while (step < N * 2) {
            for (int i = 0; i < step / 2; i++) {
                sx += dx[dir];
                sy += dy[dir];
                tornado(N, A, sx, sy, dir);
            }

            step++;
            dir = (dir + 1) % 4;
        }

        // (0, 0)까지 마지막 이동 실행
        for (int i = 0; i < step / 2 - 1; i++) {
            sx += dx[dir];
            sy += dy[dir];
            tornado(N, A, sx, sy, dir);
        }

        System.out.println(answer);
    }

    // dir: 0 (왼쪽) -> mx, my
    // dir: 1 (아래) -> -my, mx
    // dir: 2 (오른쪽) -> mx, -my
    // dir: 3 (위) -> my, mx
    public static void tornado(int N, int[][] A, int sx, int sy, int dir) {
        int curSand = A[sx][sy]; // 현재의 모래양
        int moveSand = 0; // 움직인 모래양
        int nx = 0;
        int ny = 0;

        // 9방향으로 모래를 이동시킨다
        for (int i = 0; i < 9; i++) {
            // 방향에 따른 모래 이동 위치를 계산한다
            if (dir == 0) {
                nx = sx + mx[i];
                ny = sy + my[i];
            } else if (dir == 1) {
                nx = sx - my[i];
                ny = sy + mx[i];
            } else if (dir == 2) {
                nx = sx + mx[i];
                ny = sy - my[i];
            } else {
                nx = sx + my[i];
                ny = sy + mx[i];
            }

            int sand = curSand * percent[i] / 100;
            if (sand > 0) {
                moveSand += sand;
                if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                    answer += sand;
                } else {
                    A[nx][ny] += sand;
                }
            }
        }

        // 마지막으로 a를 계산해준다
        nx = sx + dx[dir];
        ny = sy + dy[dir];
        if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
            answer += (curSand - moveSand);
        } else {
            A[nx][ny] += (curSand - moveSand);
        }
        A[sx][sy] = 0;
    }

}
