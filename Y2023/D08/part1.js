const fs = require('fs')

const input = fs.readFileSync(`${__dirname}/data.txt`, 'utf8').split('\n');

const directions = input[0].split('');

const map = {};
for(const line of input.slice(2)) {
  const [_, node, L, R] = line.match(/(\w+) = \((\w+), (\w+)\)/);
  map[node] = {
    L,
    R
  }
}

let current = 'AAA';
let total = 0;
while(current !== 'ZZZ') {
  next = directions.shift();
  directions.push(next);
  current = map[current][next];
  total++;
}
console.log(total);