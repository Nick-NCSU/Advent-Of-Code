const fs = require('fs')

const input = fs.readFileSync('data.txt', { encoding: 'utf-8' }).split('\r\n').map(line => line.split(' '));
const crt = [];
let x = 1;
let cycle = 0;
for(const [op, num] of input) {
  crt.push(isLit(cycle++, x) ? '#' : ' ');
  if(op === 'addx') {
    crt.push(isLit(cycle++, x) ? '#' : ' ');
    x += +num;
  }
}
for(let i = 0; i < 8; i++) {
  console.log(crt.slice(i * 40, (i * 40) + 40).join(''))
}

function isLit(cycle, x) {
  const pixel = cycle % 40;
  return x - 1 === pixel || x === pixel || x + 1 === pixel;
}