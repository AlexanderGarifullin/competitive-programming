#include <bits/stdc++.h>

using ld = long double;

using namespace std;

ld get_distance(ld x1, ld y1, ld x2, ld y2) {
	return sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
}

int vp, vf;
ld y;

ld f(ld x) {
	ld d1 = get_distance(x, y, 1, 0);
	ld d2 = get_distance(x, y, 0, 1);

	ld t1 = d1 * vp;
	ld t2 = d2 * vf;

	return t1 + t2;
}

// Ветви параболы вверх
int main() {
	cin >> vp >> vf >> y;

	ld l = 0, r = 1;
	while (r - l > 1e-5) {
		ld m1 = l + (r - l) / 3;
		ld m2 = r - (r - l) / 3;

		if (f(m1) > f(m2))
			l = m1;
		else
			r = m2;
	}

	cout << setprecision(19) << (l + r) / 2 << endl;

	return 0;
}