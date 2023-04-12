n = int(input())
arr = list(map(str, input().rstrip()))

red = arr.count('R')
blue = n - red

result = min(red, blue)
count = 0

# 앞부터
for i in range(n):
    if arr[i] != arr[0]: break
    count += 1

    if arr[0] == 'R':
        result = min(result, red - count)
    else:
        result = min(result, blue - count)

count = 0

# 뒤부터
for i in range(n - 1, -1, -1):
    if arr[i] != arr[n - 1]:
        break

    count += 1
if arr[n - 1] == 'R':
    result = min(result, red - count)
else:
    result = min(result, blue - count)

print(result)