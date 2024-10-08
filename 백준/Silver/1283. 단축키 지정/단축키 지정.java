import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        HashSet<Character> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String[] arr = br.readLine().split(" ");
            boolean flag = false;

            for (int j = 0; j < arr.length; j++) {
                if (!set.contains(Character.toLowerCase(arr[j].charAt(0)))) {
                    set.add(Character.toLowerCase(arr[j].charAt(0)));

                    char[] chars = new char[arr[j].length() + 2];
                    chars[0] = '[';
                    chars[1] = arr[j].charAt(0);
                    chars[2] = ']';
                    for (int k = 1; k < arr[j].length(); k++)
                        chars[k + 2] = arr[j].charAt(k);
                    arr[j] = String.valueOf(chars);

                    flag = true;
                    break;
                }
            }

            if (!flag) {
                root:
                for (int j = 0; j < arr.length; j++) {
                    for (int k = 0; k < arr[j].length(); k++) {
                        if (!set.contains(Character.toLowerCase(arr[j].charAt(k)))) {
                            set.add(Character.toLowerCase(arr[j].charAt(k)));

                            char[] chars = new char[arr[j].length() + 2];
                            for (int l = 0; l < k; l++)
                                chars[l] = arr[j].charAt(l);
                            chars[k] = '[';
                            chars[k + 1] = arr[j].charAt(k);
                            chars[k + 2] = ']';
                            for (int l = k + 1; l < arr[j].length(); l++)
                                chars[l + 2] = arr[j].charAt(l);
                            arr[j] = String.valueOf(chars);

                            break root;
                        }
                    }
                }
            }

            for (String str : arr)
                sb.append(str).append(" ");
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
