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

    static void solve() throws IOException {
        int n = cin.nextInt();
        int[] l, r, a;
        l = new int[n];
        r = new int[n];
        a = new int[n];
        Integer [] p = new Integer[n];

        for (int i = 0; i < n; i++) {
            l[i] = cin.nextInt();
            r[i] = cin.nextInt();
        }

        for (int i = 0; i < n; i++) p[i] = i;

        Arrays.sort(p, Comparator.comparingInt(i -> l[i]));


        int max1 = -1, max2 = -1, cnt = 0;

        for (int iIndex = 0; iIndex < n; iIndex++) {
            int i = p[iIndex];
            if (max1 == -1 || l[i] > r[max1]) {
                a[i] = -1;
                cnt++;
            } else {
                a[i] = 0;
            }

            if (max1 != -1 && l[i] <= r[max1] && (max2 == -1 || l[i] > r[max2])) {
                a[max1]++;
            }

            int x = i;
            if (max1 == -1 || r[x] > r[max1]) {
                int tmp = x;
                x = max1;
                max1 = tmp;
            }
            if (x != -1 && (max2 == -1 || r[x] >= r[max2])) {
                max2 = x;
            }
        }

        int maxAdd = Arrays.stream(a).max().getAsInt();
        System.out.println(cnt + maxAdd);
    }

    public static void main(String[] args) throws IOException {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
