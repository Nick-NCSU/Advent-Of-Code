lines = []
with open('data.txt') as f:
  lines = f.readlines()

def intersection(lst1, lst2):
    lst3 = [value for value in lst1 if value in lst2]
    return lst3

total = 0
for l in lines:
  a, b = l.split(',')
  a1, a2 = [int(x) for x in a.split('-')]
  b1, b2 = [int(x) for x in b.split('-')]
  if (a1 <= b1 and a2 >= b2) or (b1 <= a1 and b2 >= a2):
    total += 1

print(total)