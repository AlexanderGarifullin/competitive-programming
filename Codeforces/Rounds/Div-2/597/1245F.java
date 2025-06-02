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

    static long work(int n, int m) {
        if (n < 0 || m < 0) {
            return 0L;
        }
        long[][][] dp = new long[2][2][2];
        int c = 0;
        dp[c][0][0] = 1L;

        for (int i = 30; i >= 0; --i) {
            c ^= 1;
            dp[c][0][0] = dp[c][0][1] = dp[c][1][0] = dp[c][1][1] = 0L;

            int bitN = (n >> i) & 1;
            int bitM = (m >> i) & 1;

            for (int flA = 0; flA <= 1; ++flA) {
                for (int flB = 0; flB <= 1; ++flB) {
                    long ways = dp[c ^ 1][flA][flB];
                    if (ways == 0L) continue;

                    for (int x = 0; x <= 1; ++x) {
                        for (int y = 0; x + y <= 1; ++y) {
                            if (flA == 0 && x > bitN) continue;
                            if (flB == 0 && y > bitM) continue;

                            int nFlA = flA;
                            int nFlB = flB;
                            if (flA == 0 && x < bitN) {
                                nFlA = 1;
                            }
                            if (flB == 0 && y < bitM) {
                                nFlB = 1;
                            }
                            dp[c][nFlA][nFlB] += ways;
                        }
                    }
                }
            }
        }
        
        return dp[c][1][1];
    }
    static void solve() throws IOException {
        int l = cin.nextInt();
        int r = cin.nextInt();
        long total = work(r + 1, r + 1);
        long sub  = work(l, r + 1);
        long sub2 = work(l, l);
        long ans = total - 2L * sub + sub2;
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
