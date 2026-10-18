class BusBooking:

    def __init__(self, n: int):
        self.booked_seat = set()
        self.all_seats = []
        self.total_time = 0
        self.cost = {}

    def toggle(self, seat: str) -> None:
        if seat not in self.booked_seat:
            self.booked_seat.add(seat)
            self.all_seats.append(seat)
            middle = {"A": "B", "D": "C"}.get(seat[0], "") + seat[1:]
            self.cost[seat] = 3 if middle in self.cost else 1
            self.total_time += self.cost[seat]           
        else:
            self.booked_seat.remove(seat)
            self.all_seats.remove(seat)
            self.total_time -= self.cost.pop(seat)

    def getTotalTime(self) -> int:
        return self.total_time
    
    def getMinTime(self) -> int:
        return len(self.booked_seat)
        


# Your BusBooking object will be instantiated and called as such:
# obj = BusBooking(n)
# obj.toggle(seat)
# param_2 = obj.getTotalTime()
# param_3 = obj.getMinTime()