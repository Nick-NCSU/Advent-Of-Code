m = []
t = 0
for x in open('data.txt'):
  if len(x) > 1:
    t += int(x)
  else:
    m.append(t)
    t = 0

m.sort()
print(m[-3] + m[-2] + m[-1])