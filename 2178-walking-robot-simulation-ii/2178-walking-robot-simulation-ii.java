class Robot {
    private String currentDir = "East";
    private int currX, currY, lastX, lastY;
    public Robot(int width, int height) {
        this.currX = 0;
        this.currY = 0;
        this.lastX = width - 1;
        this.lastY = height - 1;
    }

    public void step(int num) {
        if (lastX + 1 == 1 || lastY + 1 == 1)
            num %= (lastX + 1 == 1 ? 2 * (lastY + 1 - 1) : 2 * (lastX + 1 - 1));
        else
            num %= (2 * (lastX + 1 + lastY + 1 - 2));
        while (num > 0) {
            if (currentDir.equals("East")) {
                if (currX + num <= lastX) {
                    currX += num;
                    num = 0;
                } else {
                    num -= lastX - currX;
                    currX = lastX;
                    currentDir = "North";
                }
            } else if (currentDir.equals("West")) {
                if (currX - num >= 0) {
                    currX -= num;
                    num = 0;
                } else {
                    num -= currX;
                    currX = 0;
                    currentDir = "South";
                }
            } else if (currentDir.equals("North")) {
                if (currY + num <= lastY) {
                    currY += num;
                    num = 0;
                } else {
                    num -= lastY - currY;
                    currY = lastY;
                    currentDir = "West";
                }
            } else if (currentDir.equals("South")) {
                if (currY - num >= 0) {
                    currY -= num;
                    num = 0;
                } else {
                    num -= currY;
                    currY = 0;
                    currentDir = "East";
                }
            }
        }
        if (currX == 0 && currY == 0 && num == 0)
            currentDir = "South";
    }

    public int[] getPos() {
        return new int[] {currX, currY};
    }

    public String getDir() {
        return currentDir;
    }
}

/**
    Your Robot object will be instantiated and called as such:
    Robot obj = new Robot(width, height);
    obj.step(num);
    int[] param_2 = obj.getPos();
    String param_3 = obj.getDir();
*/