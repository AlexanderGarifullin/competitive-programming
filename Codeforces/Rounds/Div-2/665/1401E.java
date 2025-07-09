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

    static final int N = (int) 1e6;
    static int[] fen = new int[N + 2];

    static void add(int x, int v) {
        for (int i = x + 1; i <= N + 1; i += i & -i)
            fen[i - 1] += v;
    }

    static int sum(int x) {
        int res = 0;
        for (int i = x; i > 0; i &= i - 1)
            res += fen[i - 1];
        return res;
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        int m = cin.nextInt();

        long ans = 1;
        List<int[]> a = new ArrayList<>();
        List<int[]> b = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int y = cin.nextInt();
            int l = cin.nextInt();
            int r = cin.nextInt();

            if (l == 0 && r == N) ans++;

            a.add(new int[]{l, y, 1});
            a.add(new int[]{r + 1, y, -1});
        }

        for (int i = 0; i < m; i++) {
            int x = cin.nextInt();
            int l = cin.nextInt();
            int r = cin.nextInt();

            if (l == 0 && r == N) ans++;

            b.add(new int[]{x, l, r});
        }

        a.sort(Comparator.comparingInt(o -> o[0]));
        b.sort(Comparator.comparingInt(o -> o[0]));

        int i = 0;
        for (int[] vertical : b) {
            int x = vertical[0], l = vertical[1], r = vertical[2];

            while (i < a.size() && a.get(i)[0] <= x) {
                int[] event = a.get(i++);
                int y = event[1];
                int v = event[2];
                add(y, v);
            }

            ans += sum(r + 1) - sum(l);
        }

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
