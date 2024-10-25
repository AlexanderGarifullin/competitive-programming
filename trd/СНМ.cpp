#include <bits/stdc++.h>

using namespace std;

struct element_set {
	element_set *parent = this;
	int size = 1;
	int value, min, max;

	element_set(int v) {
		value = v;
		min = v;
		max = v;
	}

	element_set* get_main_parent() {
		if (this == parent)
			return this;

		auto result = parent->get_main_parent();
		parent = result;

		return result;
	}

	void _union(element_set *e) {
		auto e1 = this->get_main_parent();
		auto e2 = e->get_main_parent();

		if (e1 == e2)
			return;

		if (e2->size > e1->size)
			swap(e1, e2);


		e1->min = std::min(e1->min, e2->min);
		e1->max = std::max(e1->max, e2->max);
		e1->size += e2->size;

		e2->parent = e1;
	}
};

int main() {
	string input, word;
	getline(cin, input);

	istringstream stream(input);

	int n;
	stream >> n;

	vector<element_set*> v(n + 1);
	for (int i = 0; i <= n; i++)
		v[i] = new element_set(i);

	while (getline(cin, input)) {
		istringstream stream(input);
		stream >> word;

		int x, y;

		if (word == "union") {
			stream >> x >> y;

			v[x]->_union(v[y]);
		}

		if (word == "get") {
			stream >> x;
			auto e = v[x]->get_main_parent();

			cout << e->min << " " << e->max << " " << e->size << "\n";
		}
	}

	return 0;
}