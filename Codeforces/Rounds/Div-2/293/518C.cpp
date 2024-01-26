#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    int n, m, k;
    cin >> n >> m >> k;
    vector<int> v(n+1);
    vector<int> numbers(n);
    for (int i = 0; i < n; ++i) {
        cin >> numbers[i];
        v[numbers[i]] = i;
    }
    ll a = 0;
    for (int i = 0; i < m; ++i) {
        int x;
        cin >> x;
        a += v[x] / k;
        a++;
        if (v[x]== 0)
            continue;
        int t = v[x];
        v[x] = v[numbers[t-1]];
        v[numbers[t-1]] = t;
        swap(numbers[v[numbers[t-1]]],numbers[v[x]]);
    }
    cout << a;
}
