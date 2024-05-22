stack = []
s = input()
for c in s:
    if c == ' ':
        continue
    elif c in ['+', '-', '*']:
        x1 = stack.pop()
        x2 = stack.pop()
        if c == '+':
            x2 += x1
        elif c == '-':
            x2 -= x1
        else:
            x2 *= x1
        stack.append(x2)
    else:
        stack.append(int(c))
print(stack.pop())
