class RideSharingSystem {
    private Queue<Integer> riderQueue;
    private Queue<Integer> driverQueue;
    private Set<Integer> activeRiders;

    public RideSharingSystem() {
        riderQueue = new LinkedList<>();
        driverQueue = new LinkedList<>();
        activeRiders = new HashSet<>();
    }

    public void addRider(int riderId) {
        riderQueue.offer(riderId);
        activeRiders.add(riderId);
    }

    public void addDriver(int driverId) {
        driverQueue.offer(driverId);
    }

    public int[] matchDriverWithRider() {
        while (!riderQueue.isEmpty() && !activeRiders.contains(riderQueue.peek()))
            riderQueue.poll();
        if (riderQueue.isEmpty() || driverQueue.isEmpty())
            return new int[] {-1, -1};
        int driverId = driverQueue.poll();
        int riderId = riderQueue.poll();
        activeRiders.remove(riderId);
        return new int[] {driverId, riderId};
    }

    public void cancelRider(int riderId) {
        activeRiders.remove(riderId);
    }
}


/**
    Your RideSharingSystem object will be instantiated and called as such:
    RideSharingSystem obj = new RideSharingSystem();
    obj.addRider(riderId);
    obj.addDriver(driverId);
    int[] param_3 = obj.matchDriverWithRider();
    obj.cancelRider(riderId);
*/