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

    static class Fenwick {
        int n, lg;
        int[] a;

        Fenwick(int n) {
            this.n = n;
            this.lg = 31 - Integer.numberOfLeadingZeros(n);
            this.a = new int[n];
        }

        void add(int x, int v) {
            for (int i = x + 1; i <= n; i += i & -i) {
                a[i - 1] += v;
            }
        }

        int select(int k) {
            int x = 0;
            for (int i = lg; i >= 0; --i) {
                if (x + (1 << i) <= n && k >= a[x + (1 << i) - 1]) {
                    x += 1 << i;
                    k -= a[x - 1];
                }
            }
            return (x == n) ? -1 : x;
        }
    }


    static void solve() throws IOException {
        int n = cin.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = cin.nextInt() - 1;
        }

        int[] next = new int[n];
        int[] last = new int[n];
        Arrays.fill(last, -1);

        for (int i = n - 1; i >= 0; i--) {
            next[i] = last[a[i]];
            last[a[i]] = i;
        }

        Fenwick fen = new Fenwick(n);
        for (int i = 0; i < n; i++) {
            if (last[i] != -1) {
                fen.add(last[i], 1);
            }
        }

        int[] ans = new int[n];
        List<List<Integer>> f = new ArrayList<>();
        for (int i = 0; i < n; i++) f.add(new ArrayList<>());
        for (int i = 1; i <= n; i++) {
            f.get(0).add(i);
        }

        for (int i = 0; i < n; i++) {
            for (int k : f.get(i)) {
                ans[k - 1]++;
                int x = fen.select(k);
                if (x != -1) {
                    f.get(x).add(k);
                }
            }
            fen.add(i, -1);
            if (next[i] != -1) {
                fen.add(next[i], 1);
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(ans[i]);
            System.out.print(i == n - 1 ? "\n" : " ");
        }
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
