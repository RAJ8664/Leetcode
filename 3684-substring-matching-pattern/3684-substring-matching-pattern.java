class Solution {
    public boolean hasMatch(String s, String p) {
        int idx = p.indexOf("*");
        int left = s.indexOf(p.substring(0,idx));
        int idx2 = left + p.substring(0,idx).length();
        String temp = s.substring(idx2);
        int right = temp.indexOf(p.substring(idx+1));
        if(left!=-1 && right!= -1) return true;
        return false;
    }
}