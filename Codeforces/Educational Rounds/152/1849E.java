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

    static void solve() throws IOException {
        int n = cin.nextInt();
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = cin.nextInt() - 1;
        }

        int[] bigL = new int[n], bigR = new int[n];
        int[] smaL = new int[n], smaR = new int[n];
        Arrays.fill(bigL, -1);
        Arrays.fill(smaL, -1);
        Arrays.fill(bigR, n);
        Arrays.fill(smaR, n);

        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && p[stack.peek()] < p[i]) {
                bigR[stack.pop()] = i;
            }
            if (!stack.isEmpty()) {
                bigL[i] = stack.peek();
            }
            stack.push(i);
        }

        stack.clear();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && p[stack.peek()] > p[i]) {
                smaR[stack.pop()] = i;
            }
            if (!stack.isEmpty()) {
                smaL[i] = stack.peek();
            }
            stack.push(i);
        }

        long ans = 0;

        for (int i = 0; i < n; i++) {
            if (i - smaL[i] < smaR[i] - i) {
                int mx = i;
                for (int j = i; j > smaL[i]; j--) {
                    if (p[mx] < p[j]) {
                        mx = j;
                    }
                    ans += Math.max(0, smaR[i] - bigR[mx]);
                }
            } else {
                int mx = i;
                for (int j = i + 1; j < smaR[i]; j++) {
                    if (p[mx] < p[j]) {
                        mx = j;
                    }
                    ans += Math.max(0, i - Math.max(smaL[i], bigL[mx]));
                }
            }
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
