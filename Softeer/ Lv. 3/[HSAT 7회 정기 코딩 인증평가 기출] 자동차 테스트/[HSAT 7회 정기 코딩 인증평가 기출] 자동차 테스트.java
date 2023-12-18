import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringBuilder sb = new StringBuilder();
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      st = new StringTokenizer(br.readLine());
      ArrayList<Integer> car = new ArrayList<Integer>(); // 자동차의 연비
      for(int i = 0; i < n; i++)
        car.add(Integer.parseInt(st.nextToken()));
      int[] m = new int[q]; // 기대하는 중앙값
      for(int i = 0; i < q; i++)
        m[i] = Integer.parseInt(br.readLine());
      // 입력 종료

      // 중앙값의 수는 리스트에서 해당 값의 인덱스를 찾아서 좌우의 조합
      Collections.sort(car); // 자동차 리스트 정렬
      HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
      for(int i = 0; i < n; i++) 
        map.put(car.get(i), i);
      for(int i = 0; i < q; i++) {
        if(!map.containsKey(m[i])) {
          sb.append(0).append("\n");
          continue;
        }

        int left = map.get(m[i]); // 중앙값 왼쪽의 수        
        int right = car.size() - left - 1; // 중앙값 오른쪽의 수
        sb.append(left * right).append("\n");
      }

      System.out.println(sb);
    }
}
