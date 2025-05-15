import java.io.IOException;
import java.io.InputStream;
import java.util.*;

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
    private static final int MAX_NODES = 4_100_000;

    private static final int[] left  = new int[MAX_NODES];
    private static final int[] right = new int[MAX_NODES];

    private static int ptr;

    private static void resetTrie() {
        ptr = 1;
        left[1] = right[1] = 0;
    }

    private static void add(int v) {
        int cur = 1;
        for (int bit = 18; bit >= 0; --bit) {
            int b = (v >> bit) & 1;
            int next = (b == 0 ? left[cur] : right[cur]);
            if (next == 0) {
                next = ++ptr;
                left[next] = right[next] = 0;
                if (b == 0) left[cur] = next; else right[cur] = next;
            }
            cur = next;
        }
    }

    private static int getMin(int x) {
        int cur = 1, res = 0;
        for (int bit = 18; bit >= 0; --bit) {
            int b = (x >> bit) & 1;
            int next = (b == 0 ? left[cur] : right[cur]);
            if (next == 0) {
                res |= 1 << bit;
                next = (b == 0 ? right[cur] : left[cur]);
            }
            cur = next;
        }
        return res;
    }

    private static int getMax(int x) {
        int cur = 1, res = 0;
        for (int bit = 18; bit >= 0; --bit) {
            int b = (x >> bit) & 1;
            int wantOpp = (b ^ 1);
            int nextOpp = (wantOpp == 0 ? left[cur] : right[cur]);
            if (nextOpp != 0) {
                res |= 1 << bit;
                cur = nextOpp;
            } else {
                cur = (b == 0 ? left[cur] : right[cur]);
            }
        }
        return res;
    }


    static void solve() throws IOException {
        int l = cin.nextInt();
        int r = cin.nextInt();
        int n = r -l + 1;
        int [] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = cin.nextInt();
        }
        resetTrie();
        for (int v : a) add(v);

        for (int i = l; i <= r; ++i) {
            int x  = a[0] ^ i;
            int mn = getMin(x);
            int mx = getMax(x);
            if (mn == l && mx == r) {
                System.out.println(x);
                return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
