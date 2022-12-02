import sys

n = int(sys.stdin.readline())
for _ in range(n):
    line = list(map(str, sys.stdin.readline().strip()))
    stack = []
    result = []
    for word in line:
        if word == "-":
            if result:
                result.pop()
        elif word == "<":
            if result:
                stack.append(result.pop())
        elif word == ">":
            if stack:
                result.append(stack.pop())
        else:
            result.append(word)
    while stack:
        result.append(stack.pop())

    print("".join(result))