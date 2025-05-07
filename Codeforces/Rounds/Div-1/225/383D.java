import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
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


    static class Pair<F,S> {
        F first;
        S second;

        Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        static <F,S> Pair<F,S> of(F first, S second) {
            return new Pair<>(first, second);
        }
    }
    private static int n;
    private static int [] a;

   private static final long MOD = (int)1e9 + 7;
   private static final int SHIFT = 15000 + 10;
   private static final int MX = SHIFT * 2 + 10;

    static long solve(int l, int r)  {
        if (l + 1 == r) return 0;
        int mid = (l + r) >> 1;
        long res = (solve(l, mid) + solve(mid, r)) % MOD;

        int segSum = 0;
        for (int i = l; i < r; i++) {
            segSum += a[i];
        }

        int lo = SHIFT - segSum;
        int hi = SHIFT + segSum;

        long [] t1 = new long[MX];
        long [] t2 = new long[MX];
        long [] tmp = new long[MX];
        long [] res1 = new long[MX];
        long [] res2 = new long[MX];

        t1[SHIFT] = 1;
        for (int i = mid - 1; i >= l; --i) {

            for (int s = lo; s <= hi; ++s) {
                long v1 = (s - a[i] >= 0) ? t1[s - a[i]] : 0;
                long v2 = (s + a[i] < MX) ? t1[s + a[i]] : 0;
                tmp[s] = (v1 + v2) % MOD;
            }
            for (int s = lo; s <= hi; ++s) {
                t1[s]   = tmp[s];
                res1[s] = (res1[s] + tmp[s]) % MOD;
            }
        }

        t2[SHIFT] = 1;
        for (int i = mid; i < r; ++i) {

            for (int s = lo; s <= hi; ++s) {
                long v1 = (s - a[i] >= 0) ? t2[s - a[i]] : 0;
                long v2 = (s + a[i] < MX) ? t2[s + a[i]] : 0;
                tmp[s] = (v1 + v2) % MOD;
            }
            for (int s = lo; s <= hi; ++s) {
                t2[s]   = tmp[s];
                res2[s] = (res2[s] + tmp[s]) % MOD;
            }
        }

        for (int s = lo; s <= hi; ++s) {
            res = (res + res1[s] * res2[2 * SHIFT - s]) % MOD;
        }

        return res;
    }

    static void solve() throws IOException {
        n = cin.nextInt();
        a = new int [n];
        for (int i = 0; i < n; i++) {
            a[i] = cin.nextInt();
        }
        System.out.println(solve(0, n));
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
