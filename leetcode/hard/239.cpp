class Solution {
public:
    vector<int> maxSlidingWindow(vector<int>& a, int k) {
        deque<int> Qi(k);
        int i;
        for (i = 0; i < k; ++i) {
            while (!Qi.empty() && a[i] >= a[Qi.back()]) {
                Qi.pop_back();
            }
            Qi.push_back(i);
        }
        vector<int> ans(a.size() - (k-1));
        int id = 0;
        for (; i < a.size(); ++i) {
            ans[id++] = a[Qi.front()];
            while (!Qi.empty() && Qi.front() <= i - k) {
                Qi.pop_front();
            }
            while (!Qi.empty() && a[i] >= a[Qi.back()]) {
                Qi.pop_back();
            }
            Qi.push_back(i);
        }
        ans[id++] = a[Qi.front()];
        return ans;
    }
};
