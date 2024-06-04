import java.io.*;
import java.util.*;

public class Main {
    private static int N, M;
    private static TreeMap<Integer, String> map;
    private static ArrayList<Integer> list;
    private static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());


        map = new TreeMap<>();
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int power = Integer.parseInt(st.nextToken());
            if (!map.containsKey(power)) {
                map.put(power, name);
                list.add(power);
            }
        }
        Collections.sort(list);

        sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            solution(Integer.parseInt(br.readLine()));
        }

        System.out.println(sb);
    }

    private static void solution(int power) {
        int lt = 0;
        int rt = list.size();
        while (lt < rt) {
            int mid = (lt + rt) / 2;
            if (list.get(mid) < power) {
                lt = mid + 1;
            } else {
                rt = mid;
            }
        }
        sb.append(map.get(list.get(lt))).append("\n");
    }
}
