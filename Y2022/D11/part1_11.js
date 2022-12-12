const fs = require('fs')

const input = fs.readFileSync('data.txt', { encoding: 'utf-8' }).split('\n\n').map(line => line.match(/Monkey (\d+):\n  Starting items: ([^\n]+)\n  Operation: new = old (.) ([^\n]+)\n  Test: divisible by (\d+)\n    If true: throw to monkey (\d+)\n    If false: throw to monkey (\d+)/));
const monkeys = [];
const totals = [];
for(const [_line, number, items, symbol, operation, divisor, trueMonkey, falseMonkey] of input) {
  totals[+number] = 0;
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
for(let i = 0; i < 20; i++) {
  for(const monkey of monkeys) {
    while(monkey.items.length) {
      totals[monkey.idx]++;
      const item = monkey.items.shift();
      monkey.symbol === '*' ? (item.value *= monkey.operation === 'old' ? item.value : +monkey.operation) : (item.value += monkey.operation === 'old' ? item.value : +monkey.operation);
      item.value = Math.floor(item.value / 3);
      monkeys[item.value % monkey.divisor === 0 ? monkey.trueMonkey : monkey.falseMonkey].items.push(item)
    }
  }
}
console.log(JSON.stringify(monkeys, null, 2))
console.log(totals)