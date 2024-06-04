import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        int zeroCnt = 0;
        int oneCnt = 0;
        for (char ch : S.toCharArray()) {
            if (ch == '0') zeroCnt++;
            else oneCnt++;
        }

        StringBuilder sb = new StringBuilder(S);
        zeroCnt = zeroCnt / 2;
        oneCnt = oneCnt / 2;
        for (int i = 0; i < sb.length(); i++) {
            if (oneCnt > 0 && sb.charAt(i) == '1') {
                sb.deleteCharAt(i);
                oneCnt--;
                i--;
                continue;
            }
            if (zeroCnt <= 0 && sb.charAt(i) == '0') {
                sb.deleteCharAt(i);
                i--;
                continue;
            }
            if (zeroCnt > 0 && sb.charAt(i) == '0') {
                zeroCnt--;
            }
        }

        System.out.println(sb);
    }

}
