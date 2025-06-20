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

    static final int MAXN = 200_005;
    static int[] ind = new int[MAXN];

    static void solve() throws IOException {
        int n = cin.nextInt();
        int[] l = new int[n];
        int[] r = new int[n];
        List<Integer> coords = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            l[i] = cin.nextInt();
            r[i] = cin.nextInt();
            coords.add(l[i]);
            coords.add(r[i]);
        }

        List<Integer> x = new ArrayList<>(new TreeSet<>(coords));
        int cnt = x.size();
        Map<Integer, Integer> coordMap = new HashMap<>();
        for (int i = 0; i < cnt; i++) {
            coordMap.put(x.get(i), i);
            ind[x.get(i)] = i;
        }

        List<List<Integer>> sl = new ArrayList<>();
        for (int i = 0; i <= cnt + 4; i++) sl.add(new ArrayList<>());

        for (int i = 0; i < n; i++) {
            l[i] = coordMap.get(l[i]);
            r[i] = coordMap.get(r[i]);
            sl.get(l[i]).add(r[i]);
        }

        for (int i = 0; i <= cnt; i++) {
            Collections.sort(sl.get(i));
        }

        cnt++;
        int[][] dp = new int[cnt + 3][cnt + 3];

        for (int len = 1; len <= cnt; len++) {
            for (int j = 0; j <= cnt - len + 1; j++) {
                dp[j][len] = dp[j + 1][len - 1];
                for (int rr : sl.get(j)) {
                    if (rr > j + len - 1) break;
                    if (rr == j + len - 1) {
                        dp[j][len]++;
                    } else {
                        int leftLen = rr - j + 1;
                        int rightLen = len - leftLen;
                        dp[j][len] = Math.max(dp[j][len], dp[j][leftLen] + dp[rr + 1][rightLen]);
                    }
                }
            }
        }

        System.out.println(dp[0][cnt]);
    }

    public static void main(String[] args) throws IOException {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
