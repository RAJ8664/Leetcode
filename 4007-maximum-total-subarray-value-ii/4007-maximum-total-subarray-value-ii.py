class Tuple:
    def __init__(self, cost, left, right):
        self.cost = cost
        self.left = left
        self.right = right

    def __repr__(self):
        return f"({self.cost}, {self.left}, {self.right})"

    def __lt__(self, other):
        return self.cost > other.cost

class SparseTable:
    def __init__(self, arr):
        self.n = len(arr)
        self.LOG = self.n.bit_length()

        self.mn = [[0] * self.n for _ in range(self.LOG)]
        self.mx = [[0] * self.n for _ in range(self.LOG)]

        for i in range(self.n):
            self.mn[0][i] = arr[i]
            self.mx[0][i] = arr[i]

        j = 1
        while (1 << j) <= self.n:
            length = 1 << j
            half = length >> 1

            for i in range(self.n - length + 1):
                self.mn[j][i] = min(
                    self.mn[j - 1][i],
                    self.mn[j - 1][i + half]
                )

                self.mx[j][i] = max(
                    self.mx[j - 1][i],
                    self.mx[j - 1][i + half]
                )

            j += 1

    def range_min(self, l, r):
        k = (r - l + 1).bit_length() - 1
        return min(
            self.mn[k][l],
            self.mn[k][r - (1 << k) + 1]
        )

    def range_max(self, l, r):
        k = (r - l + 1).bit_length() - 1
        return max(
            self.mx[k][l],
            self.mx[k][r - (1 << k) + 1]
        )

    def getCost(self, l, r):
        return self.range_max(l, r) - self.range_min(l, r)
  
class Solution:
    def maxTotalValue(self, nums: List[int], k: int) -> int:
        n = len(nums)

        st = SparseTable(nums)

        pq = []
        res = 0
        for i in range(0, n):
            heapq.heappush(pq, Tuple(st.getCost(i, n - 1), i, n - 1)) 

        while k > 0:
            current = heapq.heappop(pq)
            res += current.cost
            if (current.right > current.left):
                heapq.heappush(pq, Tuple(st.getCost(current.left, current.right - 1), current.left, current.right - 1))
            k -= 1  

        return res 