import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();

		String[] arr = str.split("\\+|-");
		int plus = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '+')
				plus++;
			else if (str.charAt(i) == '-')
				break;
		}

		int answer = Integer.parseInt(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			if (plus > 0) {
				answer += Integer.parseInt(arr[i]);
				plus--;
			} else {
				answer -= Integer.parseInt(arr[i]);
			}
		}

		System.out.println(answer);
	}

}
