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

    static class Operation {
        int i, j, d;
        Operation(int i, int j, int d) {
            this.i = i;
            this.j = j;
            this.d = d;
        }
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        long[] s = new long[n];
        long[] t = new long[n];
        long sum = 0;

        for (int i = 0; i < n; i++) {
            s[i] = cin.nextLong();
            sum += s[i];
        }

        for (int i = 0; i < n; i++) {
            t[i] = cin.nextLong();
            sum -= t[i];
        }

        if (sum != 0) {
            System.out.println("NO");
            return;
        }

        Arrays.sort(t);

        Integer[] ord = new Integer[n];
        for (int i = 0; i < n; i++) ord[i] = i;
        Arrays.sort(ord, Comparator.comparingLong(i -> s[i]));

        List<Integer> needMore = new ArrayList<>();
        List<Integer> needLess = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            long current = s[ord[i]];
            long target = t[i];
            if (current < target) {
                needMore.add(i);
            } else if (current > target) {
                needLess.add(i);
            }
        }

        int p1 = 0, p2 = 0;
        List<Operation> ops = new ArrayList<>();

        while (p1 < needMore.size() && p2 < needLess.size()) {
            int i = needMore.get(p1);
            int j = needLess.get(p2);
            int from = ord[i];
            int to = ord[j];

            long delta = Math.min(t[i] - s[from], s[to] - t[j]);
            if (delta < 0 || i >= j) {
                System.out.println("NO");
                return;
            }

            s[from] += delta;
            s[to] -= delta;
            ops.add(new Operation(from + 1, to + 1, (int) delta));

            if (s[from] == t[i]) p1++;
            if (s[to] == t[j]) p2++;
        }

        Arrays.sort(s);
        boolean valid = true;
        for (int i = 0; i < n; i++) {
            if (s[i] != t[i]) {
                valid = false;
                break;
            }
        }

        if (!valid || ops.size() > 5 * n) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
            System.out.println(ops.size());
            for (Operation op : ops) {
                System.out.println(op.i + " " + op.j + " " + op.d);
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
