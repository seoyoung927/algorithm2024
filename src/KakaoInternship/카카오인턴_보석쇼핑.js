function solution(gems) {
  var answer = [1, gems.length];
  const gemsMap = new Map();
  const cnt = new Set(gems).size;
  gems.forEach((gem, i) => {
    gemsMap.delete(gem);
    gemsMap.set(gem, i);
    if (gemsMap.size === cnt) {
      tmp = [gemsMap.values().next().value + 1, i + 1];
      let answerLen = answer[1] - answer[0];
      let tmpLen = tmp[1] - tmp[0];
      if (answerLen > tmpLen) answer = tmp;
    }
  });
  return answer;
}

console.log(solution(["DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA"]));
console.log(solution(["AA", "AB", "AC", "AA", "AC"]));
console.log(solution(["XYZ", "XYZ", "XYZ"]));
console.log(solution(["ZZZ", "YYY", "NNNN", "YYY", "BBB"]));
