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

    static final int N = 200005;
    static int[] a = new int[N];
    static int[] cnt = new int[N];
    static int[] bad = new int[N];
    static List<Integer>[] save = new ArrayList[N];

    static void solve() throws IOException {

        int n = cin.nextInt();
        int p = cin.nextInt();

        int mx = 0;
        for (int i = 0; i < n; i++) {
            a[i] = cin.nextInt();
            mx = Math.max(mx, a[i]);
        }

        int shift = 0;
        if (mx >= n) {
            shift = mx - n + 1;
            for (int i = 0; i < n; i++) {
                a[i] -= shift;
                a[i] = Math.max(a[i], 0);
            }
        }

        Arrays.fill(cnt, 0);
        for (int i = 0; i < n; i++) {
            cnt[a[i]]++;
        }
        for (int i = 1; i <= 2 * n; i++) {
            cnt[i] += cnt[i - 1];
        }

        for (int i = 0; i < save.length; i++) {
            save[i] = new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 2 * n; i >= 0; i--) {
            for (int x : save[i]) {
                bad[x]--;
            }

            int val = ((i - cnt[i]) % p + p) % p;
            bad[val]++;
            if (i - n >= 0) {
                save[i - n].add(val);
            }

            if (i <= n && bad[i % p] == 0) {
                ans.add(i + shift);
            }
        }

        Collections.reverse(ans);
        System.out.println(ans.size());
        for (int x : ans) {
            System.out.print(x + " ");
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
