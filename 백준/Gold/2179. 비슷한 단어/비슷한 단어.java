import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        TreeMap<String, Integer> map = new TreeMap<>();
        TreeMap<Integer, String> result = new TreeMap<>();
        for (int i = 0; i < N; i++)
            map.put(br.readLine(), i);

        int max = 0;
        String before = "";
        for (String cur : map.keySet()) {
            int size = Math.min(before.length(), cur.length());
            int cnt = size;
            for (int i = 0; i < size; i++) {
                if (before.charAt(i) != cur.charAt(i)) {
                    cnt = i;
                    break;
                }
            }

            if (max < cnt) {
                result.clear();
                max = cnt;
                result.put(map.get(before), before);
                result.put(map.get(cur), cur);
            } else if (cnt != 0 && max == cnt) {
                result.put(map.get(before), before);
                result.put(map.get(cur), cur);
            }
            before = cur;
        }
        
        String first = result.remove(result.firstKey());
        String second = "";
        for (String str : result.values()) {
            if (first.substring(0, max).equals(str.substring(0, max))) {
                second = str;
                break;
            }
        }

        sb.append(first).append("\n").append(second);
        System.out.println(sb);
    }
}