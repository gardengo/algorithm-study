import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Integer>[] stacks = new Stack[4];
        for (int i = 0; i < 4; i++)
            stacks[i] = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        loop:
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 4; j++) {
                if (stacks[j].isEmpty() || stacks[j].peek() <= a) {
                    stacks[j].push(a);
                    continue loop;
                }
            }
            System.out.println("NO");
            return;
        }
        System.out.println("YES");
    }
}