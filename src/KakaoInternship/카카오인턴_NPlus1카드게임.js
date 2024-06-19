/**
myCards: 내가 현재 들고 있는 카드
remainCards: 아직 coin으로 사지는 않았지만 후보 카드들
myCoin: 현재 남은 코인 개수
pair: 짝의 개수
**/
function solution(coin, cards) {
    //1. 초기화
    const n = cards.length;
    let myCards = new Set();
    let remainCards = new Set();
    let myCoin = coin;
    let pair = 0;
    let round = 1;
    for(let idx=0; idx<n/3; idx++) myCards.add(cards[idx]);
    for(let card of myCards){
        if(myCards.has(n+1-card)) pair++;
    }
    pair = pair/2;
    
    //2. 게임 진행
    for(let idx=n/3; idx<n; idx=idx+2){
        let card1 = cards[idx];
        let card2 = cards[idx+1];
        
        if(!myCards.has(n+1-card1)){
            remainCards.add(card1);
        }else{
            if(myCoin>0){
                myCoin--;
                pair++;
            }
        }
        if(!myCards.has(n+1-card2)){
            remainCards.add(card2);
        }else{
            if(myCoin>0){
                myCoin--;
                pair++;
            }
        }
        
        if(pair==0 && myCoin>=2){
            for(let card of remainCards){
                if(remainCards.has(n+1-card)){
                    remainCards.delete(card);
                    myCoin-=2;
                    pair++;
                    break;
                }
            }
        }
        
        if(pair>0){
            pair--;
            round++;
        }else break;
    }
    
    return round;
}