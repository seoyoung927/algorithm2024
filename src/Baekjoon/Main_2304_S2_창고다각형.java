import java.util.*;
import java.io.*;

// 112ms
public class Main_2304_S2_창고다각형 {
    static int N;
    static Tower[] tower;

    static class Tower{
        int w, h;

        public Tower(int w, int h){
            this.w=w;
            this.h=h;
        }
    }

    public static void main(String[] args) throws IOException {
        //1. 입력 및 초기화
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tower = new Tower[N];
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            tower[i] = new Tower(w,h);
        }
        Arrays.sort(tower, new Comparator<Tower>() {
            @Override
            public int compare(Tower o1, Tower o2) {
                return Integer.compare(o1.w, o2.w);
            }
        });

        //2.
        int highest = 0;
        for(int i=0; i<N; i++){
            if(tower[i].h>=tower[highest].h) highest = i;
        }
        // left ~ highest
        int answer = tower[highest].h;
        int left = 0;
        for(int i=1; i<=highest; i++) {
            if (tower[i].h >= tower[left].h) {
                int n = Math.min(tower[i].h, tower[left].h);
                answer += n * (tower[i].w - tower[left].w);
                if (tower[i].h >= tower[left].h) left = i;
            }
        }
        // highest ~ right
        int right = N-1;
        for(int i=N-2; i>=highest; i--){
            if (tower[i].h >= tower[right].h) {
                int n = Math.min(tower[i].h, tower[right].h);
                answer += n * (tower[right].w - tower[i].w);
                if (tower[i].h >= tower[right].h) right = i;
            }
        }

        //3. 출력
        System.out.println(answer);
    }
}
