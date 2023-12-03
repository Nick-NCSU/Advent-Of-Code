const fs = require('fs')

const input = fs.readFileSync(`${__dirname}/data.txt`, 'utf8').split('\n')

const symbols = [];
const numbers = [];
let id = 0;
for(const [y, line] of input.entries()) {
  let num = [];
  for(const [x, char] of line.split('').entries()) {
    if(!isNaN(+char)) {
      num.push(char)
    } else if (num.length) {
        for(let i = x - num.length; i < x; i++) {
            numbers.push({
                x: i,
                y,
                num: num.join(''),
                id
            })
        }
        id++;
      num = [];
    }
    if(isNaN(+char) && char !== '.') {
      symbols.push({
        x,
        y,
        char,
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
for(const symbol of symbols) {
    if(symbol.char === '*') {
        const nums = {}
        for(let i = symbol.y - 1; i <= symbol.y + 1; i++) {
            for(let j = symbol.x - 1; j <= symbol.x + 1; j++) {
                const number = numbers.find(s => s.x === j && s.y === i);
                if(number && !nums[number.id]) {
                    nums[number.id] = number.num;
                }
            }
        }
        if(Object.keys(nums).length === 2) {
            total += +Object.values(nums)[0] * +Object.values(nums)[1];
        }
    }
}
console.log(total);