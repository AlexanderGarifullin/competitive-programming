#include <bits/stdc++.h>

using namespace std;

int n, n_;
vector<vector<vector<int>>> tree;

void add_x(int x, int y, int z, int k);
void add_y(int x, int y, int z, int k);
void add_z(int x, int y, int z, int k);

void add_x(int x, int y, int z, int k) {
	x += n_ - 1;
	add_y(x, y, z, k);

	while (x != 0) {
		x = (x - 1) >> 1;
		add_y(x, y, z, k);
	}
}

void add_y(int x, int y, int z, int k) {
	y += n_ - 1;
	add_z(x, y, z, k);

	while (y != 0) {
		y = (y - 1) >> 1;
		add_z(x, y, z, k);
	}
}

void add_z(int x, int y, int z, int k) {
	z += n_ - 1;
	tree[x][y][z] += k;

	while (z != 0) {
		z = (z - 1) >> 1;
		int l = (z << 1) + 1, r = (z << 1) + 2;

		tree[x][y][z] = tree[x][y][l] + tree[x][y][r];
	}
}

int sum_x(int x1, int y1, int z1, int x2, int y2, int z2, int x, int lx, int rx);
int sum_y(int x1, int y1, int z1, int x2, int y2, int z2, int x, int y, int ly, int ry);
int sum_z(int x1, int y1, int z1, int x2, int y2, int z2, int x, int y, int z, int lz, int rz);

int sum_x(int x1, int y1, int z1, int x2, int y2, int z2, int x, int lx, int rx) {
	if (x2 < lx || rx < x1)
		return 0;

	if (x1 <= lx && rx <= x2)
		return sum_y(x1, y1, z1, x2, y2, z2, x, 0, 0, n_ - 1);

	int mx = (lx + rx) / 2;
	int r1 = sum_x(x1, y1, z1, x2, y2, z2, (x << 1) + 1, lx, mx);
	int r2 = sum_x(x1, y1, z1, x2, y2, z2, (x << 1) + 2, mx + 1, rx);

	return r1 + r2;
}

int sum_y(int x1, int y1, int z1, int x2, int y2, int z2, int x, int y, int ly, int ry) {
	if (y2 < ly || ry < y1)
		return 0;

	if (y1 <= ly && ry <= y2)
		return sum_z(x1, y1, z1, x2, y2, z2, x, y, 0, 0, n_ - 1);

	int my = (ly + ry) / 2;
	int r1 = sum_y(x1, y1, z1, x2, y2, z2, x, (y << 1) + 1, ly, my);
	int r2 = sum_y(x1, y1, z1, x2, y2, z2, x, (y << 1) + 2, my + 1, ry);

	return r1 + r2;
}

int sum_z(int x1, int y1, int z1, int x2, int y2, int z2, int x, int y, int z, int lz, int rz) {
	if (z2 < lz || rz < z1)
		return 0;

	if (z1 <= lz && rz <= z2)
		return tree[x][y][z];

	int mz = (lz + rz) / 2;
	int r1 = sum_z(x1, y1, z1, x2, y2, z2, x, y, (z << 1) + 1, lz, mz);
	int r2 = sum_z(x1, y1, z1, x2, y2, z2, x, y, (z << 1) + 2, mz + 1, rz);

	return r1 + r2;
}

int main() {
	cin >> n;
	
	n_ = 1;
	while (n_ < n)
		n_ <<= 1;

	tree = vector<vector<vector<int>>>(2 * n_ - 1, vector<vector<int>>(2 * n_ - 1, vector<int>(2 * n_ - 1)));

	while (true) {
		int c;
		cin >> c;

		switch (c)
		{
			case 1:
				int x, y, z, k;
				cin >> x >> y >> z >> k;

				add_x(x, y, z, k);
				break;

			case 2:
				int x1, y1, z1, x2, y2, z2;
				cin >> x1 >> y1 >> z1 >> x2 >> y2 >> z2;

				cout << sum_x(x1, y1, z1, x2, y2, z2, 0, 0, n_ - 1) << "\n";
				break;

			case 3:
				return 0;
		}
	}

	return 0;
}