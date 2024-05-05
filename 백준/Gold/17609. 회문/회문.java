import java.io.*;
import java.util.*;

public class Main {
    private static String str;
    private static int result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            str = br.readLine();
            result = 2;
            palindrome(0, str.length() - 1, false);
            System.out.println(result);
        }
    }

    private static void palindrome(int lt, int rt, boolean visited) {
        if (lt >= rt) {
            if (visited)
                result = 1;
            else
                result = 0;
            return;
        }

        if (str.charAt(lt) == str.charAt(rt)) {
            palindrome(lt + 1, rt - 1, visited);
            return;
        }

        if (visited)
            return;
        if (str.charAt(lt + 1) == str.charAt(rt))
            palindrome(lt + 2, rt - 1, true);
        if (str.charAt(lt) == str.charAt(rt - 1))
            palindrome(lt + 1, rt - 2, true);
    }
}