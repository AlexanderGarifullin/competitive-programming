import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
        int sz = 1;
        int cntBad = 0;
        int last = -10;
        boolean bad = false;
        for (int i = 0,x; i <n ; i++) {
            x = cin.nextInt();
            if (x == 1) {
                if (last == i - 1) {
                    sz += 5;
                } else {
                    sz++;
                }
                last = i;
            } else {
                if (last == i -1) {
                    cntBad = 0;
                }
                cntBad++;
                if (cntBad == 2) {
                    bad = true;
                }
            }
        }
        if (bad) {
            System.out.println(-1);
        } else {
            System.out.println(sz);
        }
    }

    public static void main(String[] args) {
        int t = cin.nextInt();
        while (t-- > 0) {
            solve();
        }
    }
}
