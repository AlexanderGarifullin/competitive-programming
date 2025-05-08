import java.io.IOException;
import java.io.InputStream;
import java.security.PublicKey;
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

    private static final int MAX = 500_000 + 5;

    private static int[] mobius = new int[MAX];
    private static int[] cnt    = new int[MAX];
    private static boolean[] onShelf;
    private static int[][] divisors;
    private static long answer = 0;

    static class Pair<F,S> {
        F first;
        S second;

        Pair(F first, S second) {
            this.first = first;
            this.second = second;
        }

        static <F,S> Pair<F,S> of(F first, S second) {
            return new Pair<>(first, second);
        }
    }

    private static void buildMobius() {
        int[] spf = new int[MAX];
        List<Integer> primes = new ArrayList<>();

        mobius[1] = 1;
        for (int i = 2; i < MAX; i++) {
            if (spf[i] == 0) {
                spf[i] = i;
                primes.add(i);
                mobius[i] = -1;
            }
            for (int p : primes) {
                if (p > spf[i] || (long) p * i >= MAX) break;
                spf[p * i] = p;
                if (i % p == 0) {
                    mobius[p * i] = 0;
                    break;
                } else {
                    mobius[p * i] = -mobius[i];
                }
            }
        }
    }

    private static void buildDivisorLists(int[] a) {
        int n = a.length;
        divisors = new int[n][];

        int[] spf = new int[MAX];
        for (int i = 2; i < MAX; i++) if (spf[i] == 0) {
            for (long j = i; j < MAX; j += i) if (spf[(int) j] == 0) spf[(int) j] = i;
        }

        for (int i = 0; i < n; i++) {
            int x = a[i];
            int[] primes = new int[8];
            int pc = 0;
            while (x > 1) {
                int p = spf[x];
                primes[pc++] = p;
                while (x % p == 0) x /= p;
            }
            int subsets = 1 << pc;
            int[] list = new int[subsets];
            list[0] = 1;
            for (int mask = 1; mask < subsets; mask++) {
                int prod = 1;
                for (int b = 0; b < pc; b++)
                    if ((mask & (1 << b)) != 0)
                        prod *= primes[b];
                list[mask] = prod;
            }
            divisors[i] = list;
        }
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        int q = cin.nextInt();
        int [] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = cin.nextInt();
        }
        buildMobius();
        buildDivisorLists(a);

        onShelf = new boolean[n];
        while(q-->0) {
            int idx = cin.nextInt() - 1;
            int z = onShelf[idx] ? -1 : 1;
            for (int d : divisors[idx]) {
                if (onShelf[idx]) cnt[d]--;
                answer += z * (long) mobius[d] * cnt[d];
                if (!onShelf[idx]) cnt[d]++;
            }
            onShelf[idx] ^= true;
            System.out.println(answer);
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
