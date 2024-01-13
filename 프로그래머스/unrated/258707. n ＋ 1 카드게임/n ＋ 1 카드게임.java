import java.util.*;

class Solution {
    static LinkedList<Integer> list;
    public int solution(int coin, int[] cards) {
        // 카드는 1 ~ n까지 중복없이 존재
        // n + 1을 내야되는데 만들 수 있는 경우의 수는 최대 (n / 3 + coin) / 2
        // 최대한 많은 라운드를 가기 위해서는 처음 손에 들고 있는 카드를 다 써야함
        int n = cards.length;
        int round = 1;
        LinkedList<Integer> list = new LinkedList<Integer>(); // 가지고 있는 카드들
        LinkedList<Integer> trash = new LinkedList<Integer>(); // 안먹고 버려진 카드들

        // 초기 값을 리스트에 넣는다
        int i = 0;
        for(; i < n / 3; i++)
            list.add(cards[i]);

        // 카드를 2개씩 꺼내며 가질 것인지 판단
        for(; i < n; i += 2) {
            int firstCard = cards[i];
            int secondCard = cards[i + 1];
            if(list.contains(n + 1 - firstCard)) {
                if(coin > 0){
                    list.add(firstCard);
                    coin--;
                }
            } else {
                trash.add(firstCard);
            }
            if(list.contains(n + 1 - secondCard)) {
                if(coin > 0){
                    list.add(secondCard);
                    coin--;
                }
            } else {
                trash.add(secondCard);
            }
            // 만약 지금 가진 카드와는 n + 1이 안되는데 먹어야 될 때 이것을 어떻게 판단?
            // 버려지는 것들을 따로 담아놓고 되살리기?

            // 카드 2장을 버린다 버릴 수 없으면 게임 종료
            if(canDrop(list, n)) {
                dropCard(list, n);
                round++;
            } else {
                // 버려진 카드들 중에서 2장을 낼 수 있다면 그 2개를 먹고 버리자
                if(canDrop(trash, n)) {
                    if(coin >= 2) {
                        dropCard(trash, n);
                        coin -= 2;
                        round++;
                        continue;
                    }
                }
                break;
            }
        }

        int answer = round;
        return answer;
    }

    // 현재 가진 카드 리스트에서 카드를 낼 수 있는지 판단
    public boolean canDrop(LinkedList<Integer> list, int n) {
        for(int i = 0; i < list.size(); i++) {
            int num = list.get(i);
            if(list.contains(n + 1 - num)) 
                return true;
        }
        return false;
    }

    // 카드 2개 버리기
    public void dropCard(LinkedList<Integer> list, int n) {
        for(int i = 0; i < list.size(); i++) {
            int num = list.get(i);
            if(list.contains(n + 1 - num)) {
                list.remove((Object)num);
                list.remove((Object)(n + 1 - num));
                return;
            }
        }
    }
}