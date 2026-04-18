# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def add_node(self, head: Optional[ListNode], val: int) -> Optional[ListNode]:
        temp = ListNode(val)
        temp.next = head
        head = temp
        return head 
    
    def reverseKGroup(self, head: Optional[ListNode], k: int) -> Optional[ListNode]:
        nodes = []
        dummy = head
        res: Optional[ListNode] = None
        
        while dummy:
            nodes.append(dummy.val)
            dummy = dummy.next
        
        idx = 0 
        while idx < len(nodes):
            if idx + k - 1 >= len(nodes): break
            nodes[idx:idx + k] = nodes[idx:idx + k][::-1]
            idx += k

        for i in range(len(nodes) - 1, -1, -1):
            res = self.add_node(res, nodes[i])
        
        return res
        