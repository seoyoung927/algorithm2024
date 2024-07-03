package NumberPartition;

import java.util.*;

public class NumberPartitionTest{
    static List<List<Integer>> partitions;

    //n을 k개의 자연수로 나눔
    public static void paritition(int n, int k, int min, List<Integer> result){
        if(k==1){
            if(n>=min){
                result.add(n);
                partitions.add(new ArrayList<>(result)); //복사본 저장
                result.remove(result.size()-1);
            }
            return;
        }

        for(int i=min; i<=n-k; i++){
            result.add(i);
            paritition(n-i, k-1, i, result); //중복 X
            result.remove(result.size()-1); //백트래킹
        }
    }

    public static void main(String[] args){
        partitions = new ArrayList<>();
        paritition(5,3,1, new ArrayList<>());
        for(List<Integer> p : partitions){
            System.out.println(p);
        }
    }
}
