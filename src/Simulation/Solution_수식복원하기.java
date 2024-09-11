import java.util.*;

class Solution_수식복원하기 {
    int N;
    List<String>[] num;
    char[] exp;
    List<Integer> candidate;
    List<Integer> question;

    public String[] solution(String[] expressions) {
        // 1. 입력 및 초기화
        N = expressions.length;
        num = new ArrayList[N];
        exp = new char[N];
        candidate = new ArrayList<>();
        question = new ArrayList<>();
        for (int i = 0; i < N; i++) num[i] = new ArrayList<>();

        int minVal = 1; // 최소 진법은 2
        for (int i = 0; i < N; i++) {
            String expression = expressions[i];
            int SIZE = expression.length();

            String val = "";
            for (int idx = 0; idx < SIZE; idx++) {
                char currentChar = expression.charAt(idx);
                if (currentChar == ' ') {
                    if (val.length() > 0) {
                        num[i].add(val);
                        val = "";
                    }
                } else if (currentChar == '=') {
                    continue;
                } else if (currentChar == '+' || currentChar == '-') {
                    exp[i] = currentChar;
                } else {
                    val += currentChar;
                    if (Character.isDigit(currentChar) && currentChar != 'X') {
                        minVal = Math.max(minVal, Character.getNumericValue(currentChar)); // 최대 자릿수에 맞는 최소 진법 계산
                    }
                }
            }
            num[i].add(val);
            if (val.equals("X")) question.add(i); // 결괏값이 X인 경우 질문 리스트에 추가
        }

        // 2. 가능한 진법 후보군 추가
        for (int i = minVal + 1; i <= 9; i++) candidate.add(i); // 진법 후보를 9진법까지 설정
        int CSIZE = candidate.size();
        boolean[] isAnswer = new boolean[CSIZE];
        Arrays.fill(isAnswer, true); // 초기값을 true로 설정하여 모든 후보가 가능하다고 가정

        // 3. 이미 주어진 결과값을 이용해 후보 진법에서 수식 검증
        for (int i = 0; i < N; i++) {
            if (num[i].get(2).equals("X")) continue; // 결괏값이 X인 수식은 검증 생략

            for (int j = 0; j < CSIZE; j++) {
                if (!isAnswer[j]) continue; // 이미 불가능한 진법은 건너뜀
                int n = candidate.get(j); // 현재 진법

                int A = Integer.parseInt(num[i].get(0), n); // A 값을 해당 진법으로 변환
                int B = Integer.parseInt(num[i].get(1), n); // B 값을 해당 진법으로 변환
                int C = Integer.parseInt(num[i].get(2), n); // C 값을 해당 진법으로 변환

                // 연산자가 + 또는 -에 따라 계산
                if (exp[i] == '+') {
                    if (A + B != C) {
                        isAnswer[j] = false; // 조건이 맞지 않으면 해당 진법 후보에서 제외
                    }
                } else {
                    if (A - B != C) {
                        isAnswer[j] = false; // 조건이 맞지 않으면 해당 진법 후보에서 제외
                    }
                }
            }
        }

        // 4. 질문 부분에 대한 답 계산
        List<Integer> filtered = new ArrayList<>();
        for(int i=0; i<CSIZE; i++){
            if(isAnswer[i]) filtered.add(candidate.get(i));
        }

        int FSIZE = filtered.size();
        int QSIZE = question.size();
        String[] answer = new String[QSIZE];

        for (int idx = 0; idx < QSIZE; idx++) {
            int i = question.get(idx); // 질문의 인덱스
            boolean flag = true; // 모든 진법에서 일관된 답을 찾았는지 여부
            int n = filtered.get(0); // 첫 번째 후보 진법

            // A와 B를 해당 진법으로 변환하여 계산
            int A = Integer.parseInt(num[i].get(0), n);
            int B = Integer.parseInt(num[i].get(1), n);
            int val = (exp[i] == '+') ? A+B : A-B; // 첫 진법으로 결과 계산         
            String sVal = Integer.toString(val, n);

            // 다른 후보 진법과 비교
            for (int j = 1; j < FSIZE; j++) {
                n = filtered.get(j); // 현재 진법
                A = Integer.parseInt(num[i].get(0), n);
                B = Integer.parseInt(num[i].get(1), n);
                int tmp = (exp[i] == '+') ? A+B : A-B;
                String sTmp = Integer.toString(tmp, n);
                if (!sTmp.equals(sVal)) {
                    flag = false; // 일치하지 않으면 답이 불확실함
                    break;
                }
            }


            // 최종 답안 작성
            if (flag) {
                answer[idx] = num[i].get(0) + " " + exp[i] + " " + num[i].get(1) + " = " + Integer.toString(val, filtered.get(0)); // 일치하는 답
            } else {
                answer[idx] = num[i].get(0) + " " + exp[i] + " " + num[i].get(1) + " = ?"; // 불확실한 답
            }
        }

        return answer; // 답 반환
    }
}