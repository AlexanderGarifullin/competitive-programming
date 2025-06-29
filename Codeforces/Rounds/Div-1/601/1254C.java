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

    static long[] area = new long[1001];

    static int sign(int i, int j, int k) throws IOException {
        System.out.println("2 " + i + " " + j + " " + k);
        System.out.flush();
        return cin.nextInt();
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        int v;
        if (sign(1, 2, 3) > 0)
            v = 2;
        else
            v = 3;

        for (int i = 4; i <= n; i++) {
            if (sign(1, i, v) > 0)
                v = i;
        }

        List<Integer> l = new ArrayList<>();
        List<Integer> r = new ArrayList<>();
        List<Integer> x = new ArrayList<>();

        for (int i = 2; i <= n; i++) {
            if (i == v) continue;
            x.add(i);
            System.out.println("1 " + i + " " + v + " 1");
            System.out.flush();
            area[i] = cin.nextLong();
        }

        x.sort(Comparator.comparingLong(i -> area[i]));

        for (int i = 0; i < x.size() - 1; i++) {
            if (sign(1, x.get(i), x.get(i + 1)) > 0)
                r.add(x.get(i));
            else
                l.add(x.get(i));
        }

        r.add(x.get(x.size() - 1));

        System.out.print("0 1 " + v);
        for (int i : r) System.out.print(" " + i);
        Collections.reverse(l);
        for (int i : l) System.out.print(" " + i);
        System.out.println();
        System.out.flush();
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
