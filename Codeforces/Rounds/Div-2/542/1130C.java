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
        int r1 = cin.nextInt(), c1 = cin.nextInt();
        int r2 = cin.nextInt(), c2 = cin.nextInt();
        r1--;c1--;r2--;c2--;
        List<List<Integer>> field = new ArrayList<>();
        List<List<Integer>> state = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String s = cin.next();
            field.add(new ArrayList<>());
            state.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                state.get(i).add(-1);
                field.get(i).add(s.charAt(j) - '0');
            }
        }
        List<List<Integer>> dsu = new ArrayList<>();
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (field.get(i).get(j) == 1 ||
                    state.get(i).get(j) != -1) continue;
                int code = dsu.size();
                dsu.add(new ArrayList<>());
                int r = i, c = j;
                Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
                queue.add(Pair.of(r, c));
                while(!queue.isEmpty()) {
                    var p = queue.poll();
                    dsu.getLast().add(getHash(p.first, p.second, n));
                    state.get(p.first).set(p.second, code);
                    for (int dx = -1; dx <=1 ; dx++) {
                        for (int dy = -1; dy <= 1; dy++) {
                            if (dx * dx + dy * dy == 1) {
                                int rr = p.first + dx;
                                int cc = p.second + dy;
                                if (0 <= rr && rr < n && 0 <= cc && cc < n
                                && state.get(rr).get(cc) == -1 && field.get(rr).get(cc) != 1) {
                                    queue.add(Pair.of(rr, cc));
                                }
                            }
                        }
                    }
                }
            }
        }
        if (state.get(r1).get(c1) == state.get(r2).get(c2)) {
            ans = 0;
        } else {
            for (int hash1 : dsu.get(state.get(r1).get(c1))) {
                for (int hash2 : dsu.get(state.get(r2).get(c2))) {
                    int x1 = hash1 / n, y1 = hash1 % n;
                    int x2 = hash2 / n, y2 = hash2 % n;
                    ans = Math.min(ans, (long) (x1 - x2) *  (x1 - x2) +
                            (long) (y1 - y2) * (y1 - y2));
                }
            }
        }
        System.out.println(ans);
    }


    public static void main(String[] args) {
//        int t = cin.nextInt();
        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
