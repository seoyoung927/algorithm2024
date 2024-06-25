package SWEA.LinkedList;

class UserSolution
{
    public class Node{
        int mID;
        int v;
        Node next;

        public Node() {}

        public Node(int mID, int v){
            this.mID = mID;
            this.v = v;
        }

        public Node(int mID, int v, Node next){
            this.mID = mID;
            this.v = v;
            this.next = next;
        }
    }

    public class Team{
        Node[] head = new Node[6];
        Node[] tail = new Node[6];
    }

    Team[] team = new Team[6];

    public int cnt = 0;
    public Node[] node = new Node[200055];
    public int[] version = new int[100055];
    public int[] teamOfMID = new int[100055];

    public Node getNewNode(int mID, Node next){
        Node ret = new Node();
        ret.mID = mID;
        ret.next = next;
        ret.v = ++version[mID];
        return ret;
    }

    public void init() //O(N)
    {
        //Node 초기화
        cnt = 0;
        for(int i=0; i<100055; i++){
            node[i] = new Node();
        }

        //team 초기화
        for(int i=1; i<6; i++){
            team[i] = new Team();
            for(int j=1; j<6; j++){
                team[i].head[j] = team[i].tail[j] = getNewNode(0, null);
            }
        }

        //version, teamOfMID 초기화
        for(int i=0; i<100055; i++){
            version[i] = 0;
            teamOfMID[i] = 0;
        }
    }

    public void hire(int mID, int mTeam, int mScore) //O(1)
    {
        Node newNode = getNewNode(mID, null);
        team[mTeam].tail[mScore].next = newNode;
        team[mTeam].tail[mScore] = newNode;
        teamOfMID[mID] = mTeam;
    }

    public void fire(int mID) //O(1)
    {
        version[mID] = -1;
    }

    public void updateSoldier(int mID, int mScore) //O(1)
    {
        hire(mID, teamOfMID[mID], mScore);
    }

    /*
    소속팀이 mTeam인 병사들의 평판 점수를 모두 변경한다.
    소속팀이 mTeam인 병사가 한 명 이상 고용되어 있음이 보장된다.
    updateTeam() 함수에서의 평판 점수 변경은 아래의 규칙에 따른다.
    ‘변경 전 평판 점수 + mChangeScore’가 5보다 클 경우, 평판 점수를 5로 변경한다.
    ‘변경 전 평판 점수 + mChangeScore’가 1보다 작을 경우, 평판 점수를 1로 변경한다.
    그 외의 경우, 평판 점수를 ‘변경 전 평판 점수 + mChangeScore’로 변경한다.
     */
    public void updateTeam(int mTeam, int mChangeScore) //O(1)
    {
        if(mChangeScore>0){
            for(int score=5; score>0; score--){
                int changeScore = score + mChangeScore;
                if(changeScore>5) changeScore = 5;
                if(changeScore<1) changeScore = 1;
                if(changeScore == score) continue;

                if (team[mTeam].head[score].next == null) continue;
                //바꾼 점수의 뒤에 연결
                team[mTeam].tail[changeScore].next = team[mTeam].head[score].next;
                team[mTeam].tail[changeScore] = team[mTeam].tail[score];
                //앞의 연결을 끊음
                team[mTeam].head[score].next = null;
                team[mTeam].tail[score] = team[mTeam].head[score];
            }
        }
        if(mChangeScore<0){
            for(int score=1; score<=5; score++){
                int changeScore = score + mChangeScore;
                if(changeScore>5) changeScore = 5;
                if(changeScore<1) changeScore = 1;
                if(changeScore == score) continue;

                if (team[mTeam].head[score].next == null) continue;
                //바꾼 점수의 뒤에 연결
                team[mTeam].tail[changeScore].next = team[mTeam].head[score].next;
                team[mTeam].tail[changeScore] = team[mTeam].tail[score];
                //앞의 연결을 끊음
                team[mTeam].head[score].next = null;
                team[mTeam].tail[score] = team[mTeam].head[score];
            }
        }
    }

    /**
     * 소속팀이 mTeam인 병사들 중 평판 점수가 가장 높은 병사의 고유번호를 반환한다.
     * 평판 점수가 가장 높은 병사가 여러 명일 경우, 고유번호가 가장 큰 병사의 고유번호를 반환한다.
     * 소속팀이 mTeam인 병사가 한 명 이상 고용되어 있음이 보장된다.
     * @param mTeam
     * @return 평판 점수가 가장 높은 병사의 고유번호
     */
    public int bestSoldier(int mTeam) //O(N)
    {
        for(int i=5; i>0; i--){
            Node node = team[mTeam].head[i].next;
            if(node==null) continue;

            int ans = 0;
            while(node != null){
                if(node.v==version[node.mID]) ans = Math.max(ans, node.mID);
                node = node.next;
            }
            if(ans!=0) return ans;
        }
        return 0;
    }
}
