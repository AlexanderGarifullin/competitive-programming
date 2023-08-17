#include <iostream>

using namespace std;

int main() {
    int w;
    cin >> w;

    if (w % 2 != 0) {
        cout << "NO" << endl;
    } else {
        bool found = false;
        for (int i = 2; i < w-1; i += 2) {
            if ((w-i) % 2 == 0 && i % 2 == 0) {
                cout << "YES" << endl;
                found = true;
                break;
            }
        }
        if (!found) {
            cout << "NO" << endl;
        }
    }

    return 0;
}
