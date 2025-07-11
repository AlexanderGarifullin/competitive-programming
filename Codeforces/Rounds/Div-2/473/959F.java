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

    static final int MOD = 1_000_000_007;
    static class Pair<F, S> {
        F first;
        S second;
        Pair(F f, S s) {
            first = f;
            second = s;
        }
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        int q = cin.nextInt();

        int[] a = new int[n];
        for (int i = 0; i < n; ++i) {
            a[i] = cin.nextInt();
        }

        List<Pair<Integer, Integer>>[] queries = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            queries[i] = new ArrayList<>();
        }

        int[] ans = new int[q];
        for (int t = 0; t < q; ++t) {
            int l = cin.nextInt();
            int x = cin.nextInt();
            queries[l - 1].add(new Pair<>(x, t));
        }

        List<Integer> basis = new ArrayList<>();
        int cnt = 1;

        for (int i = 0; i < n; ++i) {
            for (int x : basis) {
                a[i] = Math.min(a[i], a[i] ^ x);
            }

            if (a[i] > 0) {
                basis.add(a[i]);
            } else {
                cnt = (cnt * 2) % MOD;
            }

            for (Pair<Integer, Integer> query : queries[i]) {
                int x = query.first;
                for (int y : basis) {
                    x = Math.min(x, x ^ y);
                }
                ans[query.second] = (x == 0) ? cnt : 0;
            }
        }

        for (int val : ans) {
            System.out.println(val);
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
