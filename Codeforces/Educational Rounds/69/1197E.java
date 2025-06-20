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

    static final int MOD = (int) 1e9 + 7;

    static class Event implements Comparable<Event> {
        int x, op, id;

        Event(int x, int op, int id) {
            this.x = x;
            this.op = op;
            this.id = id;
        }

        @Override
        public int compareTo(Event o) {
            if (this.x != o.x) return Integer.compare(this.x, o.x);
            return Integer.compare(this.op, o.op);
        }
    }

    static void solve() throws IOException {
        int n = cin.nextInt();
        int[] out = new int[n];
        int[] inVol = new int[n];
        List<Event> events = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            out[i] = cin.nextInt();
            inVol[i] = cin.nextInt();
            events.add(new Event(inVol[i], 1, i));
            events.add(new Event(out[i], 0, i));
        }

        Collections.sort(events);

        int[] mn = new int[n];
        int[] cnt = new int[n];

        int M = 0, C = 1;
        int I = 0;

        for (Event e : events) {
            int x = e.x, op = e.op, id = e.id;
            if (op == 1) {
                I = Math.max(I, x);
                mn[id] = x + M;
                cnt[id] = C;
            } else {
                int value = -x + mn[id];
                if (value < M) {
                    M = value;
                    C = cnt[id];
                } else if (value == M) {
                    C = (C + cnt[id]) % MOD;
                }
            }
        }

        M = Integer.MAX_VALUE;
        C = 0;
        for (int i = 0; i < n; i++) {
            if (out[i] > I) {
                if (mn[i] < M) {
                    M = mn[i];
                    C = cnt[i];
                } else if (mn[i] == M) {
                    C = (C + cnt[i]) % MOD;
                }
            }
        }

        System.out.println(C);
    }

    public static void main(String[] args) throws IOException {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
