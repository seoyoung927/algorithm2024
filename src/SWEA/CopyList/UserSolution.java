package CopyList;

import java.util.*;

class UserSolution {
    static final int MAX_LENGTH = 10;
    static final int MAX_LIST_LENGTH = 200000;
    static final int MAX_ADDRESS = 6000;
    static final int MAX_CHANGE = 110000;

    static int[][] initArr;
    static int arrIdx;

    static Map<String, Integer> address;
    static int addIdx;

    static Command[] commands;
    static int commandIdx;
    static int[] prevCmd;
    static int[] lastCmd;

    static class Command{
        int first;
        int second;

        public Command() {}

        public Command(int first, int second){
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString(){
            return "f="+first+",s="+second;
        }
    }

    public void init() {
        initArr = new int[MAX_LENGTH+1][MAX_LIST_LENGTH+1];
        arrIdx = 0;

        address = new HashMap<>();
        addIdx = 0;

        commands = new Command[MAX_CHANGE+1];
        commandIdx = 0;
        prevCmd = new int[MAX_CHANGE+1];
        lastCmd = new int[MAX_ADDRESS+1];
    }

    String getName(char[] name) {  // char[] -> String 변환 함수
        String x = "";
        for (int i=0;name[i]!=0;i++){
            x += name[i];
        }
        return x;
    }

    public void makeList(char mName[], int mLength, int mListValue[]) {
        String name = getName(mName);
        for(int i=0; i<mLength; i++) initArr[arrIdx][i] = mListValue[i];
        arrIdx+=1;

        address.put(name, addIdx);
        addIdx++;

        commands[commandIdx] = new Command(-1, arrIdx-1);
        prevCmd[commandIdx] = -1;
        lastCmd[addIdx-1] = commandIdx;
        commandIdx++;
    }

    public void copyList(char mDest[], char mSrc[], boolean mCopy) {
        String name = getName(mDest);
        String parent = getName(mSrc);
        int parentIdx = address.get(parent);

        if(mCopy){
            //깊은 복사
            address.put(name, addIdx);
            addIdx++;

            commands[commandIdx] = new Command(-1, parentIdx);
            prevCmd[commandIdx] = lastCmd[parentIdx];
            lastCmd[addIdx-1] = commandIdx;
            commandIdx++;
        }else{
            //얕은 복사
            address.put(name, parentIdx);
        }
    }

    public void updateElement(char mName[], int mIndex, int mValue) {
        String name = getName(mName);
        int idx = address.get(name);
        commands[commandIdx] = new Command(mIndex, mValue);
        prevCmd[commandIdx] = lastCmd[idx];
        lastCmd[idx] = commandIdx;
        commandIdx++;
    }

    public int element(char mName[], int mIndex) {
        String name = getName(mName);
        int idx = address.get(name);
        int c = lastCmd[idx];
        while(true){
            if(prevCmd[c]==-1){ //이전 변화가 없다면
                return initArr[commands[c].second][mIndex];
            }
            if(commands[c].first == mIndex){
                return commands[c].second;
            }
            c = prevCmd[c];
        }
    }
}