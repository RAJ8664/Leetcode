class Solution:
    def get_strength(self, char) -> int:
        if char.isalpha():
            if char.isupper():
                return 2
            if char.islower():
                return 1
        if char.isdigit():
            return 3
        return 5

    def passwordStrength(self, password: str) -> int:
        n = len(password)
        strength = 0
        st = set()
        for i in range(n):
            if password[i] in st:
                continue
            st.add(password[i])
            strength += self.get_strength(password[i])
        return strength
            
        