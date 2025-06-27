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
        long k = cin.nextLong();
        String s = cin.next();
        char[] arr = s.toCharArray();

        boolean[] stable = new boolean[n];
        int stableCount = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[(i - 1 + n) % n] || arr[i] == arr[(i + 1) % n]) {
                stable[i] = true;
                stableCount++;
            }
        }

        if (stableCount == 0) {
            if (k % 2 == 1) {
                for (int i = 0; i < n; i++)
                    arr[i] = arr[i] == 'B' ? 'W' : 'B';
            }
            System.out.println(new String(arr));
            return;
        }

        int[] left = new int[n];
        int[] right = new int[n];

        int lastStable = -1;
        for (int i = 0; i < 2 * n; i++) {
            int idx = i % n;
            if (stable[idx]) {
                lastStable = i;
            } else if (lastStable != -1) {
                left[idx] = lastStable;
            }
        }

        lastStable = -1;
        for (int i = 2 * n - 1; i >= 0; i--) {
            int idx = i % n;
            if (stable[idx]) {
                lastStable = i;
            } else if (lastStable != -1) {
                right[idx] = lastStable;
            }
        }

        char[] res = Arrays.copyOf(arr, n);
        for (int i = 0; i < n; i++) {
            if (stable[i]) continue;
            int ld = (i - (left[i] % n) + n) % n;
            int rd = ((right[i] % n) - i + n) % n;

            if (ld > k && rd > k) {
                if (k % 2 == 1) {
                    res[i] = arr[i] == 'B' ? 'W' : 'B';
                }
            } else if (arr[left[i] % n] == arr[right[i] % n]) {
                res[i] = arr[left[i] % n];
            } else {
                res[i] = ld <= rd ? arr[left[i] % n] : arr[right[i] % n];
            }
        }

        System.out.println(new String(res));
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
