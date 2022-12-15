const fs = require('fs')

const input = fs.readFileSync('data.txt', { encoding: 'utf-8' }).split('\n').map(line => line.split(' -> ').map(coord => { 
  const [x, y] = coord.split(',').map(n => +n);
  return {
    x,
    y
  };
}));
const filled = new Set();
let maxY = 0;
for(const line of input) {
  for(let i = 0; i < line.length - 1; i++) {
    const a = line[i];
    const b = line[i + 1];
    maxY = Math.max(maxY, a.y);
    if(line[i].x !== line[i + 1].x) {
      const length = Math.abs(a.x - b.x) + 1;
      const dir = a.x > b.x ? -1 : 1;
      const start = a.x - dir;
      Array.from({ length: Math.abs(length) }, (_v, k) => start + (k * dir) + dir).forEach(v => {
        filled.add(`${v},${a.y}`);
      });
    } else {
      const length = Math.abs(a.y - b.y) + 1;
      const dir = a.y > b.y ? -1 : 1;
      const start = a.y - dir;
      Array.from({ length: Math.abs(length) }, (_v, k) => start + (k * dir) + dir).forEach(v => {
        filled.add(`${a.x},${v}`);
      });
    }
  }
}
Array.from({ length: 1_000 }, (_v, k) => k + 1).forEach(v => {
  filled.add(`${v},${maxY + 2}`);
});
maxY += 2;
let n = 0;
while(dropSand()) {
  n++;
}
console.log(n);

function dropSand() {
  let x = 500, y = 0;
  if(filled.has(`${x},${y}`)) {
    return false;
  }
  while(true) {
    if(!filled.has(`${x},${y + 1}`)) {
      if(y + 1 === maxY) {
        return false;
      }
      y = lowerSand(x, y);
    } else if(!filled.has(`${x-1},${y+1}`)) {
      x--;
      y++;
    } else if(!filled.has(`${x+1},${y+1}`)) {
      x++;
      y++;
    } else {
      filled.add(`${x},${y}`);
      return true;
    }
  }

}

function lowerSand(x, y) {
  while(!filled.has(`${x},${y++}`) && y <= maxY);
  return y - 2;
}