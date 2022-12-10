const fs = require('fs')

const input = fs.readFileSync('data.txt', { encoding: 'utf-8' }).split('\r\n').map(line => line.split(' '));
let x = 1;
const cycle = [0];
for(const [op, num] of input) {
  cycle.push(x * cycle.length);
  if(op === 'addx') {
    cycle.push(x * cycle.length);
    x += +num;
  }
}
console.log(cycle[20] + cycle[60] + cycle[100] + cycle[140] + cycle[180] + cycle[220])