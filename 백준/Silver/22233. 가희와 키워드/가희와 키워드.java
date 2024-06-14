import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashSet<String> set = new HashSet<>();
        for (int i = 1; i <= N; i++)
            set.add(br.readLine());

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine(), ",");
            while(st.hasMoreTokens())
                set.remove(st.nextToken());
            sb.append(set.size()).append("\n");
        }

        System.out.println(sb);
    }
}