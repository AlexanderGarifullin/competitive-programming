#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;
 
int main()
{
    string s1,s2;
    ll h1,h2,m1,m2;
    cin >> s1 >> s2;
    h1 = stoi(s1.substr(0,2));
    h2 = stoi(s2.substr(0,2));
    m1 = stoi(s1.substr(3,2));
    m2 = stoi(s2.substr(3,2));
    if (h2 >= h1 && m2 >= m1)
        cout <<h2 - h1 + m2 - m1;
    else if (h2 == h1 &&  m2 < m1)
        cout << m2 + (60 - m1) + 23 - h1 + h2;
    else if (h2 > h1 &&  m2 < m1)
        cout << m2 + 60 - m1 + h2 - h1 - 1;
    else if (h2 < h1 && m1 == m2)
        cout << 24 - h1 + h2;
    else if (h2 < h1 && m1 < m2)
        cout << 24 - h1 + h2 + m2 - m1;
    else if (h2 < h1 &&  m1 > m2)
        cout << m2 + 60 - m1 + 23 - h1 + h2;
}
