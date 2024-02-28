import java.io.*;
import java.util.*;

public class Main {
    private static String str1, str2;
    private static int[][] LCS;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str1 = br.readLine();
        str2 = br.readLine();
        LCS = new int[str2.length() + 1][str1.length() + 1];

        for (int i = 1; i <= str2.length(); i++) {
            for (int j = 1; j <= str1.length(); j++) {
                if (str1.charAt(j - 1) == str2.charAt(i - 1)) {
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                } else {
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                }
            }
        }

        StringBuilder sb1 = new StringBuilder();
        int i = str2.length();
        int j = str1.length();
        while (true) {
            if (i == 0 || j == 0)
                break;
            if (LCS[i][j] == LCS[i - 1][j]) {
                i--;
                continue;
            }
            if (LCS[i][j] == LCS[i][j - 1]) {
                j--;
                continue;
            }

            sb1.append(str1.charAt(j - 1));
            i--;
            j--;
        }

        StringBuilder sb2 = new StringBuilder();
        sb2.append(LCS[str2.length()][str1.length()]);
        if (LCS[str2.length()][str1.length()] != 0)
            sb2.append("\n").append(sb1.reverse());
        System.out.println(sb2);
    }

}
