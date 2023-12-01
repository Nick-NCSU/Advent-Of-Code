const fs = require('fs')

const input = fs.readFileSync(`${__dirname}/data.txt`, 'utf8').split('\n')

const wordMap = {
    'one': 1,
    'two': 2,
    'three': 3,
    'four': 4,
    'five': 5,
    'six': 6,
    'seven': 7,
    'eight': 8,
    'nine': 9
}

const result = input.reduce((acc, curr) => {
    a:
    for(let i = 0; i < curr.length; i++) {
        if(!isNaN(+curr[i])) {
            break;
        }
        for(const word of Object.keys(wordMap)) {
            if(curr.substring(i, i + word.length) === word) {
                curr = wordMap[word] + curr.substring(i + word.length);
                break a
            }
        }
    }
    b:
    for(let i = curr.length - 1; i >= 0; i--) {
        if(!isNaN(+curr[i])) {
            break;
        }
        for(const word of Object.keys(wordMap)) {
            if(curr.substring(i, i + word.length) === word) {
                curr = curr.substring(0, i) + wordMap[word];
                break b
            }
        }
    }
    curr = curr.replace(/[^\d]/g, '')
    return acc + +(curr[0] + curr[curr.length - 1]);
}, 0);

console.log(result);