const fs = require('fs')

const input = fs.readFileSync(`${__dirname}/data.txt`, 'utf8').split('\n')

let total = 0;
for(const line of input) {
    let [_, _game, a] = line.match(/Game (\d+): (.+)/);
    a = a.split('; ').map(t => t.split(',').map(s => s.trim()).map(s => s.split(' ')).reduce((acc, s) => ({ ...acc, [s[1]]: +s[0] }), {}));
    const max = {
        red: 1,
        green: 1,
        blue: 1
    }
    for(const set of a) {
        for(const color of Object.keys(max)) {
            if(set[color] > max[color]) {
                max[color] = set[color];
            }
        }
    }
    total += max['red'] * max['green'] * max['blue'];
}

console.log(total);