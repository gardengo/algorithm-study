package Section1;

import java.util.ArrayList;
import java.util.Scanner;

public class Q4 { //단어 뒤집기

    public ArrayList<String> solution(String[] str) {
        ArrayList<String> answer = new ArrayList<>();
        for (String x : str) {
            char[] ch = x.toCharArray();
            int lt = 0;
            int rt = ch.length - 1;
            while (lt < rt) {
                char tmp = ch[lt];
                ch[lt] = ch[rt];
                ch[rt] = tmp;
                lt++;
                rt--;
            }
            String tmp = String.valueOf(ch);
            answer.add(tmp);
        }
        return answer;
    }

    public static void main(String[] args) {
        Q4 T = new Q4();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] str = new String[n];
        for (int i = 0; i < n; i++) str[i] = sc.next();
        for (String x : T.solution(str)) System.out.println(x);
    }
}
