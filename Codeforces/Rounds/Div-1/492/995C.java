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

    static final long LIMIT_SQ = 1500000L * 1500000L;

    static void solve() throws IOException {
        int n = cin.nextInt();

        long[] x = new long[n];
        long[] y = new long[n];

        for (int i = 0; i < n; i++) {
            x[i] = cin.nextLong();
            y[i] = cin.nextLong();
        }

        List<Integer> id = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            id.add(i);
        }

        int[] c = new int[n];

        while (true) {
            Collections.shuffle(id);

            long px = 0;
            long py = 0;

            for (int k = 0; k < n; k++) {
                int i = id.get(k);

                long pxPlus  = px + x[i];
                long pyPlus  = py + y[i];
                long pxMinus = px - x[i];
                long pyMinus = py - y[i];

                long distPlusSq  = pxPlus * pxPlus  + pyPlus * pyPlus;
                long distMinusSq = pxMinus * pxMinus + pyMinus * pyMinus;

                if (distPlusSq <= distMinusSq) {
                    c[i] = +1;
                    px = pxPlus;
                    py = pyPlus;
                } else {
                    c[i] = -1;
                    px = pxMinus;
                    py = pyMinus;
                }
            }

            if (px * px + py * py <= LIMIT_SQ) {
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.print(c[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
