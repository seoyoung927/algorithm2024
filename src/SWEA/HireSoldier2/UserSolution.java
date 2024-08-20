package HireSoldier2;

class UserSolution {
    final int MAX_NUM = 100005;
    final int MAX_TEAM = 5;
    final int MAX_SCORE = 5;
    Node[] node = new Node[4*MAX_NUM];
    Team[] team = new Team[MAX_TEAM+1];
    int[] version = new int[MAX_NUM];
    int[] teamOfSoldier = new int[MAX_NUM+1];
    int cnt = 0;

    class Team{
        Node[] head = new Node[MAX_SCORE+1];
        Node[] tail = new Node[MAX_SCORE+1];

        public Team(){
            for(int i=1; i<MAX_SCORE+1; i++) {
                head[i] = new Node();
                tail[i] = head[i];
            }
        }
    }

    class Node{
        int mID;
        int version;
        Node next;

        public Node() {}

        @Override
        public String toString(){
            return "mID="+mID+"mTeam="+teamOfSoldier[mID];
        }
    }

    public void init() {
        for(int i=0, SIZE=node.length; i<SIZE; i++) node[i] = new Node();
        for(int i=1; i<MAX_TEAM+1; i++) team[i] = new Team();
    }

    /**
     * 고유번호가 mID, 소속팀이 mTeam, 평판 점수가 mScore인 병사를 고용한다.
     * @param mID 고유번호 (1 ≤ mID ≤ 100,000)
     * @param mTeam 소속팀 (1 ≤ mTeam ≤ 5)
     * @param mScore 평판 점수 (1 ≤ mScore ≤ 5)
     */
    public void hire(int mID, int mTeam, int mScore) {
        version[mID]++;
        node[cnt].mID = mID;
        node[cnt].version = version[mID];
        teamOfSoldier[mID] = mTeam;
        team[mTeam].tail[mScore].next = node[cnt];
        team[mTeam].tail[mScore] = node[cnt];
        cnt++;
    }

    /**
     * 고유번호가 mID인 병사를 해고한다.
     * @param mID
     */
    public void fire(int mID) {
        version[mID]++;
    }

    /**
     * 고유번호가 mID인 병사의 평판 점수를 mScore로 변경한다.
     * @param mID
     * @param mScore
     */
    public void updateSoldier(int mID, int mScore) {
        int mTeam = teamOfSoldier[mID];
        version[mID]++;
        node[cnt].mID = mID;
        node[cnt].version = version[mID];
        team[mTeam].tail[mScore].next = node[cnt];
        team[mTeam].tail[mScore] = node[cnt];
        cnt++;
    }

    /**
     * 소속팀이 mTeam인 병사들의 평판 점수를 모두 변경한다.
     * @param mTeam
     * @param mChangeScore
     */
    public void updateTeam(int mTeam, int mChangeScore) {
        if(mChangeScore>=0){
            for(int score = MAX_SCORE; score>=1; score--){
                int updatedScore = score+mChangeScore;
                if(updatedScore>5) updatedScore = 5;
                if(team[mTeam].head[score].next==null) continue;
                if(score==updatedScore) continue;
                team[mTeam].tail[updatedScore].next = team[mTeam].head[score].next;
                team[mTeam].tail[updatedScore] = team[mTeam].tail[score];
                team[mTeam].head[score].next = null;
                team[mTeam].tail[score] = team[mTeam].head[score];
            }
        }else{
            for(int score = 2; score<= MAX_SCORE; score++){
                int updatedScore = score+mChangeScore;
                if(updatedScore<1) updatedScore = 1;
                if(team[mTeam].head[score].next==null) continue;
                if(score==updatedScore) continue;
                team[mTeam].tail[updatedScore].next = team[mTeam].head[score].next;
                team[mTeam].tail[updatedScore] = team[mTeam].tail[score];
                team[mTeam].head[score].next = null;
                team[mTeam].tail[score] = team[mTeam].head[score];
            }
        }
    }

    /**
     * 소속팀이 mTeam인 병사들 중 평판 점수가 가장 높은 병사의 고유번호를 반환한다.
     * 평판 점수가 가장 높은 병사가 여러 명일 경우, 고유번호가 가장 큰 병사의 고유번호를 반환한다.
     * @param mTeam
     * @return
     */
    public int bestSoldier(int mTeam) {
        int ret = 0;
        for(int score = 5; score>=1; score--){
            if(ret!=0) break;

            Node cur = team[mTeam].head[score].next;
            while(cur!=null){
                if(cur.version == version[cur.mID]){
                    ret = Math.max(ret, cur.mID);
                }
                cur = cur.next;
            }
        }

        return ret;
    }
}