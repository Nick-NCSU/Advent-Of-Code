const fs = require('fs')

const input = fs.readFileSync(`${__dirname}/data.txt`, 'utf8').split('\n')

const symbols = [];
const numbers = [];
for(const [y, line] of input.entries()) {
  let num = [];
  for(const [x, char] of line.split('').entries()) {
    if(!isNaN(+char)) {
      num.push(char)
    } else if (num.length) {
      numbers.push({
        x: x - num.length,
        y,
        num: num.join('')
      })
      num = [];
    }
    if(isNaN(+char) && char !== '.') {
      symbols.push({
        x,
        y,
      })
    }
  }
  if(num.length) {
    numbers.push({
      x: input[y].length - num.length,
      y,
      num: num.join('')
    })
  }
}

let total = 0;
next:
for(const number of numbers) {
  for(let i = number.y - 1; i <= number.y + 1; i++) {
    for(let j = number.x - 1; j <= number.x + number.num.length; j++) {
      const symbol = symbols.find(s => s.x === j && s.y === i);
      if(symbol) {
        total += +number.num;
        continue next;
      }
    }
  }
}
console.log(total);