class Solution:
    def numberOfSubstrings(self, s: str) -> int:
        n = len(s)
        freq = defaultdict(int)

        left, right, count = 0, 0, 0
        while left < n:
            while right < n and len(freq) < 3:
                freq[s[right]] += 1
                right += 1

            if len(freq) == 3:
                count += n - right + 1

            freq[s[left]] -= 1
            if freq[s[left]] == 0:
                del freq[s[left]]

            left += 1

        return count
        