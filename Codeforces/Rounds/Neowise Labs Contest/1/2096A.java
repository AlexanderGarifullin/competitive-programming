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
        String s = cin.next();
        List<Integer> v = new ArrayList<>(n);
        int l = 1, r =n;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '<') {
                v.add(l++);
            }  else {
                v.add(r--);
            }
        }
        v.add(l);
        v = v.reversed();
        for (int x : v) System.out.print(x + " ");
        System.out.println();
    }


    public static void main(String[] args) {
        int t = cin.nextInt();
        while (t-- > 0) {
            solve();
        }
    }
}
