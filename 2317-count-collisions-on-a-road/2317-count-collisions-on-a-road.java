class Solution {
    public int countCollisions(String directions) {
        Stack<Character> stack = new Stack<>();
        int count = 0;
        for(char direction : directions.toCharArray()){
            if(!stack.isEmpty() && direction == 'L' && stack.peek() != 'L'){ 
                int prev = stack.pop();
                count++;
                if(prev == 'R')
                    count++;
                while(!stack.isEmpty() && stack.peek() == 'R'){
                    count++;
                    stack.pop();
                }
                stack.push('S');
            }
            else if(!stack.isEmpty() && direction == 'S' && stack.peek() == 'R'){
                while(!stack.isEmpty() && stack.peek() == 'R'){
                    count++;
                    stack.pop();
                }
                stack.push('S');
            }
            else{
                stack.push(direction);
            }
        }
        return count;
    }
}