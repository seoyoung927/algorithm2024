import java.util.*;
import java.io.*;

public class Main_14658_G3_하늘에서별똥별이빗밧친다 {
    // N: 가로, M: 세로, L: 트램펄린의 한 변의 길이, K: 별똥별의 개수
    static int N,M,L,K;
    static Point[] star;

    static class Point{
        int x;
        int y;

        public Point(int x, int y){
            this.x=x;
            this.y=y;
        }
    }

    public static int count(int x, int y){
        int res = 0;
        for(int i=0; i<K; i++){
            if(x<=star[i].x && star[i].x<=x+L && y<=star[i].y && star[i].y<=y+L) res++;
        }
        return res;
    }

    public static void main(String[] args) throws IOException{
        // 1. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        star = new Point[K];
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int starX = Integer.parseInt(st.nextToken());
            int starY = Integer.parseInt(st.nextToken());
            star[i] = new Point(starX, starY);
        }

        // 2. 최적의 트램펄린 위치 구하기
        int res = Integer.MIN_VALUE;
        for(int i=0; i<K; i++){
            for(int j=0; j<K; j++){
                res = Math.max(res, count(star[i].x, star[j].y));
            }
        }

        // 3. 출력
        System.out.println(K-res);
    }
}
