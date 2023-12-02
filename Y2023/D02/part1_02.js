const fs = require('fs')

const input = fs.readFileSync(`${__dirname}/data.txt`, 'utf8').split('\n')

const max = {
    red: 12,
    green: 13,
    blue: 14
}

let total = 0;
next: 
for(const line of input) {
    let [_, game, a] = line.match(/Game (\d+): (.+)/);
    a = a.split('; ').map(t => t.split(',').map(s => s.trim()).map(s => s.split(' ')).reduce((acc, s) => ({ ...acc, [s[1]]: +s[0] }), {}));
    for(const set of a) {
        for(const color of Object.keys(max)) {
            if(set[color] > max[color]) {
                continue next;
            }
        }
    }
    total += +game;
}

console.log(total);