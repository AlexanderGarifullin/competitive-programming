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

    private static final int MAX = 500_000 + 5;

    private static int[] mobius = new int[MAX];
    private static int[] cnt    = new int[MAX];
    private static boolean[] onShelf;
    private static int[][] divisors;
    private static long answer = 0;

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


    static final int MOD = 1_000_000_007;
    static final int MAX_N = 250;

    static int n;
    static long K;
    static int[][] C = new int[MAX_N + 1][MAX_N + 1];
    static int[] pw  = new int[MAX_N + 1];
    static int[] pwk = new int[MAX_N + 1];
    static int[][] dp = new int[2][MAX_N + 1];

    private static void precompute() {
        for (int i = 0; i <= n; ++i) {
            C[i][0] = 1;
            for (int j = 1; j <= i; ++j) {
                C[i][j] = (C[i - 1][j] + C[i - 1][j - 1]) % MOD;
            }
        }

        pw[0] = 1;
        long km1 = (K - 1) % MOD;
        for (int i = 1; i <= n; ++i) {
            pw[i] = (int) ((pw[i - 1] * km1) % MOD);
        }

        pwk[0] = 1;
        long kMod = K % MOD;
        for (int i = 1; i <= n; ++i) {
            pwk[i] = (int) ((pwk[i - 1] * kMod) % MOD);
        }
    }

    static void solve() throws IOException {
        n = cin.nextInt();
        K = cin.nextLong();
        precompute();
        int cur = 0;
        dp[cur][0] = 1;

        for (int row = 0; row < n; ++row) {
            int prev = cur;
            cur ^= 1;
            Arrays.fill(dp[cur], 0);

            for (int closed = 0; closed <= n; ++closed) {
                int waysPrev = dp[prev][closed];
                if (waysPrev == 0) continue;

                for (int closedAfter = closed; closedAfter <= n; ++closedAfter) {
                    int choose = C[n - closed][closedAfter - closed];

                    int multOpenCols = pw[n - closedAfter];

                    int factorClosedCols = (closedAfter == closed)
                            ? ((pwk[closed] - pw[closed] + MOD) % MOD)
                            : pwk[closed];

                    long add = waysPrev;
                    add = (add * choose) % MOD;
                    add = (add * multOpenCols) % MOD;
                    add = (add * factorClosedCols) % MOD;

                    dp[cur][closedAfter] = (int) ((dp[cur][closedAfter] + add) % MOD);
                }
            }
        }

        System.out.println(dp[cur][n]);
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
