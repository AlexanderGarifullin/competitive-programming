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

    static final int N = (int) 1e6 + 5;
    static long[][] f = new long[N][2];
    static int[] a = new int[N];

    static void upd(int i, int j, long val) {
        if (f[i][j] > val) f[i][j] = val;
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        long r1 = cin.nextLong();
        long r2 = cin.nextLong();
        long r3 = cin.nextLong();
        long d = cin.nextLong();



        for (int i = 1; i <= n; ++i) {
            a[i] = cin.nextInt();
        }

        for (int i = 2; i <= n; ++i) {
            f[i][0] = f[i][1] = Long.MAX_VALUE;
        }

        f[1][0] = 1L * r1 * a[1] + r3;
        f[1][1] = Math.min(r2, 1L * r1 * a[1] + r1);

        for (int i = 1; i < n; ++i) {
            // f[i][0] → f[i+1][0]
            upd(i + 1, 0, f[i][0] + d + 1L * r1 * a[i + 1] + r3);

            // f[i][0] → f[i+1][1]
            upd(i + 1, 1, f[i][0] + d + Math.min(r2, 1L * r1 * a[i + 1] + r1));

            // f[i][1] → f[i+1][0] (три варианта выхода)
            upd(i + 1, 0, f[i][1] + d + 1L * r1 * a[i + 1] + r3 + 2 * d + r1);
            upd(i + 1, 0, f[i][1] + d + 1L * r1 * a[i + 1] + r1 + d + r1 + d + r1);
            upd(i + 1, 0, f[i][1] + d + r2 + d + r1 + d + r1);

            // f[i][1] → f[i+1][1] (два варианта)
            upd(i + 1, 1, f[i][1] + d + r2 + d + r1 + d);
            upd(i + 1, 1, f[i][1] + d + 1L * r1 * a[i + 1] + r1 + d + r1 + d);

            // Специальный случай — последний переход (оптимизация окончания)
            if (i == n - 1) {
                upd(i + 1, 0, f[i][1] + d + 1L * r1 * a[i + 1] + r3 + d + r1);
            }
        }

        System.out.println(f[n][0]);
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
