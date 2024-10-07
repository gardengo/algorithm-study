import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        int answer = 0;
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (y != 0 && stack.isEmpty()) {
                stack.push(y);
            } else {
                while (!stack.isEmpty() && stack.peek() > y) {
                    stack.pop();
                    answer++;
                }
                if (y != 0 && (stack.isEmpty() || stack.peek() < y))
                    stack.push(y);
            }
        }

        System.out.println(answer + stack.size());
    }
}
