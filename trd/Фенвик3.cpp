#include <bits/stdc++.h>

using namespace std;

int n;
vector<vector<vector<int>>> fenwick;

int sum(int x_, int y_, int z_) {
	x_++, y_++, z_++;
	int r = 0;

	int x = x_;
	while (x > 0) {
		int y = y_;
		while (y > 0) {
			int z = z_;
			while (z > 0) {
				r += fenwick[x][y][z];
				z = (z & (z + 1)) - 1;
			}
			y = (y & (y + 1)) - 1;
		}
		x = (x & (x + 1)) - 1;
	}

	return r;
}

int sum(int x1, int y1, int z1, int x2, int y2, int z2) {
	return sum(x2, y2, z2) - sum(x1 - 1, y1 - 1, z1 - 1)
		+ sum(x1 - 1, y1 - 1, z2) - sum(x1 - 1, y2, z2) 
		+ sum(x1 - 1, y2, z1 - 1) - sum(x2, y1 - 1, z2) 
		+ sum(x2, y1 - 1, z1 - 1) - sum(x2, y2, z1 - 1);
}

void add(int x_, int y_, int z_, int k) {
	x_++, y_++, z_++;
	
	int x = x_;
	while (x <= n) {
		int y = y_;
		while (y <= n) {
			int z = z_;
			while (z <= n) {
				fenwick[x][y][z] += k;
				z |= z + 1;
			}
			y |= y + 1;
		}
		x |= x + 1;
	}
}

int main() {
	cin >> n;

	fenwick = vector<vector<vector<int>>>(n + 1, vector<vector<int>>(n + 1, vector<int>(n + 1)));

	while (true) {
		int c;
		cin >> c;

		switch (c)
		{
			case 1:
				int x, y, z, k;
				cin >> x >> y >> z >> k;

				add(x, y, z, k);
				break;

			case 2:
				int x1, y1, z1, x2, y2, z2;
				cin >> x1 >> y1 >> z1 >> x2 >> y2 >> z2;

				cout << sum(x1, y1, z1, x2, y2, z2) << "\n";
				break;

			case 3:
				return 0;
		}
	}

	return 0;
}