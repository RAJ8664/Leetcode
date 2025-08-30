class LRUCache {
    static class Node {
        Node prev, next;
        int val;
        public Node(int val) {
            this.val = val;
            prev = null;
            next = null;
        }
    }
    private Node head;
    private Node tail;
    private int totalNodes;
    private int n;
    private HashMap<Integer, Integer> map;
    private HashMap<Integer, Node> nodeMap;
    public LRUCache(int capacity) {
        this.head = null;
        this.tail = null; 
        this.totalNodes = 0;  
        this.n = capacity;
        map = new HashMap<>();
        nodeMap = new HashMap<>(); 
    }
    public int get(int key) {
        if (map.containsKey(key)) {
            int res = map.get(key);
            deletePrevRef(key);
            addFront(key);
            return res;
        }
        return -1; 
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            deletePrevRef(key);
            addFront(key);
            map.remove(key);
            map.put(key, value); 
        } 
        
        else if (totalNodes == n) {
            /* I need to delete something */
            int deletedKey = deleteLast();
            map.remove(deletedKey);

            map.put(key, value);
            addFront(key);   
        }  
        else {
            /* I want to make a node of this value */
            if (map.containsKey(key)) {
                map.remove(key);
                deletePrevRef(key);

                map.put(key, value);
                addFront(key);  
            }
            else {
                map.put(key, value);
                addFront(key);
            }
        }
    }
    private int deleteLast() {
        totalNodes--;
        int ans = tail.val;
        nodeMap.remove(ans);
        if (totalNodes == 0) {
            head = null;
            tail = null;
        }
        else {
            tail = tail.prev;
            tail.next = null;
        }
        return ans;
    }
    private void deletePrevRef(int key) {
        totalNodes--;
        if (totalNodes == 0) {
            head = null;
            tail = null;
            return;
        }
        Node refNode = nodeMap.get(key);
        if (refNode.prev == null) {
            /* want to delete the head node */
            head = refNode.next;
            head.prev = null;
            return;
        }
        if (refNode.next == null) {
            /* want to delete the tail node*/
            tail = tail.prev;
            tail.next = null;     
        }
        else {
            refNode.prev.next = refNode.next;
            refNode.next.prev = refNode.prev; 
        }
    }
    private void deletePrev(int key) {
        totalNodes--;
        if (totalNodes == 0) {
            head = null;
            tail = null;
            return;
        }
        Node temp = head;
        if (temp.val == key) {
            head = temp.next;
            head.prev = null;
            return;   
        }
        while (temp != null) {
            if (temp.val == key) {
                if (temp.next == null) {
                    tail = tail.prev;
                    tail.next = null;     
                }
                else {
                    temp.prev.next = temp.next;
                    temp.next.prev = temp.prev;
                }
            }
            temp = temp.next;
        }         
    } 
    private void addFront(int val) {
        if (head == null) {
            head = new Node(val);
            tail = head;
            nodeMap.put(val, head);
        }
        else {
            Node newNode = new Node(val);
            head.prev = newNode;
            newNode.next = head;
            head = newNode;
            head.prev = null;
            nodeMap.put(val, head);
        }
        totalNodes++;
    }
    private void print() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */