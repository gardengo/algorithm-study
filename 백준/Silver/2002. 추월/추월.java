import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<String> start = new ArrayList<>();
        HashMap<Integer, String> end = new HashMap<>();
        for (int i = 0; i < N; i++)
            start.add(br.readLine());
        for (int i = 0; i < N; i++)
            end.put(i, br.readLine());

        int answer = 0;
        for (int i = 0; i < N; i++) {
            String ecar = end.get(i);
            String scar = start.get(i - answer);
            if (!ecar.equals(scar)) {
                answer++;
                start.remove(ecar);
            }
        }

        System.out.println(answer);
    }
}