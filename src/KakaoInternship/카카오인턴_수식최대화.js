function solution(expression) {
  let answer = 0;

  const candidates = [
    ["+", "-", "*"],
    ["+", "*", "-"],
    ["-", "+", "*"],
    ["-", "*", "+"],
    ["*", "+", "-"],
    ["*", "-", "+"],
  ];

  const operators = [];
  const operands = [];
  let tmp = "";
  for (const s of expression) {
    if (s === "+" || s === "-" || s === "*") {
      operands.push(Number(tmp));
      tmp = "";
      operators.push(s);
    } else {
      tmp += s;
    }
  }
  if (tmp.length !== 0) operands.push(Number(tmp));

  // console.log("operators: ", operators);
  // console.log("operands: ", operands);

  for (const ops of candidates) {
    const size = operators.length;
    const visited = new Array(size + 1).fill(0);
    const copy = operands.slice();
    //console.log(ops);

    for (const op of ops) {
      for (let i = 0; i < size; i++) {
        const operator = operators[i];
        if (operator === op) {
          let next = -1;
          for (let n = i + 1; n < size + 1; n++) {
            if (visited[n] == 0) {
              next = n;
              break;
            }
          }
          if (next != -1) {
            visited[i] = 1;
            let val = copy[i];
            if (operator === "+") val += copy[next];
            if (operator === "-") val -= copy[next];
            if (operator === "*") val *= copy[next];
            copy[next] = val;
            copy[i] = 0;
          }
        }
      }
    }
    //순회
    for (let n of copy) {
      answer = Math.max(answer, Math.abs(n));
    }
  }

  return answer;
}

console.log(solution("100-200*300-500+20"));
console.log(solution("50*6-3*2"));
console.log(solution("50-6-4-2"));
