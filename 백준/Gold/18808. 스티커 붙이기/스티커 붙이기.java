import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[R][C];

            for (int r = 0; r < R; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < C; c++)
                    sticker[r][c] = Integer.parseInt(st.nextToken());
            }

            paste(map, sticker, 0);
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1)
                    answer++;
            }
        }

        System.out.println(answer);
    }

    private static void paste(int[][] map, int[][] sticker, int cnt) {
        int R = sticker.length;
        int C = sticker[0].length;
        boolean canPaste = false;

        root:
        for (int i = 0; i <= N - R; i++) {
            for (int j = 0; j <= M - C; j++) {
                canPaste = true;

                test:
                for (int r = 0; r < R; r++) {
                    for (int c = 0; c < C; c++) {
                        if (map[i + r][j + c] == sticker[r][c] && sticker[r][c] == 1) {
                            canPaste = false;
                            break test;
                        }
                    }
                }

                if (canPaste) {
                    for (int r = 0; r < R; r++) {
                        for (int c = 0; c < C; c++) {
                            if (sticker[r][c] == 1)
                                map[i + r][j + c] = sticker[r][c];
                        }
                    }
                    break root;
                }
            }
        }

        if (!canPaste)
            rotate(map, sticker, cnt);
    }

    private static void rotate(int[][] map, int[][] sticker, int cnt) {
        if (cnt > 3)
            return;

        int R = sticker.length;
        int C = sticker[0].length;

        int[][] result = new int[C][R];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                result[c][R - 1 - r] = sticker[r][c];
            }
        }

        paste(map, result, cnt + 1);
    }
}