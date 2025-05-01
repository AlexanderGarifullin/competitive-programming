import java.security.PublicKey;
import java.util.*;

public class Main {
    static Scanner cin = new Scanner(System.in);

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

    static int getHash(int r, int c, int n) {
        return r * n + c;
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

    static void solve() {
        long n,k; n = cin.nextLong(); k = cin.nextLong();
        long rightMin = k / 2;
        if (rightMin * 2 != k) rightMin++;
        if (rightMin > n) System.out.println(0);
        else {
            long rightMax = Math.min(n, k-1);
            long ans = rightMax - rightMin + 1;
            if (rightMin * 2 == k) ans--;
            System.out.println(ans);
        }
    }


    public static void main(String[] args) {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
