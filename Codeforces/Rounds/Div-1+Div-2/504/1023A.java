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
        int m = cin.nextInt();
        String s = cin.next();
        String t = cin.next();
        List<Character> a = new LinkedList<>(), b = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            a.add(s.charAt(i));
        }
        for (int i = 0; i < m; i++) {
            b.add(t.charAt(i));
        }
        while(!a.isEmpty() && !b.isEmpty() && a.getLast() == b.getLast()){
            a.removeLast();
            b.removeLast();
        }
        a = a.reversed();
        b = b.reversed();
        while(!a.isEmpty() && !b.isEmpty() && a.getLast() == b.getLast()){
            a.removeLast();
            b.removeLast();
        }
        if (a.isEmpty() && b.isEmpty() || (a.size() == 1 && a.getFirst() == '*')) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
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
