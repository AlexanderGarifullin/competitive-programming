class Solution {
    public List<Integer> findAnagrams(String s, String t) {
        if (t.length() > s.length()) {
            return Collections.emptyList();
        }
        char [] cnt = new char[26];
        char [] need = new char[26];
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i) -'a']++;
        }
        for (int left = 0; left < t.length() ; left++) {
            cnt[s.charAt(left) - 'a']++;
        }
        List<Integer> ans = new ArrayList<>();
        if (Arrays.equals(cnt, need)) ans.add(0);
        for (int i = 1; i + t.length() - 1 < s.length(); i++) {
            cnt[s.charAt(i + t.length() - 1) - 'a']++;
            cnt[s.charAt(i - 1) - 'a']--;
            if (Arrays.equals(cnt, need)) ans.add(i);
        }
        return ans;
    }
}
