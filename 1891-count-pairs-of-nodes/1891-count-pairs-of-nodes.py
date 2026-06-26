class Solution:
    def countPairs(self, n: int, edges: List[List[int]], queries: List[int]) -> List[int]:
        degree = [0] * n 
        freq = defaultdict(int)
        res = []

        for u, v in edges:
            degree[u - 1] += 1
            degree[v - 1] += 1
            freq[min(u - 1, v - 1), max(u - 1, v - 1)] += 1
        
        arr = sorted(degree)

        for query in queries:
            count = 0
            low, high = 0, n - 1
            while low < high:
                if query < arr[low] + arr[high]:
                    count += high - low
                    high = high - 1
                else:
                    low = low + 1
            for u, v in freq:
                    if degree[u] + degree[v] - freq[u, v] <= query < degree[u] + degree[v]:
                        count = count - 1
            res.append(count)
        
        return res