const fs = require('fs')

const input = fs.readFileSync(`${__dirname}/data.txt`, 'utf8').split('\n')

const result = input.reduce((acc, curr) => {
    const num = curr.replace(/[^\d]/g, '');
    return acc + +(num[0] + num[num.length - 1]);
}, 0);

console.log(result);