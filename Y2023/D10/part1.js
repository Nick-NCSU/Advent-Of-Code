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

//DFS to find farthest node from start
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
console.log(arr.length / 2)