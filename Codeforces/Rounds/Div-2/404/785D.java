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

    private static final int N = 200000+10;
    private static final long mod = (int)1e9 + 7;

    private static final long modAdd(long a, long b) {
        long res = a + b;
        if (res >= mod) res -= mod;
        return res;
    }
    private static final long modSub(long a, long b) {
        long res = a-b;
        if (res < 0) res += mod;
        return res;
    }
    private static final long modMul(long a, long b) {
        return (a*b) % mod;
    }

    private static final long modPow(long x, long e) {
        long res = 1;
        long base = x % mod;
        long exp = e;
        while(exp > 0) {
            if((exp & 1) == 1) res = modMul(res, base);
            base = modMul(base, base);
            exp >>= 1;
        }
        return res;
    }

    private static final long modInv(long x) {
        return modPow(x, mod - 2);
    }
    private static final long fact[] = new long[N+1];
    private static final long invfact[] = new long[N+1];

    private static final long C(int n, int k) {
        if (k < 0 || k > n) return 0;
        return modMul(fact[n], modMul(invfact[k], invfact[n -k]));
    }

    static void solve() throws IOException {


        fact[0] = 1;
        for (int i = 1; i <= N; i++) {
            fact[i] = modMul(fact[i-1], i);
        }
        invfact[N] = modInv(fact[N]);
        for (int i = N-1; i >= 0; i--) {
            invfact[i] = modMul(invfact[i+1], i + 1);
        }

        String s = cin.next();
        int len = s.length();
        int [] opnLeft = new int[len];
        int [] clsRight = new int[len];
        opnLeft[0] = s.charAt(0) == '(' ? 1 : 0;
        for (int i = 1; i < len; i++) {
            opnLeft[i] = opnLeft[i-1] + ((s.charAt(i) == '(') ? 1 : 0);
        }
        clsRight[len-1] = (s.charAt(len-1) == ')') ? 1 : 0;

        for (int i = len-2; i >=0 ; i--) {
            clsRight[i] = clsRight[i+1] + ((s.charAt(i) == ')') ? 1 : 0);
        }

        long res=0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != '(' || clsRight[i] == 0) continue;
            long add = C(opnLeft[i] + clsRight[i] -1, opnLeft[i]);
            res = modAdd(res, add);
        }
        System.out.println(res);
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
