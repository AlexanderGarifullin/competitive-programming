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

    static void solve() {
        int n = cin.nextInt();
        int k = cin.nextInt();
        List<Integer> l = new ArrayList<>(n), r = new ArrayList<>(n), b = new ArrayList<>(n);
        for (int i = 0, x; i < n; i++) {
            x = cin.nextInt();
            l.add(x);
        }
        for (int i = 0, x; i < n; i++) {
            x = cin.nextInt();
            r.add(x);
        }
        long y = 0;
        for (int i = 0; i < n; i++) {
            b.add(Math.min(l.get(i), r.get(i)));
            y += Math.max(l.get(i), r.get(i));
        }
        b.sort(Comparator.reverseOrder());
//        System.out.println(b);
        for (int i = 0; i < k-1; i++) {
            y += b.get(i);
        }
        System.out.println(y + 1);
    }


    public static void main(String[] args) {
        int t = cin.nextInt();
        while (t-- > 0) {
            solve();
        }
    }
}
