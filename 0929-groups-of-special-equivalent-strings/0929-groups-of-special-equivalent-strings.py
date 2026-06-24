class Solution:
	def numSpecialEquivGroups(self, A):
		mp = collections.defaultdict(int)
		for word in A:
			even = ''.join(sorted(word[0::2]))
			odd = ''.join(sorted(word[1::2]))
			mp[(even, odd)] += 1
		
		return len(mp)