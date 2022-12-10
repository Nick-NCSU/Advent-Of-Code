const fs = require('fs')

const input = fs.readFileSync('data.txt', { encoding: 'utf-8' }).split('\r\n').map(line => line.split(''));
const visible = new Set();

for(let i = 0; i < input.length; i++) {
  let max = -1;
  for(let j = 0; j < input[i].length; j++) {
    if(input[i][j] > max) {
      max = input[i][j];
      visible.add(`${i},${j}`)
    }
  }
}

for(let i = 0; i < input.length; i++) {
  let max = -1;
  for(let j = input[i].length - 1; j > -1; j--) {
    if(input[i][j] > max) {
      max = input[i][j];
      visible.add(`${i},${j}`)
    }
  }
}

for(let i = 0; i < input[0].length; i++) {
  let max = -1;
  for(let j = 0; j < input.length; j++) {
    if(input[j][i] > max) {
      max = input[j][i];
      visible.add(`${j},${i}`)
    }
  }
}

for(let i = 0; i < input[0].length; i++) {
  let max = -1;
  for(let j = input.length - 1; j > -1; j--) {
    if(input[j][i] > max) {
      max = input[j][i];
      visible.add(`${j},${i}`)
    }
  }
}
// for(let i = 0; i < input.length; i++) {
//   for(let j = 0; j < input[0].length; j++) {
//     process.stdout.write(visible.has(`${i},${j}`) ? '0' : '.');
//   }
//   process.stdout.write('\n')
// }
console.log(visible.size)