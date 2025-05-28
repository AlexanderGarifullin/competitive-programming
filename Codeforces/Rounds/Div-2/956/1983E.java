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

    static final int MOD = 1_000_000_007;

    static void solve() throws IOException {
        int n = cin.nextInt();
        int k = cin.nextInt();
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            v[i] = cin.nextInt();
        }

        int ns = n - k;
        int a = (ns + 1) / 2;
        int b = ns / 2;
        int c = (ns + 2) / 2;
        int d = (ns + 1) / 2;

        long res1 = 0, res2 = 0;

        if (ns > 0) {
            int invNs = modInverse(ns, MOD);
            int invNsPlus1 = modInverse(ns + 1, MOD);

            for (int i = 0; i < k; i++) {
                res1 = (res1 + 1L * v[i] * c % MOD * invNsPlus1 % MOD) % MOD;
                res2 = (res2 + 1L * v[i] * d % MOD * invNsPlus1 % MOD) % MOD;
            }

            for (int i = k; i < n; i++) {
                res1 = (res1 + 1L * v[i] * a % MOD * invNs % MOD) % MOD;
                res2 = (res2 + 1L * v[i] * b % MOD * invNs % MOD) % MOD;
            }
        } else {
            for (int i = 0; i < n; i++) {
                res1 = (res1 + v[i]) % MOD;
            }
        }
        System.out.println(res1 + " " + res2);
    }

    static int modInverse(int a, int mod) {
        return (int) modPow(a, mod - 2, mod);
    }

    public static void main(String[] args) throws IOException {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
