class Pair:
    def __init__(self, name, height) -> None:
        self.name = name
        self.height = height

    def __lt__(self, other):
        return self.height > other.height

class Solution:
    def sortPeople(self, names: List[str], heights: List[int]) -> List[str]:
        arr = []
        res = []
        
        for _ in range(len(names)):
            arr.append(Pair(names[_], heights[_]))

        arr.sort()

        for _ in range(len(arr)):
            res.append(arr[_].name)

        return res 
        