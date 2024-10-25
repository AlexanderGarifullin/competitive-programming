// C++ program to illustrate the use
// of ropes using Rope header file
#include <ext/rope>
#include <iostream>
 
// SGI extension
using namespace __gnu_cxx;
 
using namespace std;
 
// Driver Code
int main()
{
    // rope<char> r = "abcdef"
    crope r = "abcdef";
 
    cout << r << "\n";
 
    return 0;
}

//////////////////////////////////////////////////////
// C++ program to illustrate the use
// of ropes using Rope header file
#include <ext/rope>
#include <iostream>
 
// SGI extension
using namespace __gnu_cxx;
using namespace std;
 
// Driver Code
int main()
{
    // rope<char> r = "abcdef"
    crope r = "geeksforgeeks";
 
    cout << "Initial rope: "
         << r << endl;
 
    // 'g' is added at the
    // end of the rope
    r.push_back('g');
    r.push_back('f');
    r.push_back('g');
 
    cout << "Rope after pushing f: "
         << r << endl;
 
    int pos = 2;
 
    // gfg will be inserted
    // before position 2
    r.insert(pos - 1, "gfg");
 
    cout << "Rope after inserting "
         << "gfg at position 2: " << r
         << endl;
 
    // gfg will be deleted
    r.erase(pos - 1, 3);
 
    cout << "Rope after removing gfg"
         << " inserted just before: "
         << r << endl;
 
    // Replace "ee" with "00"
    r.replace(pos - 1, 2, "00");
 
    cout << "Rope after replacing "
         << "characters: " << r
         << endl;
 
    // Slice the rope
    crope r1 = r.substr(pos - 1, 2);
 
    cout << "Subrope at position 2: "
         << r << endl;
 
    // Removes the last element
    r.pop_back();
    r.pop_back();
    r.pop_back();
 
    cout << "Final rope after popping"
         << " out 3 elements: " << r;
 
    return 0;
}
Начальная веревка: geeksforgeeks
Веревка после толкания f: geeksforgeeksgfg
Веревка после вставки gfg в позицию 2: ggfgeeksforgeeksgfg
Веревка после удаления gfg вставлена ​​непосредственно перед: geeksforgeeksgfg
Веревка после замены символов: g00ksforgeeksgfg
Подвергнуться на позиции 2: g00ksforgeeksgfg
Финальная веревка после выталкивания 3 элементов: g00ksforgeeks

//////////////////////////////////////////////////////
// C++ program to illustrate the use
// of ropes using Rope header file
#include <ext/rope>
#include <iostream>
 
// SGI extension
using namespace __gnu_cxx;
using namespace std;
 
// Driver Code
int main()
{
    // rope<char> r = "abcdef"
    crope r = "abcdef";
 
    rope<char>::iterator it;
    for (it = r.mutable_begin();
         it != r.mutable_end(); it++) {
 
        // Print the value
        cout << char((*it) + 2)
             << "";
    }
 
    return 0;
}

//////////////////////////////////////////////////
push_back(): Эта функция используется для ввода символа в конце веревки. Временная сложность: O(log N).
pop_back(): введена в C++11 (для строк), эта функция используется для удаления последнего символа из веревки. Временная сложность: O(log N).
insert(int x, crope r1): Вставляет содержимое r1 перед элементом x . Временная сложность: в лучшем случае: O(log N) и в худшем случае: O(N).
erasure(int x, int l): Стирает l элементов, начиная с x- го элемента. Временная сложность: O(log N).
substr(int x, int l): Возвращает новую веревку, элементами которой являются l символов, начиная с позиции x . Временная сложность: O(log N).
replace(int x, int l, crope r1): Заменяет l элементов, начиная с x -го элемента, элементами из r1 . Временная сложность: O(log N).
concatenate(+): объединить две веревки с помощью символа '+'. Временная сложность: O(1).
