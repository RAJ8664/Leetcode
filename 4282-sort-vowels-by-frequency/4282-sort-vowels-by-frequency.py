class Pair:
    def __init__(self, char, frequency, idx):
        self.char = char
        self.frequency = frequency
        self.original = frequency
        self.idx = idx

    def __repr__(self):
        return f"({self.char}, {self.frequency})"

    def __lt__(self, other):
        if self.original == other.original:
            return self.idx < other.idx
        return self.original > other.original

class Solution:
    def is_vowel(self, char):
        if char == 'a' or char == 'e' or char == 'i' or char == 'o' or char == 'u':
            return True
        return False

    def sortVowels(self, s: str) -> str:
        n = len(s)

        freq = dict()
        first = dict()
        for i in range(n):
            if self.is_vowel(s[i]):
                if s[i] in freq:
                    freq[s[i]] += 1
                else:
                    freq[s[i]] = 1
                    first[s[i]] = i

        pq = []
        for key, val in freq.items():
            heapq.heappush(pq, Pair(key, val, first[key]))

        res = ""
        for i in range(n):
            if not self.is_vowel(s[i]):
                res += s[i]
            else:
                curr_pair = heapq.heappop(pq)
                res += curr_pair.char
                curr_pair.frequency -= 1

                if curr_pair.frequency > 0:
                    heapq.heappush(pq, curr_pair)

        return res