const fs = require('fs')

const input = fs.readFileSync('data.txt', { encoding: 'utf-8' }).split('\n\n').map(line => line.match(/Monkey (\d+):\n  Starting items: ([^\n]+)\n  Operation: new = old (.) ([^\n]+)\n  Test: divisible by (\d+)\n    If true: throw to monkey (\d+)\n    If false: throw to monkey (\d+)/));
const monkeys = [];
const totals = [];
let mod = 1;
for(const [_line, number, items, symbol, operation, divisor, trueMonkey, falseMonkey] of input) {
  totals[+number] = 0;
  mod *= +divisor;
  monkeys[+number] = {
    items: items.split(', ').map(item => { return { value: +item } }),
    symbol,
    operation,
    divisor: +divisor,
    trueMonkey: +trueMonkey, 
    falseMonkey: +falseMonkey,
    idx: +number,
  };
}
for(let i = 0; i < 10_000; i++) {
  for(const monkey of monkeys) {
    while(monkey.items.length) {
      totals[monkey.idx]++;
      const item = monkey.items.shift();
      monkey.symbol === '*' ? (item.value *= monkey.operation === 'old' ? item.value : +monkey.operation) : (item.value += monkey.operation === 'old' ? item.value : +monkey.operation);
      item.value %= mod;
      monkeys[item.value % monkey.divisor === 0 ? monkey.trueMonkey : monkey.falseMonkey].items.push(item)
    }
  }
}
totals.sort((a, b) => b - a);
console.log(totals[0] * totals[1])