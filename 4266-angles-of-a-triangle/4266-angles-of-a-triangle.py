class Solution:
    def triangle_angles(self, a, b, c) -> list[float]:
        A = math.acos((b * b + c * c - a * a) / (2 * b * c))
        B = math.acos((a * a + c * c - b * b) / (2 * a * c))
        C = math.acos((a * a + b * b - c * c) / (2 * a * b))
        
        A = math.degrees(A)
        B = math.degrees(B)
        C = math.degrees(C)
        
        return [round(A, 5), round(B, 5), round(C, 5)]

    def internalAngles(self, sides: list[int]) -> list[float]:
        res: list[float] = []
        if (sides[0] + sides[1] <= sides[2]) or (sides[1] + sides[2] <= sides[0]) or (sides[2] + sides[0] <= sides[1]):
            return res
        res = self.triangle_angles(sides[0], sides[1], sides[2])
        res.sort()
        return res 