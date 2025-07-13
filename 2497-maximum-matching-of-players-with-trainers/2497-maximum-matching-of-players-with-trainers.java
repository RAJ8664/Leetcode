import java.util.HashMap;
import java.util.TreeSet;

class Solution {
    public int matchPlayersAndTrainers(int[] players, int[] trainers) {
        int n = players.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        TreeSet<Integer> set = new TreeSet<>();
        for (int ele : trainers) {
            map.put(ele, map.getOrDefault(ele, 0) + 1);
            set.add(ele);
        }
        int count = 0;
        for (int ele : players) {
            Integer match = set.ceiling(ele);
            if (match != null) {
                count++;
                map.put(match, map.getOrDefault(match, 0) -1);
                if (map.getOrDefault(match, 0) == 0) {
                    set.remove(match);
                    map.remove(match);
                }
            }
        }
        return count;
    }
}