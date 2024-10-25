// 1 способ
vector<vector<int>> adj;
vector<bool> visited;
vector<int> tin, low;
int timer;

void IS_BRIDGE(int v,int to) {
}

void dfs(int v, int p = -1) {
    visited[v] = true;
    tin[v] = low[v] = timer++;
    bool parent_skipped = false;
    for (int to : adj[v]) {
        if (to == p && !parent_skipped) {
            parent_skipped = true;
            continue;
        }
        if (visited[to]) {
            low[v] = min(low[v], tin[to]);
        } else {
            dfs(to, v);
            low[v] = min(low[v], low[to]);
            if (low[to] > tin[v])
                IS_BRIDGE(v, to);
        }
    }
}

// 2 способ
const int N = 500043;
vector<int> g[N];
int tin[N], fup[N];
int T = 1;
vector<pair<int, int> > bridges;

int dfs(int x, int par = -1)
{
    tin[x] = T++;
    fup[x] = tin[x];
    for(auto y : g[x])
    {
        if(tin[y] > 0)
        {
            if(par != y)
                fup[x] = min(fup[x], tin[y]);
        }
        else
        {
            int f = dfs(y, x);
            fup[x] = min(fup[x], f);
            if(f > tin[x])
                bridges.push_back(make_pair(x, y));
        }
    }
    return fup[x];
}