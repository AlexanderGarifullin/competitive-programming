#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    ll n ;
    string m;
    cin >> n >> m;
    ll rubv = stoi(m.substr(0,m.find(',')));
    ll kopv = stoi(m.substr(m.find(',')+1));
    ll rub1 = -1;
    ll kopv1 = -1;
    string a = "-1";
    for (int i = 0; i < n; ++i) {
        string name, tek_price;
        cin >> name >> tek_price;
        ll tek_rub = stoi(tek_price.substr(0,tek_price.find(',')));;
        ll tek_kop =  stoi(tek_price.substr(tek_price.find(',')+1));
        if (tek_rub <= rubv && tek_kop <= kopv)
        {
            if (tek_rub > rub1)
            {
                rub1 = tek_rub;
                a = name;
                kopv1 = tek_kop;
            }
            else if (tek_rub == rub1)
                if (tek_kop >kopv1 )
                {
                    kopv1 = tek_kop;
                    a = name;
                }
        }
    }
    cout << a;

    return 0;
}
