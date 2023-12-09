const fs = require('fs')

const input = fs.readFileSync(`${__dirname}/data.txt`, 'utf8').split('\n');

let total = 0;
for(const line of input) {
  const nums = [line.split(' ').map(Number)];
  while(!nums.at(-1).every(n => n === 0)) {
    const next = [];
    for(let i = 0; i < nums.at(-1).length - 1; i++) {
      next.push(nums.at(-1)[i + 1] - nums.at(-1)[i]);
    }
    nums.push(next);
  }
  for(let i = nums.length - 1; i > 0; i--) {
    nums[i - 1].push(nums[i - 1].at(-1) + nums[i].at(-1));
  }
  total += nums[0].at(-1);
}
console.log(total);
