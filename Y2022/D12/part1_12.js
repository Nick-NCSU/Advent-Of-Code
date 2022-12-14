const fs = require('fs')

let start, end;
const input = fs.readFileSync('data.txt', { encoding: 'utf-8' }).split('\n').map((line, i) => line.split('').map((char, j) => {
  switch(char) {
    case 'S':
      start = `${i},${j}`;
      return 0;
    case 'E':
      end = `${i},${j}`;
      return 25;
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
console.log(dist[end])