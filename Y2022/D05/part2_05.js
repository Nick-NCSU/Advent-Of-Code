const fs = require('fs')

const stacks = [
  ['Z', 'J', 'N', 'W', 'P', 'S'],
  ['G', 'S', 'T'],
  ['V', 'Q', 'R', 'L', 'H'],
  ['V', 'S', 'T', 'D'],
  ['Q', 'Z', 'T', 'D', 'B', 'M', 'J'],
  ['M', 'W', 'T', 'J', 'D', 'C', 'Z', 'L'],
  ['L', 'P', 'M', 'W', 'G', 'T', 'J'],
  ['N', 'G', 'M', 'T', 'B', 'F', 'Q', 'H'],
  ['R', 'D', 'G', 'C', 'P', 'B', 'Q', 'W']
]

const input = fs.readFileSync('data.txt', { encoding: 'utf-8' }).split(/\r\n\r\n/)[1];
for(const line of input.split('\n')) {
  const [_line, num, from, to] = line.match(/move (\d+) from (\d+) to (\d+)/)

  const tmp = []
  for(let i = 0; i < num; i++) {
    tmp.push(stacks[from - 1].pop());
  }
  for(let i = 0; i < num; i++) {
    stacks[to - 1].push(tmp.pop());
  }
}

for(const stack of stacks) {
  process.stdout.write(stack[stack.length - 1])
}