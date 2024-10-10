import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        int idx = 0;
        int num = 0;
        while (idx < str.length()) {
            num++;
            String nstr = String.valueOf(num);
            for (int i = 0; i < nstr.length(); i++) {
                if (nstr.charAt(i) == str.charAt(idx))
                    idx++;
                if (idx >= str.length())
                    break;
            }
        }

        System.out.println(num);
    }
}
