import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static char[][] map;
    private static int answer;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[5][5];
        for (int i = 0; i < 5; i++)
            map[i] = br.readLine().toCharArray();

        answer = 0;
        Set<Integer> set = new HashSet<>();
        dfs(0, 0, set);

        System.out.println(answer);
    }

    private static void dfs(int r, int c, Set<Integer> set) {
        if (set.size() == 7) {
            checkConnect(set);
            return;
        }

        for (int i = r; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == r && j == 0)
                    j = c;
                if (set.contains(i * 10 + j))
                    continue;

                set.add(i * 10 + j);
                dfs(i, j, set);
                set.remove(i * 10 + j);
            }
        }
    }

    private static void checkConnect(Set<Integer> set) {
        Object[] arr = set.toArray();
        Set<Integer> visited = new HashSet<>();
        int start = (int) arr[0];
        visited.add(start);

        root:
        for (int k = 0; k < 6; k++) {
            for (int i = 1; i < 7; i++) {
                int cur = (int) arr[i];
                if (visited.contains(cur))
                    continue;

                for (int d = 0; d < 4; d++) {
                    int nr = cur / 10 + dx[d];
                    int nc = cur % 10 + dy[d];
                    if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5)
                        continue;

                    if (visited.contains(nr * 10 + nc)) {
                        visited.add(cur);
                        continue root;
                    }
                }
            }
            return;
        }
        checkSeven(set);
    }

    private static void checkSeven(Set<Integer> set) {
        int cnt = 0;

        for (int i : set) {
            int r = i / 10;
            int c = i % 10;

            if (map[r][c] == 'S')
                cnt++;
        }

        if (cnt >= 4) {
            answer++;
        }
    }
}