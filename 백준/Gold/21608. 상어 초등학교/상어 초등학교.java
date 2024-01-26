import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] room;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        room = new int[N + 2][N + 2];
        ArrayList<HashSet<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= N * N; i++)
            list.add(new HashSet<>());

        for (int i = 0; i < N + 1; i++) {
            room[0][i] = -1;
            room[N + 1][i] = -1;
            room[i][0] = -1;
            room[i][N + 1] = -1;
        }

        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int sn = Integer.parseInt(st.nextToken()); // 학생의 번호
            HashSet<Integer> set = new HashSet<>();
            for (int j = 0; j < 4; j++)
                set.add(Integer.parseInt(st.nextToken()));

            list.get(sn).addAll(set);
            findSeat(sn, set);
        }

        calcLike(list);
    }

    public static void findSeat(int sn, HashSet<Integer> set) {
        Queue<int[]> que1 = new ArrayDeque<>();
        int max = 0;

        // 1. 인접한 칸에 좋아하는 학생이 가장 많은 자리 구하기
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (room[i][j] != 0)
                    continue;

                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    if (set.contains(room[i + dx[d]][j + dy[d]]))
                        cnt++;
                }
                if (cnt > max) {
                    que1.clear();
                    que1.add(new int[]{i, j});
                    max = cnt;
                } else if (cnt == max) {
                    que1.add(new int[]{i, j});
                }
            }
        }

        // 2. 인접한 칸 중에서 비어있는 칸이 가장 많은 칸 찾기
        Queue<int[]> que2 = new ArrayDeque<>();
        max = 0;
        while (!que1.isEmpty()) {
            int cnt = 0;
            int[] pos = que1.poll();

            for (int d = 0; d < 4; d++) {
                if (room[pos[0] + dx[d]][pos[1] + dy[d]] == 0)
                    cnt++;
            }

            if (cnt > max) {
                que2.clear();
                que2.add(new int[]{pos[0], pos[1]});
                max = cnt;
            } else if (cnt == max) {
                que2.add(new int[]{pos[0], pos[1]});
            }
        }

        int[] pos = que2.peek();
        room[pos[0]][pos[1]] = sn;
    }

    public static void calcLike(ArrayList<HashSet<Integer>> list) {
        int sum = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int sn = room[i][j];
                HashSet<Integer> set = list.get(sn);
                double score = 0.1;

                for (int d = 0; d < 4; d++) {
                    if (set.contains(room[i + dx[d]][j + dy[d]]))
                        score *= 10;
                }

                if (score != 0.1)
                    sum += (int)score;
            }
        }

        System.out.println(sum);
    }
}
