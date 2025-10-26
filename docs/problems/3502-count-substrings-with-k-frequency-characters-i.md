# 3502. Count Substrings With K Frequency Characters I


---

<h2><a href="https://leetcode.com/problems/count-substrings-with-k-frequency-characters-i">3502. Count Substrings With K-Frequency Characters I</a></h2><h3>Medium</h3><hr><p>Given a string <code>s</code> and an integer <code>k</code>, return the total number of <span data-keyword="substring-nonempty">substrings</span> of <code>s</code> where <strong>at least one</strong> character appears <strong>at least</strong> <code>k</code> times.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abacb&quot;, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>The valid substrings are:</p>

<ul>
	<li><code>&quot;aba&quot;</code> (character <code>&#39;a&#39;</code> appears 2 times).</li>
	<li><code>&quot;abac&quot;</code> (character <code>&#39;a&#39;</code> appears 2 times).</li>
	<li><code>&quot;abacb&quot;</code> (character <code>&#39;a&#39;</code> appears 2 times).</li>
	<li><code>&quot;bacb&quot;</code> (character <code>&#39;b&#39;</code> appears 2 times).</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;abcde&quot;, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">15</span></p>

<p><strong>Explanation:</strong></p>

<p>All substrings are valid because every character appears at least once.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3000</code></li>
	<li><code>1 &lt;= k &lt;= s.length</code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>


## Solution

```java
import java.util.*;
import java.io.*;
import java.math.*;
import static java.lang.Math.*;

class Solution {
	static Reader sc = new Reader();
    static PrintWriter out = new PrintWriter(System.out);
    static Debug dbg = new Debug();
    static int mod = (int) (1000000007); //998244353 1000000007;
    static long hash_mod = 92233720368547753L;

    public static int numberOfSubstrings(String s, int k) {
        int n = s.length(), count = 0;
        for (int i = 0; i < n; i++) {
        	HashMap<Character, Integer> map = new HashMap<>();
        	int maxi = 0;
        	for (int j = i; j < n; j++) {
        		char current = s.charAt(j);
        		map.put(current, map.getOrDefault(current, 0) + 1);
        		maxi = max(maxi, map.get(current));
        		if (maxi >= k) count++;
        	}
        	map.clear();
        }
        return count;
    }
    
	public static void main(String[] args) throws IOException {
        READING(); /*→→→[■□□□□□□□□□] →→→ [■■■■□□□□□□] →→→  [■■■■■■■□□□] →→→ [■■■■■■■■■□]*/ ERROR();
        //preprocess();
        int t = 1;
        //int t = sc.nextInt();
        while (t-->0) Attack();
        sc.close();
        out.flush();
    }

    public static void Attack() throws IOException {
       	String s = sc.next();
       	int k = sc.nextInt();
       	int res = numberOfSubstrings(s, k);
       	out.println(res);
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        BufferedReader reader;
        private byte[] buffer;
        private int bufferPointer, bytesRead;
        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }
        public String readLine() throws IOException {
            byte[] buf = new byte[64];
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n') break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }
        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {ret = ret * 10 + c - '0';}
            while ((c = read()) >= '0' && c <= '9');
            if (neg) return -ret;
            return ret;
        }
        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {ret = ret * 10L + c - '0';}
            while ((c = read()) >= '0' && c <= '9');
            if (neg) return -ret;
            return ret;
        }
        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {ret = ret * 10 + c - '0';}
            while ((c = read()) >= '0' && c <= '9');
            if (c == '.') while ((c = read()) >= '0' && c <= '9') ret += (c - '0') / (div *= 10);
            if (neg) return -ret;
            return ret;
        }
        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) buffer[0] = -1;
        }
        private byte read() throws IOException {
            if (bufferPointer == bytesRead) fillBuffer();
            return buffer[bufferPointer++];
        }
        public String next() throws IOException {
            StringBuilder sb = new StringBuilder();
            byte c;
            while ((c = read()) <= ' ') ;
            do {sb.append((char) c);}
            while ((c = read()) > ' ');
            return sb.toString();
        }
        public int nextInt2() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') c = read();
            boolean neg = (c == '-');
            if (neg) c = read();
            do {ret = ret * 10 + c - '0';}
            while ((c = read()) >= '0' && c <= '9');
            if (neg) return -ret;
            return ret;
        }
        public void close() throws IOException {
            if (din == null) return;
            din.close();
        }
    }
    public static void READING(){if(System.getProperty("ONLINE_JUDGE") == null){try{sc = new Reader("input.txt");out = new PrintWriter("output.txt");}catch (Exception e){}}}
    public static void ERROR() {try {PrintStream fileOut = new PrintStream(new FileOutputStream("dbg.txt", false), true, "UTF-8");System.setErr(fileOut);} catch (FileNotFoundException | UnsupportedEncodingException e) {e.printStackTrace();}}
    static class Debug {
        public static boolean LOCAL = getLocal();
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
                System.err.print("Line #"+Thread.currentThread().getStackTrace()[2].getLineNumber()+": [");
                for(int i = 0; i<o.length; i++) {
                    if(i!=0) System.err.print(", ");
                    System.err.print(ts(o[i]));
                }
                System.err.println("]");
            }
        }
    }
}
```

## Complexity Analysis

- **Time Complexity**: O(?)
- **Space Complexity**: O(?)

## Explanation

[Add detailed explanation here]

