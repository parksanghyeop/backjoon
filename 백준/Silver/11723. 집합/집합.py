import sys

M = int(sys.stdin.readline())
S = 0b0

for _ in range(M):
    order = sys.stdin.readline().strip()

    try:
        command, num = order.split()
        num = int(num)
        if command == 'add':
            S = S | (0b1 << num)

        elif command == 'remove':
            S = S & ~(0b1 << num)

        elif command == 'check':
            if (S & (0b1 << num)):
                print(1)
            else:
                print(0)

        elif command == 'toggle':
            S = S ^ (0b1 << num)

    except:
        if order == 'all':
            S = 0b111111111111111111111

        elif order == 'empty':
            S = 0b0