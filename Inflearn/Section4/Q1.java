package Section4;

import java.util.HashMap;
import java.util.Scanner;

public class Q1 { //학급 회장(해쉬)
    public char solution(int n, char[] arr) {
        char answer = ' ';
        int max = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (char x : arr)
            map.put(x, map.getOrDefault(x, 0) + 1);
        for (char key : map.keySet())
            if (map.get(key) > max) {
                max = map.get(key);
                answer = key;
            }
        return answer;
    }

    public static void main(String[] args) {
        Q1 T = new Q1();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[] arr = sc.next().toCharArray();
        System.out.print(T.solution(n, arr));
    }
}
