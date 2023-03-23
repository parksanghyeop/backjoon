import sys
input = sys.stdin.readline

N = int(input())
stats = list(map(int, input().split()))


def get_max_stats():
    stats.sort()

    result = sys.maxsize

    for i in range(N):
        s = stats[i] + stats[2*N-i-1]
        result = min(result, s)

    return result


print(get_max_stats())