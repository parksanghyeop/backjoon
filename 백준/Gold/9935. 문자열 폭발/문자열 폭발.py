row = input().rstrip()
bomb_string = input().rstrip()

stack = []
bomb_len = len(bomb_string)

for i in range(len(row)):
    stack.append(row[i])
    if ''.join(stack[-bomb_len:]) == bomb_string:
        for _ in range(bomb_len):
            stack.pop()

if stack:
    print("".join(stack))
else:
    print("FRULA")