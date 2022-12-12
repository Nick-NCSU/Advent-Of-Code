def intersection(lst1, lst2):
    lst3 = [value for value in lst1 if value in lst2]
    return lst3

total = 0
f = open('data.txt')
try: 
  while f.readable():
    x, y, z = f.readline(),f.readline(),f.readline()
    c = intersection(x, y)
    c = intersection(c, z)
    if c[0].lower() == c[0]:
      total += ord(c[0]) - 96
    else: 
      total += ord(c[0]) - 64 + 26
except:
  print(total)