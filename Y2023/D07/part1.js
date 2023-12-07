const fs = require('fs')

const input = fs.readFileSync(`${__dirname}/data.txt`, 'utf8').split('\n');

const Win = {
  A: -1,
  B: 1
}

const order = ['2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q','K', 'A'];
function getTie(a, b) {
  for(let i = 0; i < 5; i++) {
    if(order.indexOf(a[i]) > order.indexOf(b[i])) {
      return Win.A;
    } else if(order.indexOf(a[i]) < order.indexOf(b[i])) {
      return Win.B;
    }
  }
}

input.sort((a, b) => {
  const handA = a.split(' ')[0].split('').reduce((acc, c) => { acc[c] = (acc[c] ?? 0) + 1; return acc; }, {});
  const handB = b.split(' ')[0].split('').reduce((acc, c) => { acc[c] = (acc[c] ?? 0) + 1; return acc; }, {});
  if(Object.values(handA).includes(5)) {
    if(Object.values(handB).includes(5)) {
      return getTie(a.split(' ')[0], b.split(' ')[0]);
    } else {
      return Win.A;
    }
  }
  if(Object.values(handB).includes(5)) {
    return Win.B;
  }

  if(Object.values(handA).includes(4)) {
    if(Object.values(handB).includes(4)) {
      return getTie(a.split(' ')[0], b.split(' ')[0]);
    } else {
      return Win.A;
    }
  }
  if(Object.values(handB).includes(4)) {
    return Win.B;
  }

  if(Object.values(handA).includes(3) && Object.values(handA).includes(2)) {
    if(Object.values(handB).includes(3) && Object.values(handB).includes(2)) {
      return getTie(a.split(' ')[0], b.split(' ')[0]);
    } else {
      return Win.A;
    }
  }
  if(Object.values(handB).includes(3) && Object.values(handB).includes(2)) {
    return Win.B;
  }

  if(Object.values(handA).includes(3)) {
    if(Object.values(handB).includes(3)) {
      return getTie(a.split(' ')[0], b.split(' ')[0]);
    } else {
      return Win.A;
    }
  }
  if(Object.values(handB).includes(3)) {
    return Win.B;
  }

  if(Object.values(handA).filter(v => v === 2).length === 2) {
    if(Object.values(handB).filter(v => v === 2).length === 2) {
      return getTie(a.split(' ')[0], b.split(' ')[0]);
    } else {
      return Win.A;
    }
  }
  if(Object.values(handB).filter(v => v === 2).length === 2) {
    return Win.B;
  }

  if(Object.values(handA).filter(v => v === 2).length === 1) {
    if(Object.values(handB).filter(v => v === 2).length === 1) {
      return getTie(a.split(' ')[0], b.split(' ')[0]);
    } else {
      return Win.A;
    }
  }
  if(Object.values(handB).filter(v => v === 2).length === 1) {
    return Win.B;
  }

  return getTie(a.split(' ')[0], b.split(' ')[0]);
});

input.reverse();
let score = 0;
for(let i = 0; i < input.length; i++) {
  score += (i + 1) * +input[i].split(' ')[1];
}
console.log(score);
