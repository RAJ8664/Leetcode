class Solution {
    public boolean isOneBitCharacter(int[] bits) {
        int current = bits.length - 2;
        while (current >= 0 && bits[current] > 0) 
            current--;
        return (bits.length - current) % 2 == 0;
    }
}