import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.*;
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

    static class Lady implements Comparable<Lady> {
        int beauty, intellect, richness;

        Lady(int b, int i, int r) {
            this.beauty = b;
            this.intellect = i;
            this.richness = r;
        }

        @Override
        public int compareTo(Lady other) {
            return Integer.compare(this.beauty, other.beauty);
        }
    }

    static class SegmentTree {
        int[] tree;
        int size;

        SegmentTree(int n) {
            size = 1;
            while (size < n) size <<= 1;
            tree = new int[size << 1];
        }

        void update(int idx, int value) {
            idx += size;
            tree[idx] = Math.max(tree[idx], value);
            while (idx > 1) {
                idx >>= 1;
                tree[idx] = Math.max(tree[idx << 1], tree[(idx << 1) | 1]);
            }
        }

        int query(int l, int r) {
            l += size;
            r += size;
            int res = 0;
            while (l < r) {
                if ((l & 1) == 1) res = Math.max(res, tree[l++]);
                if ((r & 1) == 1) res = Math.max(res, tree[--r]);
                l >>= 1;
                r >>= 1;
            }
            return res;
        }
    }


    static void solve() throws IOException {
        int n = cin.nextInt();

        Lady[] ladies = new Lady[n];
        int[] intellects = new int[n];

        for (int i = 0; i < n; i++) {
            int b = cin.nextInt();
            ladies[i] = new Lady(b, 0, 0);
        }

        for (int i = 0; i < n; i++) {
            ladies[i].intellect = cin.nextInt();
            intellects[i] = ladies[i].intellect;
        }

        for (int i = 0; i < n; i++) {
            ladies[i].richness = cin.nextInt();
        }
        
        Arrays.sort(intellects);
        Map<Integer, Integer> compressed = new HashMap<>();
        for (int i = 0; i < n; i++) {
            compressed.put(intellects[i], i);
        }

        for (int i = 0; i < n; i++) {
            ladies[i].intellect = compressed.get(ladies[i].intellect);
        }

        Arrays.sort(ladies);

        SegmentTree seg = new SegmentTree(n);
        int ans = 0;
        int last = n - 1;

        for (int i = n - 1; i >= 0; i--) {
            if (seg.query(ladies[i].intellect + 1, n) > ladies[i].richness) {
                ans++;
            }

            if (i > 0 && ladies[i].beauty != ladies[i - 1].beauty) {
                for (; last >= i; last--) {
                    seg.update(ladies[last].intellect, ladies[last].richness);
                }
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
