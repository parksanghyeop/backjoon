import sys
input = sys.stdin.readline 

m,n,k = map(int,input().split())

if m > n :
    print("normal")
    exit()

secret_key = "".join(list(map(str,input().split())))
user_input = "".join(list(map(str,input().split())))

if secret_key in user_input:
    print("secret")
else:
    print("normal")
