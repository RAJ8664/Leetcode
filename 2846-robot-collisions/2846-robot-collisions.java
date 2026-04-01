class Solution {
    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < positions.length; i++) 
            list.add(i);
        Collections.sort(list, (a, b) -> Integer.compare(positions[a], positions[b]));
        Stack<Integer> stck = new Stack<>();
        for (int l : list) {
            if (directions.charAt(l) == 'L') {
                while (!stck.isEmpty()) {
                    int temp = stck.peek();
                    if (healths[l] == healths[temp]) {
                        healths[l] = 0;
                        healths[temp] = 0;
                        stck.pop();
                        break;
                    } else if (healths[l] > healths[temp]) {
                        healths[l]--;
                        healths[temp] = 0;
                        stck.pop();
                    } else {
                        healths[l] = 0;
                        healths[temp]--;
                        break;
                    }
                }
            } else 
                stck.push(l);
        }
        List<Integer> res = new ArrayList<>();
        for (var h : healths) {
            if (h != 0)
                res.add(h);
        }
        return res;
    }
}