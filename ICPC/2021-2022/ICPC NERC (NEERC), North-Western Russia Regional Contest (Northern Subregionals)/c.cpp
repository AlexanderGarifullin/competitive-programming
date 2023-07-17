#include <bits/stdc++.h>
#define ll long long
#define ld long double
#define en '\n'
using namespace std;


struct vertex {
    vertex* to[26] = {0};
    vertex*father ={0};
    bool terminal = 0;
    bool check = 0;
    ll deep = 0;
    ll childs = 0;
    ll id = 0;
    vertex* operator=(vertex *v){
        return v;
    }
};
vector<vertex*> q;
vector<vertex*> q_copy;
vertex *root = new vertex();
ll ans = 0;
ll n, k;
ll mi = 0;
ll z = 1;
ll id = 0;

void add_string(string &s)
{
    vertex *v = root;
    ll deep = 0;
    for (char c: s)
    {
        c -='a';
        deep++;
        if (!v->to[c]){
            v->to[c] = new vertex();
            id++;
            v->to[c]->id = id;
        }
        v->to[c]->father = v;
        v = v->to[c];
        v->deep = deep;
    }
    v->terminal = true;
    v->childs =1;
    q.push_back(v);
}

ll add_value(vertex *v)
{
    for (int i = 0; i < 26; ++i) {
        if (v->to[i])
        {
            v->childs += add_value(v->to[i]);
        }
    }
    return v->childs;
}

void get(vertex *v)
{
    if (v->check) return;
    vertex *vcopy = v;
    ll id_vcopy = v->id;
    if (v->childs == k)
    {
        v->childs = 0;
        v->check = 1;
        while (v->father && v->father->childs <= k)
        {
            if (v->father->check)
            {
                return;
            }
            v = v->father;
            v->childs = 0;
            v->check = 1;
        }
        while (v->father)
        {
            v->father->childs -= k;
            v = v->father;
        }
        ans++;
    }
    else if (v->childs < k)
    {
        while (v->father && v->father->childs <= k)
        {
            if (v->father->check)
                return;
            v= v->father;
            id_vcopy = v->id;

        }
        if (!v->father)
        {
            v->check = 1;
            v->childs = 0;
            ans++;
            return;
        }
        if (v->childs == k)
        {
            vcopy->check = 1;
            vcopy->childs = 0;
            ans++;
            v->childs = 0;
            v->check = 1;
            while (v->father)
            {
                v = v->father;
                v->childs -= k;
            }
            return;
        }
        ll minus_father = 0;
        vector<ll> id_deletes;
        while (v->father->childs > k)
        {
            ll max_delete_child = -1;
            vertex *maxv = new vertex();
            for (int i = 0; i < 26; ++i) {
                if (v->father->to[i] && !v->father->to[i]->check && v->father->to[i]->childs > max_delete_child)
                {
                    max_delete_child = v->father->to[i]->childs;
                    maxv = v->father->to[i];
                }
            }
            if (maxv->childs > k) {
                q_copy.push_back(vcopy);
                return;
            }
            v->father->childs -= maxv->childs;
            minus_father += max_delete_child;
            maxv->check = 1;
            maxv->childs = 0;
            id_deletes.push_back(maxv->id);
            ans++;
        }
        if (minus_father != 0)
        {
            v = v->father;
            while (v->father)
            {
                v = v->father;
                v->childs -= minus_father;
            }
            bool fl = 1;
            for (auto &iddeletes: id_deletes)
            {
                if (iddeletes ==  id_vcopy)
                    fl = 0;
            }
            if (fl)
                q_copy.push_back(vcopy);
        }
        else
            q_copy.push_back(vcopy);
    }
}

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    cin >> n;
    cin >> k;
    for (int i = 0; i < n; ++i) {
        string s;
        cin >> s;
        add_string(s);
    }
    add_value(root);
    sort(q.begin(),q.end(),[](vertex* v1, vertex *v2)
    {return v1->deep > v2->deep;});
    vertex *vc = root;
    ll i = 0;
    while (!q.empty()) {
        q_copy = {};
        for (auto &v: q) {
            get(v);
        }
        q = q_copy;
    }
    if (root->childs)
        ans++;
    cout << ans;
    return 0;
}
