class Solution:
    def scoreValidator(self, events: list[str]) -> list[int]:
        score, counter = 0, 0
        for i in range(len(events)):
            if counter == 10: break
            if events[i][0] == 'N':
                score += 1
                continue
            if events[i][0] != 'W':
                score = score + int(events[i])
            elif events[i][0] == 'W' and len(events[i]) == 1:
                counter += 1
            else:
                score += 1
        return [score, counter]
            
        
        