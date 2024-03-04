const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});
let input = [];

rl.on("line", (line) => {
  input.push(line.toString().trim());
}).on("close", () => {
  //n: 지역의 개수, m: 예은이와 수색범위: m, 길의 개수: r
  const [n, m, r] = input[0].split(" ").map(Number);
  const items = input[1].split(" ").map(Number);

  class Node {
    constructor(vertex, weight) {
      this.vertex = vertex;
      this.weight = weight;
    }
  }

  const adjList = {};
  for (let i = 1; i < n + 1; i++) {
    adjList[i] = [];
  }

  for (let i = 2; i < r + 2; i++) {
    const inputArr = input[i].split(" ").map(Number);
    adjList[inputArr[0]].push(new Node(inputArr[1], inputArr[2]));
    adjList[inputArr[1]].push(new Node(inputArr[0], inputArr[2]));
  }

  let result = -Infinity;
  for (let start = 1; start < n + 1; start++) {
    let minDistance = Array(n + 1).fill(Infinity);
    let visited = Array(n + 1).fill(false);
    minDistance[start] = 0;
    let min = 0;
    let stopOver = 0;

    for (let i = 0; i < n; i++) {
      min = Infinity;
      stopOver = -1;

      for (let j = 1; j < n + 1; j++) {
        if (!visited[j] && min > minDistance[j]) {
          min = minDistance[j];
          stopOver = j;
        }
      }

      if (stopOver == -1) break;
      visited[stopOver] = true;

      for (let j = 0; j < adjList[stopOver].length; j++) {
        let cur = adjList[stopOver][j];
        if (minDistance[cur.vertex] > min + cur.weight) {
          minDistance[cur.vertex] = min + cur.weight;
        }
      }
    }

    let sum = 0;
    for (let i = 1; i < n + 1; i++) {
      if (minDistance[i] <= m) sum += items[i - 1];
    }
    result = Math.max(result, sum);
  }

  console.log(result);
});
