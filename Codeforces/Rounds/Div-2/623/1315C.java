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
        List<Integer> b=  new ArrayList<>(n);
        List<Integer> a = new ArrayList<>(2*n);
        List<Boolean> use = new ArrayList<>(2*n+1);
        List<Integer> pos = new ArrayList<>(n);
        for (int i = 0,x; i < n; i++) {
            x = cin.nextInt();
            b.add(x);
            pos.add(i);
        }
        Collections.sort(pos, (i, j) -> b.get(i).compareTo(b.get(j)));

        for (int i = 0; i < 2*n; i++) {
            a.add(-1);
            use.add(false);
        }
        use.add(false);

        for (int i = 0; i < n; i++) {
            int mmin = b.get(i);
            if (use.get(mmin)) {
                System.out.println(-1);
                return;
            }
            use.set(mmin, true);
            a.set(2*i, mmin);
        }
        List<Integer> free = new ArrayList<>();
        for (int i = 1; i < 2*n + 1; i++) {
            if (!use.get(i)) {
                free.add(i);
            }
        }
        Collections.sort(free);

        for (int i = 0; i < n; i++) {
            int bval = b.get(i);
            boolean find = false;
            for (int j = bval; j < 2 * n + 1; j++) {
                if (!use.get(j)) {
                    use.set(j, true);
                    a.set(2*i+1, j);
                    find = true;
                    break;
                }
            }
            if (!find) {
                System.out.println(-1);
                return;
            }
        }

        for (int i = 0; i < 2*n; i++) {
            System.out.print(a.get(i) + " ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
