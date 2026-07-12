class Solution:
    def secondsBetweenTimes(self, startTime: str, endTime: str) -> int:
        h1 = (int)(startTime[0:2])
        m1 = (int)(startTime[3:5])
        s1 = (int)(startTime[6:8])

        h2 = (int)(endTime[0:2])
        m2 = (int)(endTime[3:5])
        s2 = (int)(endTime[6:8])

        t1, t2 = 0, 0
        
        t1 = h1 * 60 * 60 + m1 * 60 + s1
        t2 = h2 * 60 * 60 + m2 * 60 + s2

        return t2 - t1