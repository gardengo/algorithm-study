package Section4;

import java.util.HashMap;
import java.util.Scanner;

public class Q4 { //모든 아나그램 찾기(해쉬, 투포인터, 슬라이딩 윈도우)
    public int solution(String s, String t) {
        int answer = 0;
        HashMap<Character, Integer> smap = new HashMap<>();
        HashMap<Character, Integer> tmap = new HashMap<>();
        for (int i = 0; i < t.length(); i++)
            tmap.put(t.charAt(i), tmap.getOrDefault(t.charAt(i), 0) + 1);
        for (int i = 0; i < t.length() - 1; i++)
            smap.put(s.charAt(i), smap.getOrDefault(s.charAt(i), 0) + 1);
        int lt = 0;
        for (int rt = t.length() - 1; rt < s.length(); rt++) {
            smap.put(s.charAt(rt), smap.getOrDefault(s.charAt(rt), 0) + 1);
            if (smap.equals(tmap)) answer++;
            smap.put(s.charAt(lt), smap.get(s.charAt(lt)) - 1);
            if (smap.get(s.charAt(lt)) == 0) smap.remove(s.charAt(lt));
            lt++;
        }
        return answer;
    }

    public static void main(String[] args) {
        Q4 T = new Q4();
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        String t = sc.next();
        System.out.print(T.solution(s, t));
    }
}
