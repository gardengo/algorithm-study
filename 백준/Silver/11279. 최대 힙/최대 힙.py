import sys
from queue import PriorityQueue

input = sys.stdin.readline
N = int(input())
pq = PriorityQueue()
results = []

for i in range(N):
    x = int(input())

    if x == 0:
        if pq.empty():
            results.append("0")
        else:
            results.append(str(-pq.get()))
        results.append("\n")
    else:
        pq.put(-x)

sys.stdout.write("".join(results))
