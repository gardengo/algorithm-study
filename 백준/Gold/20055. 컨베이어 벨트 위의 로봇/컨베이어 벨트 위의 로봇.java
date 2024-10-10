import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        LinkedList<Integer> A = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++)
            A.add(Integer.parseInt(st.nextToken()));

        ArrayList<Integer> list = new ArrayList<>(); // 로봇 리스트
        int step = 0;
        int cnt = 0;

        while (cnt < K) {
            step++;

            // 1. 벨트가 회전
            int last = A.removeLast();
            A.addFirst(last);
            for (int i = 0; i < list.size(); i++) {
                int robot = list.get(i);
                robot = (robot + 1) % (2 * N);
                if (robot == N - 1) {
                    list.remove(i);
                    i--;
                } else {
                    list.set(i, robot);
                }
            }

            // 2. 로봇 이동
            for (int i = 0; i < list.size(); i++) {
                int cur = list.get(i);
                int next = (cur + 1) % (2 * N);

                boolean flag = false;
                if (A.get(next) > 0) {
                    for (int robot : list) {
                        if (robot == next) {
                            flag = true;
                            break;
                        }
                    }
                    if (!flag) {
                        A.set(next, A.get(next) - 1);
                        list.set(i, next);

                        if (A.get(next) == 0)
                            cnt++;

                        if (next == N - 1) {
                            list.remove(i);
                            i--;
                        }
                    }
                }
            }

            // 3. 로봇 올리기
            if (A.get(0) > 0) {
                boolean flag = false;
                for (int robot : list) {
                    if (robot == 0) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    A.set(0, A.get(0) - 1);
                    list.add(0);

                    if (A.get(0) == 0)
                        cnt++;
                }
            }
        }

        System.out.println(step);
    }
}
