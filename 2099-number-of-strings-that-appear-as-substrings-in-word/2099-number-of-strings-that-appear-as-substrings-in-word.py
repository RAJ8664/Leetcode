class Solution:
    def numOfStrings(self, patterns: List[str], word: str) -> int:
        count = 0

        for curr_word in patterns:
            if curr_word in word:
                count += 1

        return count
        