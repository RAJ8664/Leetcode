class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
		TreeMap<Long, Integer> map = new TreeMap<>();
        map.put(Long.MAX_VALUE, potions.length);
        
		for (int i = potions.length - 1; i >= 0; i--) 
			map.put((long) potions[i], i);
		
		for (int i = 0; i < spells.length; i++) {
            long need = (success + spells[i] - 1) / spells[i];
			spells[i] = potions.length - map.ceilingEntry(need).getValue();
		}
		return spells;
    }
}