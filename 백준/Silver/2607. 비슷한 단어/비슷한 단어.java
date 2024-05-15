import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        HashMap<Character, Integer> map = new HashMap<>();
        String first = br.readLine();
        for (char ch : first.toCharArray()) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }

        int answer = 0;
        for (int i = 1; i < N; i++) {
            HashMap<Character, Integer> copy = new HashMap<>(map);
            String str = br.readLine();
            int cnt = str.length();

            for (char ch : str.toCharArray()) {
                if (copy.containsKey(ch)) {
                    cnt--;
                    if (copy.get(ch) == 1) {
                        copy.remove(ch);
                    } else {
                        copy.put(ch, copy.get(ch) - 1);
                    }
                }
            }

            int rm = 0;
            for (int j : copy.values())
                rm += j;

            if (-1 <= cnt && cnt <= 1 && rm <= 1)
                answer++;
        }

        System.out.println(answer);
    }
}
