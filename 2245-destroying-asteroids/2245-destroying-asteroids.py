class Solution:
    def asteroidsDestroyed(self, mass: int, asteroids: List[int]) -> bool:
        n = len(asteroids)
        asteroids.sort()
        curr_mass = mass
        for ele in asteroids:
            if curr_mass < ele:
                return False
            curr_mass += ele

        return True
        