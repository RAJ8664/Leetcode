class Solution:
    def angleClock(self, hour: int, minutes: int) -> float:
        x = hour + minutes / 60
        y = (11 * x) % 12
        return min(y, 12 - y) * 30
        