import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        char[] arr = str.toCharArray();
        int acnt = 0;
        int bcnt = 0;
        for (char ch : arr) {
            if (ch == 'a')
                acnt++;
            else
                bcnt++;
        }

        int answer = arr.length;
        int start = 0;
        while (start < arr.length) {
            int cnt = 0;
            for (int i = 0; i < acnt; i++) {
                if (arr[(start + i) % arr.length] == 'b')
                    cnt++;
            }
            answer = Math.min(answer, cnt);
            start++;
        }

        System.out.println(answer);
    }
}
