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

function gcd(a, b) { 
  for (let temp = b; b !== 0;) { 
      b = a % b; 
      a = temp; 
      temp = b; 
  } 
  return a; 
} 

function lcm(a, b) { 
  const gcdValue = gcd(a, b); 
  return (a * b) / gcdValue; 
} 

let current = Object.keys(map).filter(k => k.endsWith('A'));
let steps = 1;
for(node of current) {
    let total = 0;
    while(!node.endsWith('Z')) {
        next = directions.shift();
        directions.push(next);
        node = map[node][next];
        total++;
    }
    steps = lcm(steps, total);
}
console.log(steps);