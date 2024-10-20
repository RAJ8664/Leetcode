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

    public static List<String> stringSequence(String target) {
     	List<String> res = new ArrayList<>();
     	int n = target.length();
     	StringBuilder current = new StringBuilder();
     	for (int i = 0; i < n; i++) {
     		char req = target.charAt(i);
     		current.append("a");
     		for (char j = 'b'; j <= 'z' + 1; j++) {
     			if (current.charAt(current.length() - 1) == req) {
     				res.add(current.toString());
     				break;
     			}
     			else {
     				res.add(current.toString());
     				current.setCharAt(current.length() - 1 , j);
     			}
     			
     		}
     	} 
     	return res;  
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
       	String target = sc.next();
       	List<String> res = stringSequence(target);
       	for (String x : res) out.print(x + " ");
       	out.println();
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