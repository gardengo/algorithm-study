import sys

input = sys.stdin.readline
N, M = map(int, input().split())

site_pw = {}
for i in range(N):
    a, b = map(str, input().split())
    site_pw[a] = b

output = []
for i in range(M):
    output.append(site_pw.get(input().strip()))
    output.append('\n')

sys.stdout.write("".join(output))
