struct Point {
            ll x,y;
        };

ll dist(const Point & a, const Point & b) {
    ll dx = a.x - b.x;
    ll dy = a.y - b.y;
    return dx*dx + dy*dy;
}

ll brute(vector<Point> & points, int left, int right) {
    ll res = 1e18;
    for (int i = left; i < right; ++i) {
        for (int j = i+1; j < right; ++j) {
            ll d = dist(points[i], points[j]);
            setmin(res, d);
        }
    }
    return res;
}

ll closestPair(vector<Point> & points, int left, int right) {
    if (right - left <= 3) {
        return brute(points, left, right);
    }
    int mid = (left+right)/2;
    ll midX = points[mid].x;
    ll dL = closestPair(points, left, mid);
    ll dR = closestPair(points, mid, right);
    ll d = min(dL, dR);
    vector<Point> strip;
    for (int i = left; i < right; ++i) {
        ll dx = points[i].x - midX;
        if (dx * dx < d) {
            strip.push_back(points[i]);
        }
    }
    sort(all(strip), [](Point &a, Point&b){
       return a.y < b.y;
    });
    int n = isz(strip);
    for (int i = 0; i < n; ++i) {
        for (int j = i+1; j < n && (strip[i].y - strip[j].y) * (strip[i].y - strip[j].y) < d; ++j) {
            ll d2 = dist(strip[i], strip[j]);
            setmin(d, d2);
        }
    }
    return d;
}
