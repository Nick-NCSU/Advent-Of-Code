const fs = require('fs')

const input = fs.readFileSync(`${__dirname}/data.txt`, 'utf8').split('\n')

const cardCounts = {};
for(const card of input) {
  const [_, cardNum, winNumStr, numStr] = card.match(/Card\W+(\d+): ([\d ]+) \| ([\d ]+)/);
  const winNums = winNumStr.replaceAll('  ', ' ').trim().split(' ').map(Number);
  const nums = numStr.replaceAll('  ', ' ').trim().split(' ').map(Number);
  cardCounts[cardNum] ??= 0;
  cardCounts[cardNum]++;
  let t = 0;
  for(const num of nums) {
    if(winNums.includes(num)) {
      t++;
    }
  }
  for(let i = +cardNum + 1; i < +cardNum + 1 + t; i++) {
    cardCounts[i] ??= 0;
    cardCounts[i] += cardCounts[cardNum];
  }
}
console.log(Object.values(cardCounts).reduce((a, b) => a + b, 0));