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

    static final int MOD = 998244353;

    static long modInv(long x) {
        return modPow(x, MOD - 2, MOD);
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        int m = cin.nextInt();

        int[][] a = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = cin.nextInt();
            }
        }

        int startR = cin.nextInt() - 1;
        int startC = cin.nextInt() - 1;

        class Cell { int i, j, v; }
        List<Cell> cells = new ArrayList<>(n * m);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                Cell c = new Cell();
                c.i = i; c.j = j; c.v = a[i][j];
                cells.add(c);
            }

        cells.sort(Comparator.comparingInt(c -> c.v));

        long[][] dp = new long[n][m];

        long Sdp = 0, Sr = 0, Sc = 0, Srr = 0, Scc = 0;
        int count = 0;

        int idx = 0;
        while (idx < cells.size()) {
            int curVal = cells.get(idx).v;
            int jdx = idx;
            while (jdx < cells.size() && cells.get(jdx).v == curVal) jdx++;


            for (int t = idx; t < jdx; t++) {
                Cell c = cells.get(t);
                int i = c.i, j = c.j;
                if (count == 0) {
                    dp[i][j] = 0;
                } else {

                    long term = (Sdp + Srr + Scc) % MOD;
                    term = (term - 2L * i % MOD * Sr % MOD + MOD) % MOD;
                    term = (term - 2L * j % MOD * Sc % MOD + MOD) % MOD;
                    long invCnt = modInv(count);
                    dp[i][j] = (term * invCnt % MOD + (1L * i * i + 1L * j * j) % MOD) % MOD;
                }
            }

            for (int t = idx; t < jdx; t++) {
                Cell c = cells.get(t);
                int i = c.i, j = c.j;
                Sdp = (Sdp + dp[i][j]) % MOD;
                Sr  = (Sr  + i)      % MOD;
                Sc  = (Sc  + j)      % MOD;
                Srr = (Srr + 1L * i * i) % MOD;
                Scc = (Scc + 1L * j * j) % MOD;
                count++;
            }
            idx = jdx;
        }

        System.out.println(dp[startR][startC]);
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
