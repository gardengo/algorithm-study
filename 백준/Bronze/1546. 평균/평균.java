import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        double sum = 0;
        int max = 0;
        for (int i = 0; i < N; i++) {
            int score = Integer.parseInt(st.nextToken());
            max = Math.max(max, score);
            sum += score;
        }
        System.out.println((sum / max * 100 / N));
    }
}