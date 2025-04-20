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

    static int k;
    static long calc(List<Integer> v) {
        int n = v.size();
        for (int i = 0; i < n; i++) {
            v.set(i, Math.abs(v.get(i)));
        }
        Collections.sort(v);;
        long ans = 0;
        for (int i = n-1; i >= 0; i -= k) {
            ans += 2L * v.get(i);
        }
        return ans;
    }

    static void solve() {
        int n = cin.nextInt();
        k = cin.nextInt();
        int mx = 0;
        List<Integer> a =new ArrayList<>(),b = new ArrayList<>();
        for (int i = 0,x; i < n; i++) {
            x = cin.nextInt();
            if (x < 0) a.add(x);
            else b.add(x);
            mx = Math.max(mx, Math.abs(x));
        }
        System.out.println(calc(a) + calc(b) - mx);
    }

    public static void main(String[] args) {
        int t = cin.nextInt();
        while (t-- > 0) {
            solve();
        }
    }
}
