import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 1; i <= 500; i++)
            map.put(i, new ArrayList<>());
        ArrayList<PriorityQueue<Player>> game = new ArrayList<>();
        int idx = 0;

        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            String n = st.nextToken();
            if (map.get(l).isEmpty()) {
                game.add(new PriorityQueue<>(Comparator.comparing(a -> a.name)));
                game.get(idx).add(new Player(l, n));
                if (m > 1) {
                    for (int j = -10; j <= 10; j++) {
                        if (0 < l + j && l + j <= 500)
                            map.get(l + j).add(idx);
                    }
                }
                idx++;
            } else {
                int room = map.get(l).get(0);
                PriorityQueue<Player> pq = game.get(room);
                pq.add(new Player(l, n));
                if (pq.size() == m) {
                    for (int j = -20; j <= 20; j++) {
                        if (0 < l + j && l + j <= 500 && map.get(l + j).contains(room))
                            map.get(l + j).remove((Object) room);
                    }
                }
            }
        }

        for (int i = 0; i < idx; i++) {
            PriorityQueue<Player> pq = game.get(i);
            if (pq.size() == m) {
                sb.append("Started!\n");
            } else {
                sb.append("Waiting!\n");
            }
            while (!pq.isEmpty()) {
                Player player = pq.poll();
                sb.append(player.level).append(" ").append(player.name).append("\n");
            }
        }

        System.out.println(sb);
    }

    public static class Player {
        int level;
        String name;

        public Player(int l, String n) {
            this.level = l;
            this.name = n;
        }
    }
}