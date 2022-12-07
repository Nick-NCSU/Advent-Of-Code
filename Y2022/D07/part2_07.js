const fs = require('fs')

const input = fs.readFileSync('data.txt', { encoding: 'utf-8' }).split('\r\n');
let nodes = {
  '~': []
};
let dirs = [];
for(let i = 0; i < input.length; i++) {
  if(input[i] === '$ ls') {
    let j = i + 1;
    while(j < input.length && !input[j].startsWith('$')) {
      if(!nodes[dirs.join('/')]) {
        nodes[dirs.join('/')] = [];
      }
      const [info, name] = input[j].split(' ');
      nodes[dirs.join('/')].push({
        info,
        name: dirs.join('/') + '/' + name
      });
      j++;
    }
    i = j - 1;
  } else if(input[i] === '$ cd ..') {
    dirs.pop();
  } else if(input[i] === '$ cd /') {
    dirs = ['~'];
  } else {
    dirs.push(input[i].split(' ')[2]);
  }
}
const totals = {}
let unfound = Object.keys(nodes);
while(unfound.length) {
  const found = []
  for(const key of unfound) {
    if(nodes[key].filter(node => node.info === 'dir').length === 0) {
      totals[key] = nodes[key].reduce((total, val) => total + +val.info, 0);
      for(const node in nodes) {
        for(const k of nodes[node]) {
          if(k.name === key) {
            k.info = totals[key];
          }
        }
      }
      found.push(key)
    }
  }
  unfound = unfound.filter(k => !found.includes(k));
}
let a = 70_000_000 - nodes['~'].reduce((total, val) => total + +val.info, 0);
let min = Number.MAX_SAFE_INTEGER;
for(const node in nodes) {
  if(nodes[node].reduce((total, val) => total + +val.info, 0) + a >= 30_000_000) {
    min = Math.min(min, nodes[node].reduce((total, val) => total + +val.info, 0));
  }
}
console.log(min)