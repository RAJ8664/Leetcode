class Solution {
    public String decodeString(String s) {
        int n = s.length();
        Stack<Integer> dig = new Stack<>();
        Stack<Character> ch = new Stack<>();
        boolean prev = false;
        int num = 0;
        for (int i = 0; i < n; i++) {
            char current = s.charAt(i);
            if (Character.isDigit(current)) {
                num = num * 10 + (int)(current - '0');
            }
            else {
                prev = false;
                if (num > 0) dig.add(num);
                num = 0;
                if (current == ']') {
                    String current_string = "";
                    while (ch.size() > 0 && ch.peek() != '[') {
                        current_string += ch.peek();
                        ch.pop();
                    }
                    if (ch.size() > 0 && ch.peek() == '[') ch.pop();
                    StringBuilder temp = new StringBuilder(current_string);
                    String new_string = temp.reverse().toString();
                    String to_add = "";
                    for (int j = 0; j < dig.peek(); j++) {
                        to_add += new_string;
                    }
                    dig.pop();
                    for (int j = 0; j < to_add.length(); j++) ch.add(to_add.charAt(j));
                }
                else ch.add(current);
            }
        }
        String res = "";
        while (ch.size() > 0) res += ch.pop();
        StringBuilder answer = new StringBuilder(res);
        return answer.reverse().toString();
    }
}