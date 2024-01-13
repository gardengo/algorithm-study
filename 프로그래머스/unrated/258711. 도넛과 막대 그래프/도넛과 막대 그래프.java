import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int n = edges.length; // 간선의 개수
        int createNum = 0; // 생성한 정점의 번호
        int donut = 0;
        int pipe = 0;
        int eight = 0;

        // 도넛 모양 그래프는 출발 정점으로 돌아오고 들어오는 간선 1개, 나가는 간선 1개
        // 막대 모양 그래프는 들어오는 간선 1개, 나가는 간선 1개 / 단, 시작과 끝은 1개씩만
        // 8자 모양 그래프는 출발 정점으로 돌아오고 중심부만 들어오는 간선 2개, 나가는 간선 2개
        // 임의의 한 정점을 추가하고 각 그래프에 간선을 연결하면 연결된 간선의 수가 맞지 않게 된다
        // 들어오는 간선과 나가는 간선을 모두 확인하기 위해서 그래프 2개 만들기

        // 정점이 최대 100만개, 간선도 최대 100만개이므로 리스트로 구현
        ArrayList<ArrayList<Integer>> startGraph = new ArrayList<ArrayList<Integer>>();
        ArrayList<ArrayList<Integer>> endGraph = new ArrayList<ArrayList<Integer>>();
        for(int i = 0; i <= 1000000; i++) {
            startGraph.add(new ArrayList<Integer>());
            endGraph.add(new ArrayList<Integer>());
        }

        // 연결된 정점들을 그래프에 저장한다
        for(int i = 0; i < n; i++) {
            startGraph.get(edges[i][0]).add(edges[i][1]);
            endGraph.get(edges[i][1]).add(edges[i][0]);
        }

        // 출발 정점이 2개 이상인데 도착 정점이 0인 지점이 추가된 정점
        for(int i = 0; i <= 1000000; i++){
            int startCnt = startGraph.get(i).size();
            int endCnt = endGraph.get(i).size();
            if(startCnt >= 2 && endCnt == 0) {
                createNum = i;
                break;
            }
        }

        // 추가된 정점에서 연결된 간선을 모두 지우자
        ArrayList<Integer> list = startGraph.get(createNum);
        for(int num : list) 
            endGraph.get(num).remove((Object)createNum);

        // 추가된 정점에서 연결된 간선의 수가 총 그래프의 수
        // 연결 했던 정점으로 이동해서 어떤 유형인지 파악하자
        root: for(int num : list) {
            int curNum = num;

            while(true) {
                ArrayList<Integer> curList = startGraph.get(curNum);
                int size = curList.size();
                if(size == 0) {
                    pipe++;
                    continue root;
                } else if(size == 2) {
                    eight++;
                    continue root;
                } else if(curList.get(0) == num) {
                    donut++;
                    continue root;
                }
                curNum = curList.get(0);
            }
        }

        int[] answer = {createNum, donut, pipe, eight};
        return answer;
    }
}