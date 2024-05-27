package KakaoInternship;

public class Solution_문자열압축 {
	static String s = "ababcdcdababcdcd";
	
	//s: 남은 문자열, n: 반복 길이, repeat: 반복 횟수
	public static String compress(String s, int n, int repeat) {
		if(s.length()<n) return s;
		String preString = s.substring(0,n);
		String postString = s.substring(n,s.length());
		
		//불일치
		if(!postString.startsWith(preString)) {
			if(repeat==1) return preString+compress(postString, n, 1);
			return Integer.toString(repeat)+preString+compress(postString, n, 1);
		}
		
		//일치
		return compress(postString, n, repeat+1);
	}
	
	public static void main(String[] args) {
		int result = s.length();
		for(int i=1; i<s.length()/2+1; i++) {
			String str = compress(s, i, 1);
			//System.out.println(str);
			result = Math.min(result, str.length());
		}
		System.out.println(result);
	}
}
