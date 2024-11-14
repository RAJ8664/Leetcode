class Solution {
    public int uniqueLetterString(String s) {
        int n = s.length();
        HashMap<Character, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            map.computeIfAbsent(current, k->new ArrayList<>()).add(i);
        }
        int res = 0;
        for (char i = 'A'; i <= 'Z'; i++) {
            if (!map.containsKey(i)) continue;
            ArrayList<Integer> current = new ArrayList<>();
            current = map.get(i);
            int prev = 0;
            for (int j = 0; j < current.size(); j++) {
                int left = 0, right = 0;
                if (j == 0) left = current.get(j);
                else left = current.get(j) - current.get(j - 1) - 1;
                if (j == current.size() - 1) right = n - current.get(j) - 1;
                else right = current.get(j + 1) - current.get(j) - 1;
                res += 1 + left + (right * (left + 1));
            }
        }
        return res;
    }

    static Debug dbg = new Debug();
    static class Debug {
        public static boolean LOCAL = true;
        public static boolean getLocal() {
            try {
                return System.getProperty("LOCAL") == null;
            }catch(SecurityException e) {
                return false;
            }
        }
        public static <T> String ts(T t) {
            if(t==null) {
                return "null";
            }
            if(t instanceof Iterable) {
                return ts((Iterable<?>) t);
            }else if(t instanceof int[]) {
                String s = Arrays.toString((int[]) t);
                return "{"+s.substring(1, s.length()-1)+"}";
            }else if(t instanceof long[]) {
                String s = Arrays.toString((long[]) t);
                return "{"+s.substring(1, s.length()-1)+"}";
            }else if(t instanceof char[]) {
                String s = Arrays.toString((char[]) t);
                return "{"+s.substring(1, s.length()-1)+"}";
            }else if(t instanceof double[]) {
                String s = Arrays.toString((double[]) t);
                return "{"+s.substring(1, s.length()-1)+"}";
            }else if(t instanceof boolean[]) {
                String s = Arrays.toString((boolean[]) t);
                return "{"+s.substring(1, s.length()-1)+"}";
            }else if(t instanceof Object[]) {
                return ts((Object[]) t);
            }
            return t.toString();
        }
        private static <T> String ts(T[] arr) {
            StringBuilder ret = new StringBuilder();
            ret.append("{");
            boolean first = true;
            for(T t: arr) {
                if(!first) ret.append(", ");
                first = false;
                ret.append(ts(t));
            }
            ret.append("}");
            return ret.toString();
        }
        private static <T> String ts(Iterable<T> iter) {
            StringBuilder ret = new StringBuilder();
            ret.append("{");
            boolean first = true;
            for(T t: iter) {
                if(!first) ret.append(", ");
                first = false;
                ret.append(ts(t));
            }
            ret.append("}");
            return ret.toString();
        }
        public static void print(Object... o) {
            if(LOCAL) {
                System.out.print("Line #"+Thread.currentThread().getStackTrace()[2].getLineNumber()+": [");
                for(int i = 0; i<o.length; i++) {
                    if(i!=0) System.out.print(", ");
                    System.out.print(ts(o[i]));
                }
                System.out.println("]");
            }
        }
    }
}