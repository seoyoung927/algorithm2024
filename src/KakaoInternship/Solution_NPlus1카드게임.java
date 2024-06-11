package KakaoInternship;

import java.util.*;

class Solution_NPlus1카드게임 {
    public static int n; //1~n 사이의 수가 적힌 카드
    public static Set<Integer> myCards; //내가 가지고 있는 카드
    public static Set<Integer> tmpCards;
    public static int myCoin;
    public static int pair;
    public static int round;

    public void getCard(int card){
        if(myCoin>0 && myCards.contains(n+1-card)){
            myCoin--;
            pair++;
            return;
        }
        tmpCards.add(card);
    }

    public int solution(int coin, int[] cards) {
        n = cards.length;
        myCards = new HashSet<Integer>();
        tmpCards = new HashSet<Integer>();
        myCoin = coin;
        round = 1;

        for(int i=0; i<n/3; i++) myCards.add(cards[i]);
        for(int card : myCards){
            if(myCards.contains(n+1-card)){
                pair++;
            }
        }
        pair/=2;

        for(int i=n/3; i<n; i+=2){
            getCard(cards[i]);
            getCard(cards[i+1]);

            if(pair<=0 && myCoin>=2){
                for(int card : tmpCards){
                    if(tmpCards.contains(n+1-card)){
                        pair++;
                        myCoin-=2;
                        tmpCards.remove(card);
                        break;
                    }
                }
            }

            if(pair<=0) break;

            round++;
            pair--;
        }

        return round;
    }
}