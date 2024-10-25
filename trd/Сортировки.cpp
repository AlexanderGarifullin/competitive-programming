void merge_sort(vector<int>& v) {
    if (v.size() == 1) {
        return;
    }
 
    vector<int> left(v.size() / 2);
    vector<int> right(v.size() - left.size());
 
    copy(v.begin(), v.begin() + left.size(), left.begin());
    copy(v.begin() + left.size(), v.end(), right.begin());
 
    merge_sort(left);
    merge_sort(right);
 
    int i = 0, j = 0;
    while (i + j != v.size()) {
        if (j == right.size() || (i != left.size() && left[i] < right[j])) {
            v[i + j] = left[i];
            i++;
        }
        else {
            v[i + j] = right[j];
            j++;
        }
    }
}


void sift_down(vector<int>& v, int size, int index);
 
void heap_sort(vector<int>& v) {
    for (int i = v.size() / 2 - 1; i >= 0; i--) {
        sift_down(v, v.size(), i);
    }
 
    int sizeHeap = v.size();
    while (sizeHeap != 1) {
        swap(v[0], v[sizeHeap - 1]);
        sizeHeap--;
 
        sift_down(v, sizeHeap, 0);
    }
}
 
void sift_down(vector<int>& v, int size, int index) {
    int oldIndex;
    do {
        int left = index * 2 + 1;
        int right = left + 1;
        oldIndex = index;
 
        if (left < size && v[left] > v[index])
            index = left;
        if (right < size && v[right] > v[index])
            index = right;
 
        swap(v[index], v[oldIndex]);
 
    } while (index != oldIndex);
}
 

void quick_sort(vector<int>& v, int start, int end) {
    if (start >= end) {
        return;
    }
 
    int left = start, right = end;
    int element = v[rand() % (right - left + 1) + left];
 
    while (left <= right) {
        while (v[left] < element) { left++; }
        while (v[right] > element) { right--; }
 
        if (left <= right) {
            swap(v[left], v[right]);
            left++;
            right--;
        }
    }
 
    quick_sort(v, start, right);
    quick_sort(v, left, end);
}


void quick_sort(vector<int>& v) {
    quick_sort(v, 0, v.size() - 1);
}
 