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

    private static final int ALH = 26;

    static void solve() throws IOException {
        String s = cin.next();
        String t = cin.next();

        int n = s.length();
        int m = t.length();

        int[] f = new int[m + 1];
        int cur = 0;
        for (int i = 1; i < m; i++) {
            while (cur > 0 && t.charAt(cur) != t.charAt(i)) cur = f[cur];
            if (t.charAt(cur) == t.charAt(i)) cur++;
            f[i + 1] = cur;
        }

        int[] dp  = new int[n + 1];
        int[] res = new int[n + 1];
        Arrays.fill(res, -1);
        res[0] = 0;

        for (int i = 0; i < n; i++) {
            dp[i + 1] = dp[i];
            res[i + 1] = -1;

            if (i >= m - 1) {
                boolean ok = true;
                for (int j = 0; j < m && ok; j++) {
                    char sc = s.charAt(i - j);
                    char tc = t.charAt(m - 1 - j);
                    if (sc != '?' && sc != tc) ok = false;
                }

                if (ok) {
                    int tmp = m;
                    while (f[tmp] != 0) {
                        tmp = f[tmp];
                        int idx = i - (m - tmp) + 1;
                        if (res[idx] != -1)
                            res[i + 1] = Math.max(res[i + 1], res[idx] + 1);
                    }
                    res[i + 1] = Math.max(res[i + 1], dp[i - m + 1] + 1);
                }
                dp[i + 1] = Math.max(dp[i + 1], res[i + 1]);
            }
        }

        System.out.println(dp[n]);
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
