import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        HashMap<Integer, ArrayList<Integer>> bomb = new HashMap<>();
        bomb.put(0, new ArrayList<>());

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
                if (map[i][j] == 'O')
                    bomb.get(0).add(i * 1000 + j);
            }
        }

        // 0 - 폭탄 설치
        // 1 - 아무것도 하지 않음
        // 2 - 폭탄이 없는 칸에 폭탄 설치
        // 3 - 0에 설치한 폭탄 폭발
        // 4 - 폭탄이 없는 칸에 폭탄 설치
        // 5 - 2에 설치한 폭탄 폭발...
        // 1 이후 2, 3, 2, 5가 반복됨

        HashMap<Integer, StringBuilder> sb = new HashMap<>();
        for (int i = 1; i <= 5; i++)
            sb.put(i, new StringBuilder());

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++)
                sb.get(1).append(map[i][j]);
            sb.get(1).append("\n");
        }


        for (int t = 2; t <= 5; t++) {
            if (t % 2 == 0) {
                bomb.put(t, new ArrayList<>());
                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++) {
                        if (map[i][j] == '.') {
                            map[i][j] = 'O';
                            bomb.get(t).add(i * 1000 + j);
                        }
                        sb.get(t).append(map[i][j]);
                    }
                    sb.get(t).append("\n");
                }
            } else {
                ArrayList<Integer> list = bomb.get(t-3);
                for (int i = 0; i < list.size(); i++) {
                    int pos = list.get(i);
                    map[pos / 1000][pos % 1000] = '.';

                    for (int d = 0; d < 4; d++) {
                        int nx = pos / 1000 + dx[d];
                        int ny = pos % 1000 + dy[d];
                        if (nx < 0 || nx >= R || ny < 0 || ny >= C)
                            continue;

                        if (map[nx][ny] == '.')
                            continue;
                        map[nx][ny] = '.';
                        bomb.get(t - 1).remove((Object) (nx * 1000 + ny));
                    }
                }

                for (int i = 0; i < R; i++) {
                    for (int j = 0; j < C; j++)
                        sb.get(t).append(map[i][j]);
                    sb.get(t).append("\n");
                }
            }
        }

        if (N == 1) {
            System.out.println(sb.get(1));
        } else {
            System.out.println(sb.get((N + 2) % 4 + 2));
        }
    }
}
