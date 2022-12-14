const fs = require('fs')

const input = fs.readFileSync('data.txt', { encoding: 'utf-8' }).split('\n\n').map(pair => pair.split('\n'));

let sum = 0;
for(const [idx, pair] of input.entries()) {
  const [a, b] = [JSON.parse(pair[0]), JSON.parse(pair[1])];
  if(compare(a, b)) {
    sum += idx + 1;  
  }
}
console.log(sum)

function compare(a, b) {
  // console.log(`Compare ${JSON.stringify(a)} vs ${JSON.stringify(b)}`)
  if(Array.isArray(a) || Array.isArray(b)) {
    if(!Array.isArray(a)) {
      // console.log(`Mixed types; convert left to [${a}] and retry comparison`)
      a = [a];
    }
    if(!Array.isArray(b)) {
      // console.log(`Mixed types; convert right to [${b}] and retry comparison`)
      b = [b];
    }
    for(let idx = 0; idx < a.length; idx++) {
      if(idx >= b.length) {
        return false;
      }
      const res = compare(a[idx], b[idx]);
      if(res !== undefined) {
        return res;
      }
    }
    if(a.length !== b.length) {
      return true;
    }
  } else if(a === b) {
    return;
  } else {
    // console.log(a < b ? `Left side is smaller, so inputs are in the right order` : `Right side is smaller, so inputs are not in the right order`)
    return a < b;
  }
}