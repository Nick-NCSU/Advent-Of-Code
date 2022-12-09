const fs = require('fs')

const input = fs.readFileSync('data.txt', { encoding: 'utf-8' }).split('\r\n').map(line => line.split(' '));
const knots = [];
for(let i = 0; i < 10; i++) {
  knots[i] = {
    x: 0,
    y: 0,
  }
}
const visited = new Set();
for(const [dir, dist] of input) {
  for(let _i = 0; _i < +dist; _i++) {
    if(dir === 'R') {
      knots[0].x++;
    } else if(dir === 'L') {
      knots[0].x--;
    } else if(dir === 'U') {
      knots[0].y--;
    } else if(dir === 'D') {
      knots[0].y++;
    }
    for(let j = 1; j < 10; j++) {
      if(knots[j-1].x - knots[j].x > 1) {
        knots[j].x++;
        if(knots[j-1].y != knots[j].y) {
          knots[j].y += knots[j-1].y - knots[j].y > 0 ? 1 : -1;
        }
      } else if(knots[j].x - knots[j-1].x > 1) {
        knots[j].x--;
        if(knots[j-1].y != knots[j].y) {
          knots[j].y += knots[j-1].y - knots[j].y > 0 ? 1 : -1;
        }
      }
      if(knots[j-1].y - knots[j].y > 1) {
        knots[j].y++;
        if(knots[j-1].x != knots[j].x) {
          knots[j].x += knots[j-1].x - knots[j].x > 0 ? 1 : -1;
        }
      } else if(knots[j].y - knots[j-1].y > 1) {
        knots[j].y--;
        if(knots[j-1].x != knots[j].x) {
          knots[j].x += knots[j-1].x - knots[j].x > 0 ? 1 : -1;
        }
      }
    }

    visited.add(`${knots[9].x},${knots[9].y}`)
  }
}
console.log(visited.size)