import java.io.*;
import java.util.*;

public class Solution {

	// LinkedList 구현
	static class Node {
		int data;
		Node next;

		public Node(int data) {
			this.data = data;
		}
	}

	static class LinkedList {
		Node head;
		Node tail;
		int cnt;

		public void insert(int index, int data) {
			Node newNode = new Node(data);
			if (index == 0) { // 맨 앞에 추가
				if (head != null) { // head가 있을 때
					newNode.next = head;
				}
				head = newNode;
			} else { // index가 1이상
				Node cur = head;
				for (int i = 0; i < index - 1; i++)
					cur = cur.next;
				newNode.next = cur.next;
				cur.next = newNode;
			}
			if (newNode.next == null)
				tail = newNode;
			cnt++;
		}

		public void delete(int index) {
			if (index == 0) {
				head = head.next;
			} else {
				Node cur = head;
				for (int i = 0; i < index - 1; i++)
					cur = cur.next;
				cur.next = cur.next.next;
			}
			cnt--;
		}

		public void change(int index, int data) {
			Node cur = head;
			for (int i = 0; i < index; i++)
				cur = cur.next;
			cur.data = data;
		}

		public int print(int index) {
			if (cnt < index) {
				return -1;
			} else {
				Node cur = head;
				for (int i = 0; i < index; i++)
					cur = cur.next;
				return cur.data;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			sb.append("#").append(test_case).append(" ");
			LinkedList list = new LinkedList();
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 수열의 길이
			int M = Integer.parseInt(st.nextToken()); // 추가 횟수
			int L = Integer.parseInt(st.nextToken()); // 인덱스 번호

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				list.insert(i, Integer.parseInt(st.nextToken()));

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				char cmd = st.nextToken().charAt(0);
				int x, y;
				switch (cmd) {
				case 'I':
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
					list.insert(x, y);
					break;
				case 'D':
					x = Integer.parseInt(st.nextToken());
					list.delete(x);
					break;
				case 'C':
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
					list.change(x, y);
					break;
				}
			}
			sb.append(list.print(L)).append("\n");
		}
		System.out.println(sb);
	}

}
