import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		String S = s.next().toUpperCase();
		int[] alpha = new int[26];
		char answer;

		char[] C = S.toCharArray();
		for (int i = 0; i < C.length; i++)
			alpha[C[i] - 65]++;

		int key = 0;
		int fre = -1; // 최다빈도
		boolean wild = false; // 중복처리
		for (int i = 0; i < alpha.length; i++)
			if (fre < alpha[i]) {
				fre = alpha[i];
				key = i;
				wild = false;
			} else if (fre == alpha[i])
				wild = true;
		answer = (char) (key + 65);

		if (wild == true)
			System.out.println("?");
		else
			System.out.println(answer);
	}

}