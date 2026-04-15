class Solution:
    def closestTarget(self, words: List[str], target: str, startIndex: int) -> int:
        n = len(words)
        mini, count, current = 10 ** 9, 0, startIndex

        while (count <= 2 * n):
            if words[current] == target:
                mini = min(mini, count)
                break
            count += 1
            current = (current + 1) % n
        
        count = 0
        current = startIndex
        while (count <= 2 * n):
            if words[current] == target:
                mini = min(mini, count)
                break
            count += 1
            current = (current - 1 + n) % n

        if mini == 10 ** 9: return -1
        return mini