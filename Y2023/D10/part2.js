const fs = require('fs')

const input = fs.readFileSync(`${__dirname}/data.txt`, 'utf8').split('\n');

const graph = {};

const start = {
  x: 0,
  y: 0  
}
for(const [y, line] of input.entries()) {
  for(const [x, n] of line.split('').entries()) {
    switch(n) {
      case '|': graph[`${x},${y}`] = [[x, y - 1], [x, y + 1]]; break;
      case '-': graph[`${x},${y}`] = [[x - 1, y], [x + 1, y]]; break;
      case 'L': graph[`${x},${y}`] = [[x + 1, y], [x, y - 1]]; break;
      case 'J': graph[`${x},${y}`] = [[x - 1, y], [x, y - 1]]; break;
      case '7': graph[`${x},${y}`] = [[x - 1, y], [x, y + 1]]; break;
      case 'F': graph[`${x},${y}`] = [[x + 1, y], [x, y + 1]]; break;
      case '.': break;
      default: 
        const arr = [];
        if(input[y - 1]?.[x] && input[y - 1][x] !== '.') arr.push([x, y - 1]);
        if(input[y + 1]?.[x] && input[y + 1][x] !== '.') arr.push([x, y + 1]);
        if(input[y]?.[x - 1] && input[y][x - 1] !== '.') arr.push([x - 1, y]);
        if(input[y]?.[x + 1] && input[y][x + 1] !== '.') arr.push([x + 1, y]);
        graph[`${x},${y}`] = arr;
        start.x = x;
        start.y = y;
        break;
    }
  }
}

const visited = {};
const arr = [];
const stack = [`${start.x},${start.y}`];
while(stack.length) {
  const node = stack.pop();
  if(visited[node]) continue;
  visited[node] = true;
  arr.push(node);
  for(const next of graph[node]) {
    stack.push(`${next[0]},${next[1]}`);
  }
}

const nodes = new Set();
const holes = [];

for(const [cy, line] of input.entries()) {
  for(let [cx, n] of line.split('').entries()) {
    const x = cx * 2;
    const y = cy * 2;
    if(!visited[`${cx},${cy}`]) {
      holes.push([x, y]);
      continue;
    }
    switch(n) {
      case 'S':
      case '|': 
        nodes.add(`${x},${y}`);
        nodes.add(`${x},${y + 1}`);
        break;
      case '-':
        nodes.add(`${x},${y}`);
        nodes.add(`${x + 1},${y}`);
        break;
      case 'F':
        nodes.add(`${x},${y}`);
        nodes.add(`${x + 1},${y}`);
        nodes.add(`${x},${y + 1}`);
        break;
      case 'L':
        nodes.add(`${x},${y}`);
        nodes.add(`${x + 1},${y}`);
        nodes.add(`${x},${y - 1}`);
        break;
      case '7':
        nodes.add(`${x},${y}`);
        nodes.add(`${x - 1},${y}`);
        nodes.add(`${x},${y + 1}`);
        break;
      case 'J':
        nodes.add(`${x},${y}`);
        nodes.add(`${x - 1},${y}`);
        nodes.add(`${x},${y - 1}`);
        break;
    }
  }
}

let total = 0;
for(const hole of holes) {
  const visited = new Set();
  const stack = [hole];
  let found = false;
  while(stack.length) {
    const [x, y] = stack.pop();
    const key = `${x},${y}`;
    if(visited.has(key)) continue;
    visited.add(key);
    if(nodes.has(key)) continue;
    if(x === 0 || x === 2 * input[0].length - 1 || y === 0 || y === 2 * input.length - 1) {
      found = true;
      break;
    }
    stack.push([x + 1, y]);
    stack.push([x - 1, y]);
    stack.push([x, y + 1]);
    stack.push([x, y - 1]);
  }
  if(!found) {
    total++;
  }
}
console.log(total);