import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String[] minDp = new String[101];
        String[] maxDp = new String[101];

        minDp[2] = "1";
        minDp[3] = "7";
        minDp[4] = "4";
        minDp[5] = "2";
        minDp[6] = "6";
        minDp[7] = "8";
        minDp[8] = "10";
        minDp[9] = "18";
        minDp[10] = "22";


        for (int i = 5; i <= 94; i++) {
            if (minDp[i] != null)
                minDp[i + 6] = minDp[i] + "0";
        }
        for (int i = 5; i <= 93; i++) {
            if (minDp[i] != null) {
                if (minDp[i + 7].length() == minDp[i].length() + 1) {
                    if (minDp[i + 7].compareTo(minDp[i]) > 0) {
                        minDp[i + 7] = minDp[i] + "8";
                    }
                } else {
                    minDp[i + 7] = minDp[i] + "8";
                }
            }
        }

        maxDp[2] = "1";
        maxDp[3] = "7";
        for (int i = 2; i <= 98; i++) {
            if (maxDp[i] != null)
                maxDp[i + 2] = maxDp[i] + "1";
        }

//        System.out.println(Arrays.toString(minDp));
//        System.out.println(Arrays.toString(maxDp));

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(minDp[n]).append(" ").append(maxDp[n]).append("\n");
        }

        System.out.println(sb);
    }
}