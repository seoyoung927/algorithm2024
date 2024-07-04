package SWEA.CopyList2;

import java.util.*;

class UserSolution {
    static final int MAX_CNT = 10;
    static final int MAX_LEN = 200_000;
    static final int MAX_ADDRESS = 6000;
    static final int MAX_CHANGE = 110_000;

    static int[][] initArr;
    static int arrIdx;

    static Map<String, Integer> address;
    static int addCnt;

    static class Change{
        int a;
        int b;
        public Change() {}
        public Change(int a, int b){
            this.a=a;
            this.b=b;
        }
    }
    static Change[] changeLog;
    static int logIdx;
    static int[] prevLog;
    static int[] lastLog;

    String getName(char[] name) {  // char[] -> String 변환 함수
        String x = "";
        for (int i=0;name[i]!=0;i++){
            x += name[i];
        }
        return x;
    }

    public void init() {
        initArr = new int[MAX_CNT+1][MAX_LEN+1];
        arrIdx = 0;

        address = new HashMap<>();
        addCnt = 0;

        changeLog = new Change[MAX_CHANGE+1];
        logIdx = 0;
        prevLog = new int[MAX_CHANGE+1];
        lastLog = new int[MAX_ADDRESS+1];
    }

    /**
     * 함수의 호출횟수는 10이하이다.
     * @param mName
     * @param mLength
     * @param mListValue
     */
    public void makeList(char mName[], int mLength, int mListValue[]) {
        String name = getName(mName);
        for(int i=0; i<mLength; i++) initArr[arrIdx][i] = mListValue[i];
        arrIdx+=1;

        address.put(name, addCnt);
        addCnt+=1;

        changeLog[logIdx] = new Change(-1,arrIdx-1);
        prevLog[logIdx] = -1;
        lastLog[addCnt-1] = logIdx;
        logIdx+=1;
    }

    /**
     * 함수의 호출횟수는 5000이하이다.
     * @param mDest
     * @param mSrc
     * @param mCopy
     */
    public void copyList(char mDest[], char mSrc[], boolean mCopy) {
        String name = getName(mDest);
        String parent = getName(mSrc);
        int parentAddr = address.get(parent);

        if(mCopy){
            address.put(name, addCnt);
            addCnt+=1;

            //깊은 복사
            changeLog[logIdx] = new Change(-1, -1);
            prevLog[logIdx] = lastLog[parentAddr];
            lastLog[addCnt-1] = logIdx;
            logIdx+=1;
        }else{
            address.put(name, parentAddr);
        }
    }

    /**
     * 함수의 호출횟수는 100000이하이다.
     * mName 리스트의 mIndex번째 원소의 값을 mValue로 변경한다.
     * @param mName
     * @param mIndex
     * @param mValue
     */
    public void updateElement(char mName[], int mIndex, int mValue) {
        String name = getName(mName);
        int addr = address.get(name);
        changeLog[logIdx] = new Change(mIndex,mValue);
        prevLog[logIdx] = lastLog[addr];
        lastLog[addr] = logIdx;
        logIdx+=1;
    }

    /**
     * 함수의 호출횟수는 400이하이다.
     * mName 리스트의 mIndex번째 원소를 반환
     * @param mName
     * @param mIndex
     * @return
     */
    public int element(char mName[], int mIndex) {
        int ret = -1;
        String name = getName(mName);
        int addr = address.get(name);
        int c = lastLog[addr];
        while(true){
            if(prevLog[c]==-1){
                ret = initArr[changeLog[c].b][mIndex];
                break;
            }
            if(changeLog[c].a==mIndex){
                ret = changeLog[c].b;
                break;
            }
            c = prevLog[c];
        }
        return ret;
    }
}