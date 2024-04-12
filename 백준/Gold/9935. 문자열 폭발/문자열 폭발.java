import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char[] bomb = br.readLine().toCharArray();
        int bombSize = bomb.length;

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            stack.push(str.charAt(i));
            int stackSize = stack.size();

            if (stackSize >= bombSize) {
                if (stack.peek() == bomb[bombSize - 1]) {
                    boolean flag = true;
                    for (int j = 1; j < bombSize; j++) {
                        if (stack.get(stackSize - 1 - j) != bomb[bombSize - 1 - j]) {
                            flag = false;
                            break;
                        }
                    }

                    if (flag) {
                        for (int j = 0; j < bombSize; j++)
                            stack.pop();
                    }
                }
            }
        }

        if (stack.isEmpty()) {
            System.out.println("FRULA");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < stack.size(); i++)
                sb.append(stack.get(i));
            System.out.println(sb);
        }
    }
}