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

    static final int MAX_N = 55;
    static final int MAX_M = 20005;

    static int[][] animal = new int[MAX_N][MAX_M];
    static int[][] psum = new int[MAX_N][MAX_M];
    static int[][] dp = new int[MAX_N][MAX_M];
    static int[][] lmax = new int[MAX_N][MAX_M];
    static int[][] rmax = new int[MAX_N][MAX_M];

    static int ps(int i, int j, int k) {
        return psum[i][k] - psum[i][j - 1];
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        int m = cin.nextInt();
        int k = cin.nextInt();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                animal[i][j] = cin.nextInt();
                psum[i][j] = psum[i][j - 1] + animal[i][j];
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m - k + 1; j++) {
                int p = ps(i, j, j + k - 1);
                if (i + 1 <= n) p += ps(i + 1, j, j + k - 1); // на следующий день

                if (i == 1) {
                    dp[i][j] = p;
                    continue;
                }

                int maxOverlap = 0;
                for (int x = Math.max(1, j - k + 1); x <= Math.min(m - k + 1, j + k - 1); x++) {
                    int overlap = ps(i, Math.max(x, j), Math.min(x + k - 1, j + k - 1));
                    maxOverlap = Math.max(maxOverlap, dp[i - 1][x] + p - overlap);
                }

                dp[i][j] = maxOverlap;

                if (j > k)
                    dp[i][j] = Math.max(dp[i][j], lmax[i - 1][j - k] + p);
                if (j + k - 1 <= m - k)
                    dp[i][j] = Math.max(dp[i][j], rmax[i - 1][j + k] + p);
            }

            for (int j = 1; j <= m - k + 1; j++) {
                lmax[i][j] = Math.max(lmax[i][j - 1], dp[i][j]);
            }
            for (int j = m - k + 1; j >= 1; j--) {
                rmax[i][j] = Math.max(rmax[i][j + 1], dp[i][j]);
            }
        }

        int result = 0;
        for (int j = 1; j <= m; j++) {
            result = Math.max(result, dp[n][j]);
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
