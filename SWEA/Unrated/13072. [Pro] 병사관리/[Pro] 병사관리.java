class UserSolution {
	public class Node {
		int mId;
		int vers;
		Node next;
	}

	public Node[] node = new Node[200001];
	public int cnt = 0;
	public int[] version = new int[100001];
	public int[] tNum = new int[100001];

	public Node getNewNode(int mId, Node next) {
		Node newNode = node[cnt++];
		newNode.mId = mId;
		newNode.next = next;
		newNode.vers = ++version[mId];
		return newNode;
	}

	public class Team { // 팀 마다 점수 별로 1~5 생성
		Node[] head = new Node[6];
		Node[] tail = new Node[6];
	}

	public Team[] team = new Team[6]; // 팀 1~5 생성

	public void init() {
		cnt = 0;
		for (int i = 0; i < 200001; i++) {
			if (node[i] == null)
				node[i] = new Node();
		}
		for (int i = 1; i <= 5; i++) {
			team[i] = new Team();
			for (int j = 1; j <= 5; j++) {
				team[i].head[j] = team[i].tail[j] = new Node();
			}
		}
		for (int i = 0; i < 100001; i++) {
			version[i] = 0;
			tNum[i] = 0;
		}
	}

	public void hire(int mID, int mTeam, int mScore) {
		Node newNode = getNewNode(mID, null);
		team[mTeam].tail[mScore].next = newNode;
		team[mTeam].tail[mScore] = newNode;
		tNum[mID] = mTeam;
	}

	public void fire(int mID) {
		version[mID]++;
	}

	public void updateSoldier(int mID, int mScore) {
		hire(mID, tNum[mID], mScore);
	}

	public void updateTeam(int mTeam, int mChangeScore) {
		if (mChangeScore > 0) {
			for (int i = 4; i >= 1; i--) {
				if (team[mTeam].head[i].next == null)
					continue;
				int mcs = mChangeScore + i;
				if (mcs > 5)
					mcs = 5;

				team[mTeam].tail[mcs].next = team[mTeam].head[i].next;
				team[mTeam].tail[mcs] = team[mTeam].tail[i];
				team[mTeam].head[i].next = null;
				team[mTeam].tail[i] = team[mTeam].head[i];
			}
		} else if (mChangeScore < 0) {
			for (int i = 2; i <= 5; i++) {
				if (team[mTeam].head[i].next == null)
					continue;
				int mcs = mChangeScore + i;
				if (mcs < 1)
					mcs = 1;
				team[mTeam].tail[mcs].next = team[mTeam].head[i].next;
				team[mTeam].tail[mcs] = team[mTeam].tail[i];
				team[mTeam].head[i].next = null;
				team[mTeam].tail[i] = team[mTeam].head[i];
			}
		}
	}

	public int bestSoldier(int mTeam) {
		for (int i = 5; i >= 1; i--) {
			Node cur = team[mTeam].head[i];
			if (cur.next == null)
				continue;

			int answer = 0;
			while (cur.next != null) {
				cur = cur.next;
				if (cur.vers == version[cur.mId])
					answer = cur.mId > answer ? cur.mId : answer;
			}
			if (answer != 0)
				return answer;
		}
		return 0;
	}
}
