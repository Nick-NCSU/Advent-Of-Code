const fs = require('fs')

const input = fs.readFileSync('data.txt', { encoding: 'utf-8' }).split('\r\n').map(line => line.split(' '));
let x = 0, y = 0;
let tailx = 0, taily = 0;
const visited = new Set();
for(const [dir, dist] of input) {
  for(let i = 0; i < +dist; i++) {
    const curx = x;
    const cury = y;
    if(dir === 'R') {
      x++;
    } else if(dir === 'L') {
      x--;
    } else if(dir === 'U') {
      y++;
    } else if(dir === 'D') {
      y--;
    }
    if(Math.abs(tailx - x) > 1 || Math.abs(taily - y) > 1) {
      tailx = curx;
      taily = cury;
    }
    visited.add(`${tailx},${taily}`)
  }
}
console.log(visited.size)