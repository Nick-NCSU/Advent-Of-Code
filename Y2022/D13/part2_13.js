const fs = require('fs')

let input = fs.readFileSync('data.txt', { encoding: 'utf-8' }).split('\n\n').reduce((prev, pair) => [...prev, ...pair.split('\n').map(p => JSON.parse(p))], []);
const n1 = [[2]];
const n2 = [[6]];
input = [
  ...input,
  n1,
  n2,
]

input.sort((a, b) => compare(a, b) ? -1 : 1);
console.log((input.indexOf(n1) + 1) * (input.indexOf(n2) + 1))

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