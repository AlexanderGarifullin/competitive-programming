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
        int n = cin.nextInt();
        int k = cin.nextInt();
        String s = cin.next();
        if (n == k) {
            System.out.println(s);
            return;
        }
        List<Boolean> use = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            use.add(false);
        }
        List<Integer> pos = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') pos.add(i);
            else {
                use.set(pos.getLast(), true);
                use.set(i, true);
                k -=2;
                if (k == 0) break;;
                pos.removeLast();
            }
        }
        for (int i = 0; i < n; i++) {
            if (use.get(i)) {
                System.out.print(s.charAt(i));
            }
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
