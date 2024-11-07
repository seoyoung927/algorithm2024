function solution(n, times) {
    // 1. times를 오름차순으로 정렬
    times.sort((a,b) => a-b);
    
    // 2. 이분 탐색
    let left = 0;
    let right = times[times.length-1]*n;
    
    while(left<right){
        const mid = Math.floor((left+right)/2);
        let count = 0;
        
        for(let time of times){
            count += Math.floor(mid/time);
            if(count>=n) break;
        }
        
        if(count>=n){
            right = mid;
        }else{
            left = mid+1;
        }
    }
    
    return left;
}
