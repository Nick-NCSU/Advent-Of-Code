const fs = require('fs')

const input = fs.readFileSync('data.txt', { encoding: 'utf-8' });
for(let i = 0; i < input.length - 3; i++) {
  const s = input.substring(i, i + 4);
  if(isUnique(s)) {
    console.log(i + 4)
    break
  }
}

function isUnique(s) {
  return new Set(s.split('')).size === s.length
}