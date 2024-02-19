import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.*;
import java.util.*;

public class Main {
    static boolean finish;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        StringBuilder sb = new StringBuilder(T);
        finish = false;

        dfs(S, T);
        
        if (finish)
            return;
        System.out.println(0);
    }

    public static void dfs(String S, String T) {
        if (finish)
            return;

        if (S.length() == T.length()) {
            if (S.equals(T)) {
                System.out.println(1);
                finish = true;
            }
            return;
        }

        if (T.charAt(T.length() - 1) == 'A') {
            dfs(S, T.substring(0, T.length() - 1));
        }

        if (T.charAt(0) == 'B') {
            String subString = T.substring(1, T.length());
            StringBuilder sb = new StringBuilder(subString);
            dfs(S, sb.reverse().toString());
        }
    }
}
