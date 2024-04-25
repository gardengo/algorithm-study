import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int zeroCnt = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(st.nextToken());
            if (a == 0) {
                zeroCnt++;
            } else {
                list.add(a);
            }
        }
        Collections.sort(list);

        int beforeCnt = 0;
        int before = Integer.MIN_VALUE;
        HashMap<Integer, Integer> noSingle = new HashMap<>();
        for (int i : list) {
            if (before == i) {
                beforeCnt++;
            } else {
                if (beforeCnt > 0)
                    noSingle.put(before, beforeCnt + 1);
                beforeCnt = 0;
                before = i;
            }
        }
        if (beforeCnt > 0)
            noSingle.put(before, beforeCnt + 1);


        HashSet<Integer> processed = new HashSet<>();
        for (int i = 0; i < list.size(); i++)
            for (int j = i + 1; j < list.size(); j++)
                processed.add(list.get(i) + list.get(j));
        boolean canZero = processed.contains(0);

        int answer = 0;
        for (int i : list) {
            if (processed.contains(i)) {
                answer++;
                noSingle.remove(i);
            }
        }

        if (zeroCnt > 0) {
            for (int value : noSingle.values())
                answer += value;
        }
        if (canZero) {
            answer += zeroCnt;
        } else {
            if (zeroCnt > 2)
                answer += zeroCnt;
        }

        System.out.println(answer);
    }
}