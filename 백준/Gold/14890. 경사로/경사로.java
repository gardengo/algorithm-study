import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        // 문제에 설명이 부실하다.
        // 경사로가 가로 세로 겹치는 부분까지 생각했으나 한 줄 씩 생각하면 되는 쉬운 문제였다...
        int row = findSlope(N, L, map, true);
        int column = findSlope(N, L, map, false);

        System.out.println(row + column);
    }

    public static int findSlope(int N, int L, int[][] map, boolean isRow) {
        int result = 0;

        root:
        for (int i = 0; i < N; i++) {
            int before = 0;
            int cur = isRow ? map[i][0] : map[0][i];
            int cnt = 1;
            boolean down = false;

            for (int j = 1; j < N; j++) {
                before = cur;
                cur = isRow ? map[i][j] : map[j][i];

                // 경사의 차이가 1보다 크면 실패
                if (Math.abs(before - cur) > 1)
                    continue root;

                if (before == cur) { // 이전과 같으면 cnt 증가하고 지나감
                    cnt++;
                } else { // 이전과 다르면 경사로 판단
                    if (down) // 내리막 경사로 설치중이면 실패
                        continue root;
                    if (before < cur) { // 오르막길
                        if (cnt < L)
                            continue root;
                    } else { // 내리막길
                        down = true;
                    }
                    cnt = 1;
                }

                if (down && cnt == L) { // 내리막길 설치 완료
                    down = false;
                    cnt = 0;
                }
            }
            if (down)
                continue root;

            result++;
        }
        return result;
    }
}
