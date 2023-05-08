import sys

input = sys.stdin.readline

T = int(input())


def find_min_max():
    k[0] = min(current[0], k[0])
    k[1] = min(current[1], k[1])
    k[2] = max(current[0], k[2])
    k[3] = max(current[1], k[3])


def move(m):
    for i in range(2):
        current[i] += pos[m][i]
    find_min_max()


while T:
    T -= 1
    # minX, minY, maxX, maxY
    k = [0, 0, 0, 0]
    s = list(input())
    current = [0, 0]
    direction = 0
    pos = [[0, 1], [1, 0], [0, -1], [-1, 0]]
    for i in s:
        if i == "F":
            move(direction)
        if i == "B":
            move((direction + 2) % 4)
        if i == "L":
            direction -= 1
            direction %= 4
        if i == "R":
            direction += 1
            direction %= 4
    print((k[2] - k[0]) * (k[3] - k[1]))