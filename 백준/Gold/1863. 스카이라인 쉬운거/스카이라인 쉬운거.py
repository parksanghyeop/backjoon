from collections import deque

n = int(input())

stack = deque()
result = 0

for i in range(n):
    x, y = map(int, input().split())

    while len(stack) > 0 and stack[-1] > y:
        result += 1
        stack.pop()

    if len(stack) > 0 and stack[-1] == y:
        continue
    stack.append(y)

while len(stack) > 0:
    if stack[-1] > 0:
        result += 1
    stack.pop()

print(result)