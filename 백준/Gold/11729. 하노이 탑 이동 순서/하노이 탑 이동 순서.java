import java.io.*;
import java.util.*;

public class Main {
    private static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int cnt = (int) (Math.pow(2, N) - 1);
        sb.append(cnt).append("\n");
        move(N, 1, 2, 3);
        System.out.println(sb);
    }

    private static void move(int cnt, int start, int middle, int end) {
        if (cnt == 1) {
            sb.append(start).append(" ").append(end).append("\n");
            return;
        }

        move(cnt - 1, start, end, middle);
        sb.append(start).append(" ").append(end).append("\n");
        move(cnt - 1, middle, start, end);
    }
}