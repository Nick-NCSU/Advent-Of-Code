const fs = require('fs')

const input = fs.readFileSync('data.txt', { encoding: 'utf-8' }).split('\r\n').map(line => line.split(''));

let max = 0;
for(let i = 0; i < input.length; i++) {
  for(let j = 0; j < input[0].length; j++) {
    let height = input[i][j];
    let score = 1;
    let x1 = i + 1;
    while(x1 < input.length && input[x1++][j] < height) {}
    let x2 = i - 1;
    while(x2 > -1 && input[x2--][j] < height) {}
    let y1 = j + 1;
    while(y1 < input[0].length && input[i][y1++] < height) {}
    let y2 = j - 1;
    while(y2 > -1 && input[i][y2--] < height) {}
    score *= x1 - i - 1;
    score *= i - x2 - 1;
    score *= y1 - j - 1;
    score *= j - y2 - 1;
    max = Math.max(max, score);
  }
}
console.log(max)