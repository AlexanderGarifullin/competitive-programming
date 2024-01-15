#include <bits/stdc++.h>
using namespace std;

int main() {

    ios_base::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);
 
	int t_; cin >> t_; while(t_--){
		int a,b; cin >> a >> b;
		cout <<min(max(a,2*b), max(2*a,b)) *  min(max(a,2*b), max(2*a,b))<<endl;
	}
	return 0;
}
