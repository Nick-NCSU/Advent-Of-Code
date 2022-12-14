const fs = require('fs')

let starts = [], end;
const input = fs.readFileSync('data.txt', { encoding: 'utf-8' }).split('\n').map((line, i) => line.split('').map((char, j) => {
  switch(char) {
    case 'E':
      end = `${i},${j}`;
      return 25;
    case 'S':
    case 'a':
      starts.push(`${i},${j}`);
      return 0;
    default:
      return char.charCodeAt(0) - 97;
  };
}));
const graph = {};

for(const [i, row] of input.entries()) {
  for(let [j, col] of row.entries()) {
    const coord = `${i},${j}`;
    graph[coord] = [];
    if(i > 0 && input[i-1][j] - 1 <= col) {
      graph[coord].push(`${i-1},${j}`);
    }
    if(i < input.length - 1 && input[i+1][j] - 1 <= col) {
      graph[coord].push(`${i+1},${j}`);
    }
    if(j > 0 && input[i][j-1] - 1 <= col) {
      graph[coord].push(`${i},${j-1}`);
    }
    if(j < input[0].length - 1 && input[i][j+1] - 1 <= col) {
      graph[coord].push(`${i},${j+1}`);
    }
  }
}

let min = Number.POSITIVE_INFINITY;
for(const start of starts) {
  const queue = [start];
  const dist = {
    [start]: 0,
  };
  while(queue.length) {
    const current = queue.shift();
    for(const node of graph[current]) {
      if(!(node in dist)) {
        queue.push(node);
        dist[node] = dist[current] + 1;
      }
    }
  }
  if(dist[end]) {
    min = Math.min(min, dist[end]);
  }
}
console.log(min)