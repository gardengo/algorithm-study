import sys

input = sys.stdin.readline
tc = int(input())
results = []

for _ in range(tc):
    n = int(input())
    types = {}

    for _ in range(n):
        name, type = input().split()
        if types.get(type) is None:
            types[type] = []
            types[type].append(name)
        else:
            types[type].append(name)

    result = 1
    for type in types:
        result *= len(types[type]) + 1
    result -= 1

    results.append(str(result))
    results.append("\n")

sys.stdout.write("".join(results))
