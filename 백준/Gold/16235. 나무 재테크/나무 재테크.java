import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] A = new int[N + 1][N + 1];
        int[][] ground = new int[N + 1][N + 1];
        ArrayDeque<Integer> trees = new ArrayDeque<>();
        ArrayDeque<Integer> breed = new ArrayDeque<>();
        int[][] die = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                ground[i][j] = 5;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            // x * 1,000,000 + y * 10,000 + z로 해싱
            trees.offer(x * 1000000 + y * 10000 + z);
        }

        for (int i = 0; i < K; i++) {
            spring(ground, trees, breed, die);
            summerNwinter(N, ground, die, A);
            autumn(N, trees, breed);
        }

        System.out.println(trees.size());
    }

    // 봄
    // 자신의 나이만큼 양분을 먹고 나이가 1 증가 (어린순)
    // 양분이 부족해 나이만큼 먹을 수 없으면 죽는다
    public static void spring(int[][] ground, ArrayDeque<Integer> trees, ArrayDeque<Integer> breed, int[][] die) {
        int size = trees.size();

        for (int i = 0; i < size; i++) {
            int tree = trees.pollFirst();
            int x = tree / 1000000;
            int y = (tree % 1000000) / 10000;
            int z = tree % 10000;

            if (ground[x][y] >= z) {
                ground[x][y] -= z;
                trees.offerLast(tree + 1);
                if ((z + 1) % 5 == 0)
                    breed.offerLast(tree + 1);
            } else {
                die[x][y] += z / 2;
            }
        }
    }

    // 여름 & 겨울
    // 봄에 죽은 나무가 (나이/2)의 양분으로 변한다
    // A 배열만큼 양분을 추가한다
    public static void summerNwinter(int N, int[][] ground, int[][] die, int[][] A) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                ground[i][j] += die[i][j] + A[i][j];
                die[i][j] = 0;
            }
        }
    }

    // 가을
    // 나이가 5의 배수인 나무 주변 8칸에 나이가 1인 나무가 생긴다
    public static void autumn(int N, ArrayDeque<Integer> trees, ArrayDeque<Integer> breed) {
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        while (!breed.isEmpty()) {
            int tree = breed.poll();
            int x = tree / 1000000;
            int y = (tree % 1000000) / 10000;

            for (int d = 0; d < 8; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 1 || nx > N || ny < 1 || ny > N)
                    continue;

                trees.offerFirst(nx * 1000000 + ny * 10000 + 1);
            }
        }
    }


}
