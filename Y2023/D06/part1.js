const fs = require('fs')

const input = fs.readFileSync(`${__dirname}/data.txt`, 'utf8').split('\n')

const times = input[0].replaceAll(/[ ]+/g, ' ').split(' ').slice(1).map(Number);
const distances = input[1].replaceAll(/[ ]+/g, ' ').split(' ').slice(1).map(Number);

let ans = 1;
for(let i = 0; i < times.length; i++) {
  let total = 0;
  for(let j = 0; j < times[i]; j++) {
    const speed = j;
    const score = (times[i] - speed) * speed;
    if(score > distances[i]) {
      total++;
    }
  }
  ans *= total;
}
console.log(ans);