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

    static class Pair {
        long x;
        int y;
        Pair(long x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static final int N = 20;
    static final int M = 1 << 10;
    static final int MOD = 998244353;

    static int k;
    static int[] pw10 = new int[N];
    static int[] bitCnt = new int[M];

    static Pair[][][] dp = new Pair[N][M][2];

    static int add(int a, int b) {
        a += b;
        if (a >= MOD) a -= MOD;
        if (a < 0) a += MOD;
        return a;
    }

    static int mul(int a, int b) {
        return (int) ((long) a * b % MOD);
    }

    static int calc(String s) {
        int len = s.length();
        for (int i = 0; i <= len; i++) {
            for (int j = 0; j < M; j++) {
                dp[i][j][0] = new Pair(0L, 0);
                dp[i][j][1] = new Pair(0L, 0);
            }
        }

        dp[0][0][1] = new Pair(1L, 0);

        for (int i = 0; i < len; i++) {
            int cdig = s.charAt(i) - '0';
            for (int mask = 0; mask < M; mask++) {
                for (int tight = 0; tight <= 1; tight++) {
                    Pair cur = dp[i][mask][tight];
                    if (cur.x == 0) continue;
                    int limit = tight == 1 ? cdig : 9;
                    for (int dig = (i == 0 ? 1 : 0); dig <= limit; dig++) {
                        int nmask = mask | (1 << dig);
                        long cnt = cur.x;
                        int sum = add(cur.y, mul(dig, mul(pw10[len - i - 1], (int)(cnt % MOD))));
                        int ntight = (tight == 1 && dig == cdig) ? 1 : 0;
                        Pair nxt = dp[i + 1][nmask][ntight];
                        nxt.x += cnt;
                        nxt.y = add(nxt.y, sum);
                        dp[i + 1][nmask][ntight] = nxt;
                    }
                }
            }
        }

        int ans = 0;
        for (int mask = 0; mask < M; mask++) {
            if (bitCnt[mask] > k) continue;
            ans = add(ans, dp[len][mask][0].y);
            ans = add(ans, dp[len][mask][1].y);
        }
        return ans;
    }

    static int calc(long n) {
        String s = Long.toString(n);
        int len = s.length();
        int ans = 0;
        for (int l = 1; l < len; l++) {
            char[] arr = new char[l];
            Arrays.fill(arr, '9');
            ans = add(ans, calc(new String(arr)));
        }
        ans = add(ans, calc(s));
        return ans;
    }

    static void solve() throws IOException {
        long l = cin.nextLong();
        long r = cin.nextLong();

        k = cin.nextInt();

        pw10[0] = 1;
        for (int i = 1; i < N; i++) {
            pw10[i] = mul(pw10[i - 1], 10);
        }
        for (int i = 0; i < M; i++) {
            bitCnt[i] = Integer.bitCount(i);
        }

        int ans = add(calc(r), -calc(l - 1));
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
