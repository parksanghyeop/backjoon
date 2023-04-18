import sys

row_len, col_len = map(int, input().split())
arr = []
for _ in range(row_len):
    arr.append(list(map(int, sys.stdin.readline().split())))

ice = {}
for i in range(1, row_len - 1):
    for j in range(1, col_len - 1):
        if arr[i][j]:
            ice[(i, j)] = 1

dy = [-1, 0, 1, 0]
dx = [0, 1, 0, -1]


def DFS(row_i, col_i):
    linked_ice = [(row_i, col_i)]

    while linked_ice:
        item = linked_ice.pop()
        if not visited_ice[item[0]][item[1]]:
            visited_ice[item[0]][item[1]] = True

            water_count = 0

            for i in range(4):
                row = item[0] + dy[i]
                col = item[1] + dx[i]

                if not visited_ice[row][col]:
                    if arr[row][col] <= 0:
                        water_count += 1
                    else:
                        linked_ice.append((row, col))

            arr[item[0]][item[1]] -= water_count

            if arr[item[0]][item[1]] <= 0:
                ice[(item[0], item[1])] = 0


year = 0
while sum(ice.values()) > 0:
    dfs_count = 1
    visited_ice = [[False] * col_len for _ in range(row_len)]

    for key, value in ice.items():
        if value and not visited_ice[key[0]][key[1]]:
            if dfs_count == 2:
                print(year)
                break
            DFS(key[0], key[1])
            dfs_count += 1
    else:
        year += 1
        continue
    break

else:
    print(0)