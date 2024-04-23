import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str = br.readLine();

        char begin = str.charAt(0);
        char end = str.charAt(N - 1);
        int beginIndex = 0;
        int endIndex = 0;
        for (int i = 0; i < N; i++) {
            if (str.charAt(i) != begin) {
                beginIndex = i - 1;
                break;
            }
        }
        for (int i = N - 1; i >= 0; i--) {
            if (str.charAt(i) != end) {
                endIndex = i + 2;
                break;
            }
        }

        str = str.substring(beginIndex, endIndex);
        N = str.length();

        int startCnt = 0;
        int endCnt = 0;
        boolean startPlus = false;
        boolean endPlus = false;

        for (int i = 0; i < N; i++) {
            if (str.charAt(i) != begin) {
                startPlus = true;
            }
            if (str.charAt(N - 1 - i) != end) {
                endPlus = true;
            }
            if (str.charAt(i) == begin && startPlus) {
                startCnt++;
            }
            if (str.charAt(N - 1 - i) == end && endPlus) {
                endCnt++;
            }
        }

        System.out.println(Math.min(startCnt, endCnt));
    }
}