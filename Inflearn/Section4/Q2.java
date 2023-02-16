package Section4;

import java.util.HashMap;
import java.util.Scanner;

public class Q2 { //아나그램(해쉬)
    public String solution(String s1, String s2) {
        String answer = "";
        HashMap<Character, Integer> map1 = new HashMap<>();
        HashMap<Character, Integer> map2 = new HashMap<>();
        for (char x : s1.toCharArray())
            map1.put(x, map1.getOrDefault(x, 0) + 1);
        for (char y : s2.toCharArray())
            map2.put(y, map2.getOrDefault(y, 0) + 1);
        if (map1.equals(map2)) answer = "YES";
        else answer = "NO";
        return answer;
    }

    public static void main(String[] args) {
        Q2 T = new Q2();
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        String s2 = sc.next();
        System.out.print(T.solution(s1, s2));
    }
}
