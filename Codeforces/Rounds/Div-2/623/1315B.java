import javax.crypto.spec.PSource;
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
        long a,b,p;
        String s;
        a = cin.nextInt();
        b = cin.nextInt();
        p = cin.nextInt();
        s = cin.next();
        int n = s.length();
        int left = -1;
        int right = n-1;
        while(left + 1 < right) {
            int mid = (left + right) / 2;
            boolean ok = true;
            long have = p;
            int tekPos = mid;
            while(tekPos < n-1 && have >= 0) {
                int j = tekPos;
                while(j < n-1 && s.charAt(j) == s.charAt(tekPos)) j++;

                if (s.charAt(tekPos) == 'A') have -= a;
                else have -= b;

                tekPos = j;
            }
            ok = have >= 0;
            if (ok) right = mid;
            else left = mid;
        }
        System.out.println(right + 1);
    }


    public static void main(String[] args) {
        int t = cin.nextInt();
//        int t = 1;
        while (t-- > 0) {
            solve();
        }
    }
}
