let inputs = require("fs")
  .readFileSync("/dev/stdin")
  .toString()
  .trim()
  .split("\n");

//1. 초기화
let N = parseInt(inputs[0]);
let cars = new Map();
let T = -1;
for (let i = 0; i < N; i++) {
  let car = inputs[i + 1].split(" ");
  if (i == 0) T = parseInt(car[0]);
  if (!cars.has(parseInt(car[0]))) cars.set(parseInt(car[0]), []);
  cars.get(parseInt(car[0])).push([i, car[1]]);
}

//2.
let result = new Array(N).fill(-1);
let R = [[], [], [], []];
let cnt = 0;
while (cnt < N) {
  //교착상태에 빠졌다면
  if (
    R[0].length !== 0 &&
    R[1].length !== 0 &&
    R[2].length !== 0 &&
    R[3].length !== 0
  )
    break;

  //교착상태에 빠지지 않았다면
  if (cars.has(T)) {
    for (let i = 0; i < cars.get(T).length; i++) {
      if (cars.get(T)[i][1] === "A") R[0].push(cars.get(T)[i]);
      if (cars.get(T)[i][1] === "B") R[1].push(cars.get(T)[i]);
      if (cars.get(T)[i][1] === "C") R[2].push(cars.get(T)[i]);
      if (cars.get(T)[i][1] === "D") R[3].push(cars.get(T)[i]);
    }
  }

  let flags = [0, 0, 0, 0];
  for (let i = 0; i < 4; i++) {
    if (i == 0) j = 3;
    else j = i - 1;

    if (R[i].length !== 0 && R[j].length == 0) {
      let cur = R[i][0];
      result[cur[0]] = T;
      flags[i] = 1;
    }
  }

  for (let i = 0; i < 4; i++) {
    if (flags[i] == 1) {
      R[i].shift();
      cnt += 1;
    }
  }

  T += 1;
}

//3. 출력
for (let i = 0; i < N; i++) {
  console.log(result[i]);
}
