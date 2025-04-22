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
        int n,m;
        n = cin.nextInt();
        m = cin.nextInt();
        List<List<Character>> field = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Character> l = new ArrayList<>();
            String line = cin.next();
            for (int j = 0; j < m; j++) {
                l.add(line.charAt(j));
            }
            field.add(l);
        }
        boolean ok = true;
        int z = -1;

        for (int i = 0; i < n && z == -1; i++) {
            for (int j = 0; j < m && z == -1; j++) {
                if (field.get(i).get(j) == 'R') {
                    z = (i+j) % 2;
                }
            }
        }
        if (z != -1) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (field.get(i).get(j) != '.') continue;
                   int k = (i+j) % 2;
                   if (k == z) {
                       field.get(i).set(j, 'R');
                   } else {
                       field.get(i).set(j, 'W');
                   }
                }
            }
        } else {
            for (int i = 0; i < n && z == -1; i++) {
                for (int j = 0; j < m && z == -1; j++) {
                    if (field.get(i).get(j) == 'W') {
                        z = (i+j) % 2;
                    }
                }
            }
            if (z == -1) z = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (field.get(i).get(j) != '.') continue;
                    int k = (i+j) % 2;
                    if (k == z) {
                        field.get(i).set(j, 'W');
                    } else {
                        field.get(i).set(j, 'R');
                    }
                }
            }
        }
        for (int i = 0; i <n;  i++) {
            for (int j = 0; j < m; j++) {
                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        if (k*k + l*l != 1) continue;
                        int x = i + k;
                        int y = j + l;
                        if (0 <= x && x < n && 0 <= y && y < m) {
                            ok &= (field.get(i).get(j) !=
                                    field.get(x).get(y));
                        }
                    }
                }
            }
        }
        if (ok) {
            System.out.println("YES");
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    System.out.print(field.get(i).get(j));
                }
                System.out.println();
            }
        } else {
            System.out.println("NO");
        }
    }

    public static void main(String[] args) {
        int t = cin.nextInt();
        while (t-- > 0) {
            solve();
        }
    }
}
