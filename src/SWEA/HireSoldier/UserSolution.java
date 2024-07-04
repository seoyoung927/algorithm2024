package SWEA.HireSoldier;

class UserSolution{
    final int MAX_MID = 100_000;
    final int MAX_TEAM = 5;
    final int MAX_SCORE = 5;

    class Node{
        int mID;
        int version;
        Node next;

        public Node() {}
    }

    int[] teamOfSoldier = new int[MAX_MID+1];
    int[] version = new int[MAX_MID+1];

    Node[] node = new Node[4*MAX_MID];
    int cnt = 0;
    public Node getNewNode(int mID, Node next){
        node[cnt] = new Node();
        node[cnt].mID = mID;
        node[cnt].next = next;
        node[cnt].version = ++version[mID];
        return node[cnt++];
    }

    class Team{
        Node[] head = new Node[MAX_TEAM+1];
        Node[] tail = new Node[MAX_TEAM+1];

        public Team(){
            for(int i=1; i<=MAX_TEAM; i++){
                head[i] = getNewNode(0,null);
                tail[i] = head[i];
            }
        }
    }
    Team[] team = new Team[MAX_TEAM+1];

    public void init() {
        //초기화
        for(int i=1; i<=MAX_SCORE; i++){
            team[i] = new Team();
        }
    }

    /**
     * 고유번호가 mID, 소속팀이 mTeam, 평판점수가 mScore인 병사를 고용한다.
     * @param mID 고유번호
     * @param mTeam 소속팀
     * @param mScore 평판점수
     */
    public void hire(int mID, int mTeam, int mScore) {
        Node newNode = getNewNode(mID,null);
        team[mTeam].tail[mScore].next = newNode;
        team[mTeam].tail[mScore] = newNode;
        teamOfSoldier[mID] = mTeam;
    }

    /**
     * 고유번호가 mID인 병사를 해고한다.
     * @param mID 고유번호
     */
    public void fire(int mID) {
        ++version[mID];
    }

    /**
     * 고유번호가 mID인 병사의 평판점수를 mScore로 변경한다.
     * @param mID 고유번호
     * @param mScore 평판 점수
     */
    public void updateSoldier(int mID, int mScore) {
        int mTeam = teamOfSoldier[mID];
        Node newNode = getNewNode(mID,null);
        team[mTeam].tail[mScore].next = newNode;
        team[mTeam].tail[mScore] = newNode;
    }

    /**
     * 소속팀이 mTeam인 병사들의 평판점수를 모두 변경한다.
     * @param mTeam
     * @param mChangeScore
     */
    public void updateTeam(int mTeam, int mChangeScore) {
        if(mChangeScore>=0){
            for(int i=MAX_SCORE; i>=1; i--){
                int changeScore = i+mChangeScore;
                if(changeScore>5) changeScore=5;
                if(changeScore<1) changeScore=1;
                if(changeScore==i) continue;
                if(team[mTeam].head[i].next==null) continue;

                team[mTeam].tail[changeScore].next = team[mTeam].head[i].next;
                team[mTeam].tail[changeScore] = team[mTeam].tail[i];
                team[mTeam].head[i].next = null;
                team[mTeam].tail[i] = team[mTeam].head[i];
            }
        }else{
            for(int i=1; i<=MAX_SCORE; i++){
                int changeScore = i+mChangeScore;
                if(changeScore>5) changeScore=5;
                if(changeScore<1) changeScore=1;
                if(changeScore==i) continue;
                if(team[mTeam].head[i].next==null) continue;

                team[mTeam].tail[changeScore].next = team[mTeam].head[i].next;
                team[mTeam].tail[changeScore] = team[mTeam].tail[i];
                team[mTeam].head[i].next = null;
                team[mTeam].tail[i] = team[mTeam].head[i];
            }
        }
    }

    /**
     * 소속팀이 mTeam인 병사들 중 평판 점수가 가장 높은 병사의 고유번호를 반환한다.
     * @param mTeam
     * @return
     */
    public int bestSoldier(int mTeam) {
        int ret = -1;
        int best = -1;
        for(int i=MAX_SCORE; i>=1; i--){
            if(ret!=-1) break;

            Node n = team[mTeam].head[i].next;
            while(n!=null){
                if(n.version==version[n.mID]){
                    if(i>best) best = i;
                    if(i==best) ret = Math.max(ret, n.mID);
                }
                n = n.next;
            }
        }
        return ret;
    }
}