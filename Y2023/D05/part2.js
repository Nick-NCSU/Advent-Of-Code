const fs = require('fs')

const input = fs.readFileSync(`${__dirname}/data.txt`, 'utf8').split('\n\n')

const seeds = input[0].split(' ').slice(1).map(Number);

const seedToSoil = input[1].split('\n').slice(1).map(l => {
  const nums = l.split(' ').map(Number);
  return {
    source: nums[0],
    destination: nums[1],
    range: nums[2],
  }
});

const soilToFertilizer = input[2].split('\n').slice(1).map(l => {
  const nums = l.split(' ').map(Number);
  return {
    source: nums[0],
    destination: nums[1],
    range: nums[2],
  }
});

const fertilizerToWater = input[3].split('\n').slice(1).map(l => {
  const nums = l.split(' ').map(Number);
  return {
    source: nums[0],
    destination: nums[1],
    range: nums[2],
  }
});

const waterToLight = input[4].split('\n').slice(1).map(l => {
  const nums = l.split(' ').map(Number);
  return {
    source: nums[0],
    destination: nums[1],
    range: nums[2],
  }
});

const lightToTemperature = input[5].split('\n').slice(1).map(l => {
  const nums = l.split(' ').map(Number);
  return {
    source: nums[0],
    destination: nums[1],
    range: nums[2],
  }
});

const temperatureToHumidity = input[6].split('\n').slice(1).map(l => {
  const nums = l.split(' ').map(Number);
  return {
    source: nums[0],
    destination: nums[1],
    range: nums[2],
  }
});

const humidityToLocation = input[7].split('\n').slice(1).map(l => {
  const nums = l.split(' ').map(Number);
  return {
    source: nums[0],
    destination: nums[1],
    range: nums[2],
  }
});

function getRange(map, value) {
  for(const { source, destination, range } of map) {
    if(destination <= value && value <= destination + range - 1) {
      return source + (value - destination);
    }
  }
  return value;
}

const mapOrder = [seedToSoil, soilToFertilizer, fertilizerToWater, waterToLight, lightToTemperature, temperatureToHumidity, humidityToLocation];
let minLocation = Infinity;
for(let i = 0; i < seeds.length; i += 2) {
  for(let j = seeds[i]; j < seeds[i] + seeds[i + 1]; j++) {
    if(j % 10_000_000 === 0) {
      console.log(`${i / 2}/${seeds.length / 2}`)
      console.log((j - seeds[i]) / seeds[i + 1])
    }
    let seed = j;
    for(const map of mapOrder) {
      seed = getRange(map, seed);
    }
    minLocation = Math.min(minLocation, seed);
  }
}
console.log(minLocation);