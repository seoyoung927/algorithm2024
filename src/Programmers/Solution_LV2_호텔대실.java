package Programmers;

import java.util.*;

class Solution_LV2_호텔대실 {
    int N;
    int[][] book;

    public int getTime(String time){
        String[] timeArr = time.split(":");
        return Integer.parseInt(timeArr[0])*60+Integer.parseInt(timeArr[1]);
    }

    public int solution(String[][] book_time) {
        // 1. 입력 및 초기화
        N = book_time.length;
        book = new int[N][2];

        for(int i=0; i<N; i++){
            book[i][0] = getTime(book_time[i][0]);
            book[i][1] = getTime(book_time[i][1]);
        }

        Arrays.sort(book, (t1,t2) -> Integer.compare(t1[0], t2[0]));

        // 2. answer 계산
        List<Integer> room = new ArrayList<>();
        room.add(book[0][1]+10);
        for(int i=1; i<N; i++){
            boolean flag = false; // 방에 들어갈 수 있냐?
            for(int j=0, SIZE=room.size(); j<SIZE; j++){
                if(book[i][0]>=room.get(j)){
                    flag = true;
                    room.set(j, book[i][1]+10);
                    break;
                }
            }
            if(!flag) room.add(book[i][1]+10);
        }

        return room.size();
    }
}