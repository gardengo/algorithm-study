import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	private static List<Node> heap;

	private static class Node {
		int x;
		int abs;

		public Node(int x, int abs) {
			this.x = x;
			this.abs = abs;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // 입력을 위한 BufferedReader 객체 생성
		StringBuilder sb = new StringBuilder(); // 출력을 위한 StringBuilder 객체 생성
		int N = Integer.parseInt(br.readLine()); // 연산의 개수 N
		heap = new ArrayList<Node>(); // Node를 갖는 heap 생성
		heap.add(new Node(0, 0)); // 0번 인덱스는 사용하지 않는다

		for (int i = 1; i <= N; i++) {
			int x = Integer.parseInt(br.readLine()); // 연산에 대한 정보를 나타내는 정수 x
			if (x == 0) { // x가 0이면 절댓값이 가장 작은 값 출력
				if (heap.size() == 1) { // 힙이 비어있는 경우
					sb.append(0).append("\n"); // 0 출력
				} else { // 힙이 비어있지 않은 경우
					sb.append(heap.get(1).x).append("\n"); // 절댓값이 가장 작은 값 출력
					deleteNode(); // 마지막 노드를 삭제
				}
			} else { // x가 0이 아닌 경우
				Node node = new Node(x, Math.abs(x)); // (정수 x, x의 절댓값)를 저장하는 노드
				heap.add(node); // 힙의 마지막에 노드를 추가한다
				if (heap.size() != 2) { // heap에 노드가 2개면 시행하지 않는다(0은 사용하지 않으므로 사실상 1개)
					addNode(); // 노드 추가
				}
			}
		}
		System.out.println(sb); // 출력

	}

	private static void addNode() {
		int pos = heap.size() - 1; // 마지막 노드부터 시작
		while (pos / 2 > 0) {
			if (heap.get(pos).abs == heap.get(pos / 2).abs) { // 절댓값이 같을 때
				if (heap.get(pos).x < heap.get(pos / 2).x) { // 추가된 노드의 x가 부모 노드의 x보다 작을 때
					Node tmp = heap.get(pos);
					heap.set(pos, heap.get(pos / 2));
					heap.set(pos / 2, tmp);
					pos = pos / 2;
				} else {
					break;
				}
			} else if (heap.get(pos).abs < heap.get(pos / 2).abs) { // 추가된 노드의 절댓값이 부모 노드의 절댓값보다 작을 때
				Node tmp = heap.get(pos);
				heap.set(pos, heap.get(pos / 2));
				heap.set(pos / 2, tmp);
				pos = pos / 2;
			} else {
				break;
			}
		}
	}

	private static void deleteNode() {
		heap.set(1, heap.get(heap.size() - 1)); // 힙의 마지막 노드를 루트에 삽입
		heap.remove(heap.size() - 1); // 힙의 마지막 노드 삭제
		if (heap.size() > 2) { // 힙이 비어있거나 노드가 1개면 시행하지 않는다
			int pos = 1; // 루트 노드부터 시작
			while (pos * 2 < heap.size()) { // 왼쪽 자식 노드가 있다면
				int x = 0; // 자식 노드의 x
				int abs = 0; // 자식 노드의 절댓값
				int index = 0; // 자식 노드의 인덱스
				if (pos * 2 + 1 < heap.size()) { // 오른쪽 자식 노드가 있다면
					if (heap.get(pos * 2).abs == heap.get(pos * 2 + 1).abs) { // 왼쪽과 오른쪽의 절댓값이 같다면
						if (heap.get(pos * 2).x <= heap.get(pos * 2 + 1).x) { // 왼쪽 자식 노드의 x가 더 작을때
							x = heap.get(pos * 2).x; // 왼쪽 자식 노드의 x 저장
							abs = heap.get(pos * 2).abs; // 왼쪽 자식 노드의 절댓값 저장
							index = pos * 2; // 왼쪽 자식 노드의 인덱스 저장
						} else { // 오른쪽 자식 노드의 x가 더 작을때
							x = heap.get(pos * 2 + 1).x; // 오른쪽 자식 노드의 x 저장
							abs = heap.get(pos * 2 + 1).abs; // 오른쪽 자식 노드의 절댓값 저장
							index = pos * 2 + 1; // 오른쪽 자식 노드의 인덱스 저장
						}
					} else { // 왼쪽과 오른쪽의 절댓값이 다르다면
						if (heap.get(pos * 2).abs < heap.get(pos * 2 + 1).abs) { // 왼쪽 자식 노드의 절댓값이 더 작을때
							x = heap.get(pos * 2).x; // 왼쪽 자식 노드의 x 저장
							abs = heap.get(pos * 2).abs; // 왼쪽 자식 노드의 절댓값 저장
							index = pos * 2; // 왼쪽 자식 노드의 인덱스 저장
						} else { // 오른쪽 자식 노드의 절댓값이 더 작을때
							x = heap.get(pos * 2 + 1).x; // 오른쪽 자식 노드의 x 저장
							abs = heap.get(pos * 2 + 1).abs; // 오른쪽 자식 노드의 절댓값 저장
							index = pos * 2 + 1; // 오른쪽 자식 노드의 인덱스 저장
						}
					}
				} else { // 오른쪽 자식 노드가 없다면
					x = heap.get(pos * 2).x; // 왼쪽 자식 노드의 x 저장
					abs = heap.get(pos * 2).abs; // 왼쪽 자식 노드의 절댓값 저장
					index = pos * 2; // 왼쪽 자식 노드의 인덱스 저장
				}

				if (abs == heap.get(pos).abs && x >= heap.get(pos).x) // 절댓값이 같고 부모 노드의 x가 더 작으면
					break;
				if (abs > heap.get(pos).abs) // 부모 노드의 절댓값이 더 작으면
					break; // 멈춘다

				Node tmp = heap.get(pos);
				heap.set(pos, heap.get(index));
				heap.set(index, tmp);
				pos = index;
			}
		}
	}

}
