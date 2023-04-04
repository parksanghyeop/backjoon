from collections import defaultdict
import sys
input = sys.stdin.readline

SMELL = 3

dr8 = (False, 0, -1, -1, -1, 0, 1, 1, 1)
dc8 = (False, -1, -1, 0, 1, 1, 1, 0, -1)

dr4 = (False, -1, 0, 1, 0)
dc4 = (False, 0, -1, 0, 1)

M, S = map(int, input().split())
board = [[0] * 5 for _ in range(5)]

fish_location = defaultdict(list)
fish_moved_location = defaultdict(list)

for i in range(M):
    r, c, d = tuple(map(int, input().split()))
    fish_location[(r, c)].append(d)

shark_location = tuple(map(int, input().split()))


def check_range(r, c):
    return r < 1 or c < 1 or r > 4 or c > 4


def move_fishes():
    global fish_moved_location
    fish_moved_location = defaultdict(list)

    for loc, d_list in fish_location.items():
        r, c = loc
        for d in d_list:
            move_flag = False
            nd = d
            for i in range(8):
                nr = r + dr8[nd]
                nc = c + dc8[nd]

                if check_range(nr, nc) or board[nr][nc] > 0 or (nr, nc) == shark_location:
                    nd = nd - 1 if nd != 1 else 8
                    continue
                else:

                    move_flag = True
                    fish_moved_location[(nr, nc)].append(nd)
                    break
            if not move_flag:
                fish_moved_location[loc].append(d)


best_shark_move = None


def move_shark(shark, route, count, delete_loc):
    if len(route) != 0:
        if shark not in delete_loc:
            count += len(fish_moved_location[shark])
            delete_loc.append(shark)

    if len(route) == 3:
        global best_shark_move
        if best_shark_move == None:
            best_shark_move = (int(route), count, shark, delete_loc)
        elif best_shark_move[1] < count or (best_shark_move[1] == count and best_shark_move[0] > int(route)):
            best_shark_move = (int(route), count, shark, delete_loc)
        return

    for i in range(1, 5):
        r, c = shark
        nr = r + dr4[i]
        nc = c + dc4[i]
        if check_range(nr, nc):
            continue
        else:
            move_shark((nr, nc), route + str(i), count, delete_loc.copy())


def delete_and_update_fishes():
    global fish_location, fish_moved_location, shark_move_result, shark_location, best_shark_move
    _, _, shark_next_loc, deleted_loc = best_shark_move
    shark_location = shark_next_loc

    for loc in deleted_loc:
        y, x = loc
        if fish_moved_location[loc] != []:
            board[y][x] = SMELL
            fish_moved_location.pop(loc)

    for y in range(1, 5):
        for x in range(1, 5):
            if board[y][x] > 0:
                board[y][x] -= 1

    for k, v in fish_moved_location.items():
        if v:
            fish_location[k].extend(v)
    fish_moved_location = defaultdict(list)


def simulation():
    move_fishes()

    global best_shark_move
    best_shark_move = None
    move_shark(shark_location, "", 0, [])

    delete_and_update_fishes()


for i in range(S):
    simulation()

result = 0
for k, v in fish_location.items():
    result += len(v)

print(result)