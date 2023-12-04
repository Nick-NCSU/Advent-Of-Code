const fs = require('fs')

const input = fs.readFileSync(`${__dirname}/data.txt`, 'utf8').split('\n')

let total = 0;
for(const card of input) {
  const [_, winNumStr, numStr] = card.match(/Card\W+\d+: ([\d ]+) \| ([\d ]+)/);
  const winNums = winNumStr.replaceAll('  ', ' ').trim().split(' ').map(Number);
  const nums = numStr.replaceAll('  ', ' ').trim().split(' ').map(Number);
  let cardTotal = 0;
  for(const num of nums) {
    if(winNums.includes(num)) {
      if(cardTotal === 0) {
        cardTotal = 1;
      } else {
        cardTotal *= 2;
      }
    }
  }
  total += cardTotal;
}
console.log(total)