// 순열 8!
let K;
let N;
let perms = [];
let visited = [];
let answer = 0;
let d;

function permutation(depth){
    if(depth==N){
        let resK = K;
        let i=0;
        for(i; i<N; i++){
            if(resK<d[perms[i]][0]) break;
            resK-=d[perms[i]][1];
        }
        answer = Math.max(answer, i);
        return;
    }
    
    for(let i=0; i<N; i++){
        if(visited[i]===1) continue;
        perms[depth]=i;
        visited[i]=1;
        permutation(depth+1);
        visited[i]=0;
    }
}

function solution(k, dungeons) {
    K = k;
    N = dungeons.length;
    perms = Array(N).fill(0);
    visited = Array(N).fill(0);
    d = dungeons;
    
    permutation(0);
    
    return answer;
}
