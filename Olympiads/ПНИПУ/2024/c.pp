//Лимит времени 2000/4000/4000/4000 мс. Лимит памяти 65000/65000/65000/65000 Кб.
//
//Вася – очень азартный персонаж. Ему везет во всяких лотереях. Он часто покупает лотерейные билеты в киоске рядом с домом. Однажды в киоск привезли N лотерейных билетов. По секрету Вася узнал, что ровно K из них – выигрышные. Интересно, какое минимальное количество билетов Вася должен купить, чтобы среди них оказался хотя бы один выигрышный билет с вероятностью не меньше, чем P?
//
//Формат входных данных
//        В единственной строке входного файла содержатся три числа, разделенные пробелом: натуральное N, целое неотрицательное K, вещественное P, где 0≤K≤100, 0<N≤100, K≤N, 0≤P≤1.
//
//Формат выходных данных
//        В единственной строке выходного файла должно быть написано искомое минимальное количество билетов, которое необходимо купить, чтобы с вероятностью не меньше, чем Р, среди них был хотя бы один выигрышный билет. Если вероятность не меньше, чем P, нельзя обеспечить никаким количеством купленных билетов, следует вывести -1.
//Ввод 1	Ввод 2
//2 1 0.5	2 1 0.75
//Вывод 1	Вывод 2
//1	2

#include <bits/stdc++.h>

constexpr auto sp = " ";
constexpr auto en = "\n";

using ull = unsigned long long;
using ll = long long;
using ld = long double;

#define isz(x) int((x).size())
#define all(x) begin(x), end(x)
#define rall(x) rbegin(x), rend(x)
#define fin(x) for (auto& it : x) cin >> it;
#define foutsp(x) for (auto& it : x) cout << it << " "; cout << en;
#define fouten(x) for (auto& it : x) cout << it << en;
#define fyesno(x) cout << (x ? "YES" : "NO") << en;

using namespace std;

void solve();
void global();

ull gcd(ull a, ull b) {
    return b == 0 ? a : gcd(b, a % b);
}

ull lcm(ull a, ull b) {
    return a * b / gcd(a, b);
}

vector<int> to_z(string& s, bool flag = false) {
    vector<int> z(isz(s));
    if (flag) z[0] = isz(z);
    int l = 0, r = 1;
    for (int i = 1; i < isz(z); i++) {
        if (r > i) z[i] = min(z[i - l], r - i);
        while (z[i] + i < isz(z) && s[z[i]] == s[z[i] + i]) z[i]++;
        if (r < z[i] + i) { l = i; r = z[i] + i; }
    }
    return z;
}

ull get_c(ull n, ull k) {
    ull r = 1, dif = n - k;
    for (int i = 1; i <= k; i++)
        r += r * dif / i;
    return r;
}

string pi_str = "31415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679";

double get_pi() {
    return acos(-1);
}

int my_rand(int left, int right) {
    return rand() % (right - left + 1) + left;
}

void test() {
    solve();
}

void tests() {
    int t; cin >> t;
    while (t--) solve();
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr); cout.tie(nullptr);
    cout.setf(ios::fixed); cout.precision(12);

    srand(time(0));
    global();
    test();

    return 0;
}


void global() {

}

void solve() {
    int n, k1;
    ld p1;
    cin >> n >> k1 >> p1;

    ld k2 = n - k1;
    ld p2 = 1 - p1;

    if (k2 == n) {
        cout << -1 << en;
        return;
    }

    ld value = 1;
    int m = n;

    for (int i = 0; i < n; i++) {
        value *= (k2--) / m--;

        if (value < p2 || (abs(value - p2) < 1e-6)) {
            cout << i + 1 << en;
            return;
        }
    }
}
