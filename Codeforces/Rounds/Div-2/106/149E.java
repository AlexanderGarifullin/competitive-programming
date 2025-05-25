import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.BiConsumer;

public class Main {
    private static final class FastScanner {
        private final byte[] buf = new byte[1 << 16];
        private int len = 0, ptr = 0;
        private final InputStream in;

        FastScanner(InputStream in) {
            this.in = in;
        }

        private int readByte() throws java.io.IOException {
            if (ptr >= len) {
                len = in.read(buf);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buf[ptr++];
        }

        int nextInt() throws java.io.IOException {
            int c, sign = 1, val = 0;
            do { c = readByte(); } while (c <= ' ' && c != -1);
            if (c == '-') { sign = -1; c = readByte(); }
            while (c > ' ' && c != -1) {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }

        long nextLong() throws java.io.IOException {
            long c, sign = 1, val = 0;
            do { c = readByte(); } while (c <= ' ' && c != -1);
            if (c == '-') { sign = -1; c = readByte(); }
            while (c > ' ' && c != -1) {
                val = val * 10 + (c - '0');
                c = readByte();
            }
            return val * sign;
        }

        String next() throws java.io.IOException {
            int c;
            do {
                c = readByte();
            } while (c <= ' ' && c != -1);

            if (c == -1) return null;

            StringBuilder sb = new StringBuilder();
            while (c > ' ' && c != -1) {
                sb.append((char) c);
                c = readByte();
            }
            return sb.toString();
        }
    }

    static FastScanner cin = new FastScanner(System.in);

    public static <T extends Comparable<? super T>> boolean isSorted(List<T> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i-1).compareTo(list.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    public static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static long lcm(long a, long b) {
        return a * b / gcd(a,b);
    }

    static long modPow(long base, int exp, int mod) {
        long result = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1)
                result = result * base % mod;
            base = base * base % mod;
            exp >>= 1;
        }
        return result;
    }


    static void solve() throws IOException {
        String s = cin.next();
        int n = s.length();
        int q = cin.nextInt();

        int result = 0;

        while (q-- > 0) {
            String t = cin.next();
            int m = t.length();

            if (m < 2) continue;

            int[] fl = buildPrefixFunction(t);
            int[] fr = buildSuffixFunction(t);

            int[] l = new int[n];
            int curr = 0;

            for (int i = 0; i < n; i++) {
                while (curr > 0 && s.charAt(i) != t.charAt(curr)) {
                    curr = fl[curr];
                }
                if (s.charAt(i) == t.charAt(curr)) {
                    curr++;
                }
                l[i] = curr;
                if (curr == m) curr = fl[curr];
            }

            int[] r = new int[n];
            curr = 0;

            for (int i = n - 1; i >= 0; i--) {
                while (curr > 0 && s.charAt(i) != t.charAt(m - curr - 1)) {
                    curr = fr[curr];
                }
                if (s.charAt(i) == t.charAt(m - curr - 1)) {
                    curr++;
                }
                r[i] = curr;
                if (curr == m) curr = fr[curr];
            }

            int maxL = 0;
            boolean ok = false;

            for (int i = 0; i < n; i++) {
                if (maxL + r[i] >= m) {
                    ok = true;
                    break;
                }
                maxL = Math.max(maxL, l[i]);
            }

            if (ok) result++;
        }

        System.out.println(result);
    }

    private static int[] buildPrefixFunction(String t) {
        int m = t.length();
        int[] fl = new int[m + 1];
        int curr = 0;

        for (int i = 1; i < m; i++) {
            while (curr > 0 && t.charAt(i) != t.charAt(curr)) {
                curr = fl[curr];
            }
            if (t.charAt(i) == t.charAt(curr)) {
                curr++;
            }
            fl[i + 1] = curr;
        }

        return fl;
    }

    private static int[] buildSuffixFunction(String t) {
        int m = t.length();
        int[] fr = new int[m + 1];
        int curr = 0;

        for (int i = m - 2; i >= 0; i--) {
            while (curr > 0 && t.charAt(i) != t.charAt(m - curr - 1)) {
                curr = fr[curr];
            }
            if (t.charAt(i) == t.charAt(m - curr - 1)) {
                curr++;
            }
            fr[m - i] = curr;
        }

        return fr;
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
