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

    static final int MAXN = 200_000 + 5;
    static final int MOD = 998244353;
    static long[] vv = new long[MAXN], gg = new long[MAXN];
    static long[] b = new long[MAXN];
    static long[] cur = new long[64];

    static void init() {
        vv[1] = 1;
        for (int i = 2; i < MAXN; i++) {
            vv[i] = MOD - (MOD / i) * vv[MOD % i] % MOD;
        }
        gg[0] = 1;
        for (int i = 1; i < MAXN; i++) {
            gg[i] = gg[i - 1] * vv[i] % MOD;
        }
    }


    static void solve() throws IOException {
        int n = cin.nextInt();
        long k = cin.nextLong();

        for (int i = 1; i <= n; i++) {
            b[i] = cin.nextLong();
        }

        cur[0] = 1;
        for (int d = 1; d < 64; d++) {
            cur[d] = cur[d - 1] * ((k - 1 + d) % MOD) % MOD;
        }

        for (int i = 1; i <= n; i++) {
            int L = i - (i & -i) + 1;
            for (int j = L; j < i; j++) {
                int d = Integer.bitCount(i - j);
                long coeff = cur[d] * gg[d] % MOD;
                b[i] = (b[i] - b[j] * coeff % MOD + MOD) % MOD;
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(b[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        init();
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
