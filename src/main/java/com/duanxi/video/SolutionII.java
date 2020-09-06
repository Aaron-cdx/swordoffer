package com.duanxi.video;


import java.util.*;

/**
 * Created by caoduanxi on 2020/4/6
 */
public class SolutionII {
    // 每一行每一列都不能有相同的
    private boolean[] col;
    // 两个对角线
    private boolean[] dia1;
    private boolean[] dia2;
    private List<List<String>> res;

    public List<List<String>> solveNQueens(int n) {
        col = new boolean[n];
        // 总共有七条对角线
        dia1 = new boolean[2 * n - 1];
        dia2 = new boolean[2 * n - 1];
        res = new ArrayList<>();

        putNQueen(n, 0, new ArrayList<Integer>());

        return res;
    }

    private void putNQueen(int n, int index, ArrayList<Integer> list) {
        if (index == n) {
            List<String> strings = generateString(n, new ArrayList<Integer>(list));
            res.add(strings);
            return;
        }
        // 开始遍历
        for (int i = 0; i < n; i++) {
            // 一行一列是false，两条对角线都是false即可
            // index-i本来是表示斜对角线上的点，但是由于index-i可能会出现负数的情况
            // 可以得到index-i+n-1可以保证没有负数
            if (!col[i] && !dia1[index + i] && !dia2[index - i + n - 1]) {
                col[i] = true;
                dia1[index + i] = true;
                dia2[index - i + n - 1] = true;
                list.add(i);
                putNQueen(n, index + 1, list);
                col[i] = false;
                dia1[index + i] = false;
                dia2[index - i + n - 1] = false;
                list.remove(list.size() - 1);
            }
        }
    }

    // list中存放的是每一行可以放的坐标
    private List<String> generateString(int n, ArrayList<Integer> list) {
        List<String> strings = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(".");
        }
        for (int i = 0; i < n; i++) {
            strings.add(sb.toString());
        }
        for (int i = 0; i < n; i++) {
            String s = strings.get(i);
            StringBuilder stringBuilder = new StringBuilder(s);
            stringBuilder.setCharAt(list.get(i), 'Q');
            strings.remove(i);
            strings.add(i, stringBuilder.toString());
        }
        return strings;
    }


    // 通过了，时间复杂度：O(n^2) 空间复杂度：O(n^2)
    public int minimumTotal(List<List<Integer>> triangle) {
        // 层数
        int size = triangle.size();
        // 上一个元素可以获得的下面元素为
        // 记录上一个元素的
        int[][] dp = new int[size][size];
        dp[0][0] = triangle.get(0).get(0);
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < size; i++) {
            List<Integer> tempList = triangle.get(i);
            for (int j = 0; j < tempList.size(); j++) {
                // 第一个数的时候
                if (j == 0) {
                    // 将其直接与上一排第一个相加
                    dp[i][j] = dp[i - 1][j] + tempList.get(j);
                } else if (j == i) {
                    // 否则将其与上排前一个相加，同等位置，下一排会往后延伸一位，所以往上需要j-1
                    dp[i][j] = dp[i - 1][j - 1] + tempList.get(j);
                } else {
                    // 否则的话，则是处于中间段,此时计算上面相邻的两个即可
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i - 1][j - 1]) + tempList.get(j);
                }
            }
        }
        // 最后所有的结果都在二维数组最后一组数据中
        for (int i = 0; i < dp[size - 1].length; i++) {
            res = Math.min(dp[size - 1][i], res);
        }

        return res;
    }

    // 由于上述只与dp[i-1][j] dp【i-1][j-1]有关
    // 用两个变量来存储上面的这两个变量
    public int minimumTotal1(List<List<Integer>> triangle) {
        // 层数
        int size = triangle.size();
        // 上一个元素可以获得的下面元素为
        // 记录上一个元素的
        int[] dp = new int[size];
        dp[0] = triangle.get(0).get(0);
        int res = Integer.MAX_VALUE;
        int prev = 0;// dp[i-1][j]
        int cur = 0;
        for (int i = 1; i < size; i++) {
            List<Integer> list = triangle.get(i);
            for (int j = 0; j < list.size(); j++) {
                // 表示向右移动
                prev = dp[j];
                // 每一个dp[j]记录的都是一个元素。总共有四层
                if (j == 0) {
                    dp[j] = prev + list.get(j);
                } else if (j == i) {
                    dp[j] = cur + list.get(j);
                } else {
                    dp[j] = Math.min(prev, cur) + list.get(j);
                }
                // 注意prev和cur需要向右移动
                cur = prev;
            }
        }
        for (int i = 0; i < size; i++) {
            res = Math.min(res, dp[i]);
        }
        return res;
    }

    // 辗转相除法解决
    public double gcd(int a, int b) {
        // 如果能整除的话，此时最大公约数为当前的除数
        int m = Math.max(a, b);
        int n = Math.min(a, b);
        int c = m % n;
        if (c == 0) {
            return m;
        }
        return gcd(n, c);
    }

    public void moveZeroes(int[] nums) {
        if (nums == null) return;
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, i, k);
                k++;
            }
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length < 1) return 0;
        int i = 0;
        int j = -1;
        int res = 0;
        int len = nums.length + 1;
        // 滑动窗口的终止条件
        while (i < nums.length) {
            if (res < s) {
                // 需要确保j+1不能越界
                if (j + 1 >= nums.length) break;
                res += nums[++j];
            } else if (res >= s) {
                // 如果大于等于，此时计算长度，i向前进
                len = Math.min(len, j - i + 1);
                res -= nums[i++];
            }
        }
        return len == nums.length + 1 ? 0 : len;
    }

    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if (p.length() < 1) return res;
        int i = 0;
        int j = 0;
        int[] nums = new int[26];
        int[] nump = new int[26];
        for (int l = 0; l < p.length(); l++) {
            nums[p.charAt(l) - 'a']++;
        }
        while (j < s.length()) {
            int temp = s.charAt(j) - 'a';
            nump[temp]++;
            // 如果有相同的，可能会有多个重复！
            while (nump[temp] > nums[temp]) {
                // 算出左边当前的，移除左边的
                int cnt = s.charAt(i) - 'a';
                i++;
                nump[cnt]--;
            }
            if ((j - i + 1) == p.length()) {
                res.add(i);
            }
            j++;
        }
        return res;
    }

    /**
     * 递归方法实现前序遍历二叉树
     */
//    public List<Integer> preorderTraversal(TreeNode root) {
//        List<Integer> res = new ArrayList<>();
//        dfs(root, res);
//        return res;
//    }
//
//    private void dfs(TreeNode root, List<Integer> res) {
//        if (root == null) {
//            return;
//        }
//        res.add(root.val);
//        dfs(root.left, res);
//        dfs(root.right, res);
//    }

    /**
     * 非递归方法实现前序遍历二叉树
     * 这里需要用栈实现，用Queue队列实现的话，用例过不了
     */
    public List<Integer> preorderTraversal_1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> queue = new Stack<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.pop();
            res.add(poll.val);
            if (poll.right != null) {
                queue.push(poll.right);
            }
            if (poll.left != null) {
                queue.push(poll.left);
            }
        }
        return res;
    }

    /**
     * 最小覆盖字串问题
     */
    public String minWindow(String s, String t) {
        // 一定要保证进来多少就要出去多少！
        Map<Character, Integer> map = new HashMap<>();
        Map<Character, Integer> resMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int l = 0;
        int r = 0;
        int match = 0;// 匹配的个数，只关心字符，出现次数也需要一样
        String res = "";
        int len = Integer.MAX_VALUE;
        while (r < s.length()) {
            char temp = s.charAt(r);
            if (map.containsKey(temp)) {
                resMap.put(temp, resMap.getOrDefault(temp, 0) + 1);
                if (map.get(temp).equals(resMap.get(temp))) {
                    match++;
                }
            }
            r++; // 向前进
            // 匹配到需要的长度，也就是map中的元素个数长度
            while (match == map.size()) {
                // 如果长度小的话，则截取
                if ((r - l) < len) {
                    res = s.substring(l, r);
                    len = res.length();
                }
                // 此时需要向右移动
                char t1 = s.charAt(l);
                if (map.containsKey(t1)) {
                    resMap.put(t1, resMap.get(t1) - 1);
                    // 不匹配的话，是因为resMap中的当前字母个数小于map中的
                    if (map.get(t1) > resMap.getOrDefault(t1, 0)) {
                        match--;
                    }
                }
                l++;
            }

        }
        return res.length() < Integer.MAX_VALUE ? res : "";
    }

    public int lengthOfLongestSubstring(String s) {
        int l = 0;
        int r = 0;
        // 要应对全部的字符
        int[] nums = new int[256];
        int res = Integer.MIN_VALUE;
        while (r < s.length()) {
            char c = s.charAt(r);
            // 表示不重复，继续向前
            if (nums[c] == 0) {
                nums[c]++;
                r++;
            } else {
                // 此时遇到重复，取长度，然后窗口向前移动，当前字符--
                res = Math.max(r - l, res);
                nums[s.charAt(l++)]--;
            }
        }
        return Math.max(r - l, res);
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i - 1 >= 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < nums.length - 1; j++) {
                // 相同的元素需要略过
                if (j - 1 > i && nums[j] == nums[j - 1]) continue;
                for (int k = j + 1; k < nums.length; k++) {
                    if (k - 1 > j && nums[k] == nums[k - 1]) continue;
                    if (nums[j] + nums[k] + nums[i] == 0) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        return res;
    }

    public List<List<Integer>> threeSum_1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        Arrays.sort(nums);

        // 二分法
        for (int i = 0; i < nums.length - 2; i++) {
            if (i - 1 >= 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1;
            int r = nums.length - 1;
            int temp = -nums[i];
            // 大于0的话，由于排序了，后面的数不可能为负数
            if (nums[i] > 0) {
                break;
            }
            while (l < r) {
                // [-2,0,0,2,2]
                // 内部也要去重复
                int tmp = nums[l] + nums[r];
                if (tmp > temp) {
                    r--; // 先想前走，如果还是等于后面的，继续走，去重逻辑
                    while (r > l && nums[r] == nums[r + 1]) {
                        r--;
                    }
                } else if (tmp < temp) {
                    l++; // 先向后走，如果还是等于前面的，继续走，去重逻辑
                    while (l < r && nums[l] == nums[l - 1]) {
                        l++;
                    }
                } else {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    r--; // 先前后都走，如果两个还是等于，继续一起走
                    l++;
                    while (l < r && nums[r + 1] == nums[r] && nums[l] == nums[l - 1]) {
                        r--;
                        l++;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 时间复杂度:O(nlogn+mlogm)主要是两趟排序的时间，遍历的可以忽略  空间复杂度:O(1)
     */
    public int[] intersect_1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int len1 = nums1.length;
        int len2 = nums2.length;
        int l1 = 0;
        int l2 = 0;
        List<Integer> list = new ArrayList<>();
        while (l1 < len1 && l2 < len2) {
            if (nums1[l1] > nums2[l2]) {
                l2++;
            } else if (nums1[l1] < nums2[l2]) {
                l1++;
            } else {
                list.add(nums1[l1]);
                l1++;
                l2++;
            }
        }
        int[] res = new int[list.size()];
        l1 = 0;
        for (Integer num : list) {
            res[l1++] = num;
        }
        return res;
    }

    /**
     * 另外一种方法用map记录此时去重复
     * 时间复杂度:O(m+n) 空间复杂度:O(min(m,n))
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1); // 选出短的
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int k = 0;
        for (int num : nums2) {
            int cnt = map.getOrDefault(num, 0);
            if (cnt > 0) {
                nums1[k++] = num;
                map.put(num, cnt - 1);
            }
        }
        return Arrays.copyOfRange(nums1, 0, k);
    }

    // 不是快乐数最终会变为4
    public boolean isHappy1(int n) {
        // 也就是最后需要为100，值为1
        if (n == 4) return false;
        if (n == 1) return true;
        int sum = 0;
        while (n != 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return isHappy1(sum);
    }

    /**
     * 使用快慢指针的思路
     */
    public boolean isHappy(int n) {
        int fast = n;
        int slow = n;
        // 不是快乐数最终是会相遇的
        do {
            slow = getSum(slow);
            fast = getSum(fast);
            fast = getSum(fast);
        } while (slow != fast);
        return slow == 1;
    }

    public int getSum(int n) {
        int sum = 0;
        while (n != 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }

    /**
     * 空间复杂度：O(n)  时间复杂度：O(n)
     */
    public boolean wordPattern1(String pattern, String str) {
        char[] chars = pattern.toCharArray();
        String[] s = str.split(" ");
        int l1 = chars.length;
        int l2 = s.length;
        if (l1 != l2) return false;
        // abba    dog dog dog dog  ==> false
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        int index = 0;
        while (index < l1) {
            if (!map.containsKey(chars[index])) {
                map.put(chars[index], s[index]);
                boolean add = set.add(s[index]);  // set添加
                if (!add) { // 如果set失败的话，就遇到了键不同，值相同的情况
                    return false;
                }
            } else {
                // 否则就是包含
                if (!map.get(chars[index]).equals(s[index])) {
                    return false;
                }
            }
            index++;
        }
        return true;
    }

    public boolean wordPattern(String pattern, String str) {
        String[] s = str.split(" "); // 主要是pattern为"" str为"beef"类似这种
        if (s.length != pattern.length()) return false;
        String[] p = pattern.split("");

        return judgement(p, s) && judgement(s, p);
    }

    private boolean judgement(String[] p, String[] s) {
        int len = p.length;
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            if (map.containsKey(p[i])) {
                if (!map.get(p[i]).equals(s[i])) {
                    return false;
                }
            } else {// 不包含
                map.put(p[i], s[i]);
            }
        }
        return true;
    }

    public boolean isIsomorphic(String s, String t) {
        // ab aa
        if (s.length() != t.length()) return false;
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if ((map.get(s.charAt(i)) != t.charAt(i))) {
                    return false;
                }
            } else {
                if (map.containsValue(t.charAt(i))) {
                    return false;
                }
                map.put(s.charAt(i), t.charAt(i));
            }
        }
        return true;
    }

    /**
     * 通过HashMap获取值，然后排序获得
     */
    public String frequencySort1(String s) {
        if (s == null) return s;
        Map<String, Integer> map = new HashMap<>();
        String[] split = s.split("");
        for (int i = 0; i < split.length; i++) {
            map.put(split[i], map.getOrDefault(split[i], 0) + 1);
        }
        ArrayList<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : entries) {
            int cnt = entry.getValue();
            String key = entry.getKey();
            while (cnt != 0) {
                sb.append(key);
                cnt--;
            }
        }
        return sb.toString();
    }


    /**
     * 使用map和结构化数组来实现
     * 数组中放入list，其中数组大小由最大次数决定即数组中有list
     * 次数是list的下标，而这么多次数的字母在list中
     */
    public String frequencySort2(String s) {
        if (s == null) return s;
        Map<Character, Integer> map = new HashMap<>();
        int maxTimes = -1;
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            maxTimes = Math.max(maxTimes, map.get(s.charAt(i)));
        }
        List<Character>[] arrayLists = new ArrayList[maxTimes + 1];
        for (Character c : map.keySet()) {
            int f = map.get(c);
            if (arrayLists[f] == null) {
                arrayLists[f] = new ArrayList<>();
            }
            arrayLists[f].add(c);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = maxTimes; i >= 0; i--) {
            if (arrayLists[i] != null) {
                for (Character c : arrayLists[i]) {
                    while (i != 0) {
                        sb.append(c);
                        i--;
                    }
                }
            }
        }
        return sb.toString();
    }

    // 学了面向对象，居然不会用面向对象，可笑

    /**
     * 使用大顶堆，自己构建一个对象，对象实现comparator接口
     * 重写比较方法即可维护一个属于自己的判断的大顶堆
     * 最后根据大顶堆输出字符的次数，StringBuilder拼接即可
     */
    public String frequencySort(String s) {
        int[] tab = new int[256];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            tab[c]++;
        }

        PriorityQueue<Latter> queue = new PriorityQueue<>();
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] != 0) {
                queue.add(new Latter((char) i, tab[i]));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Latter poll = queue.poll();
            int cnt = poll.cnt;
            while (cnt != 0) {
                sb.append(poll.c);
                cnt--;
            }
        }
        return sb.toString();
    }

    // 构造对象
    class Latter implements Comparable {
        char c;
        int cnt;

        public Latter(char c, int cnt) {
            this.c = c;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Object o) {
            return ((Latter) o).cnt - this.cnt;
        }
    }

    /**
     * 利用四重循环的思路可以解决，时间复杂度O(n^4)
     */
    public List<List<Integer>> fourSum1(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 4) return res;
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len - 3; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            for (int j = i + 1; j < len - 2; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) continue;
                int temp = nums[i] + nums[j];
                for (int k = j + 1; k < len - 1; k++) {
                    if (k > j + 1 && nums[k - 1] == nums[k]) continue;
                    for (int l = k + 1; l < len; l++) {
                        if (l > k + 1 && nums[l - 1] == nums[l]) continue;
                        if (temp + nums[k] + nums[l] == target) {
                            res.add(Arrays.asList(nums[i], nums[j], nums[k], nums[l]));
                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * 使用双指针的方式
     * 排序O(nlogn) 双重遍历O(n^2) 其中双指针操作O(n/2)
     * 总的时间复杂度O(n^3)
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length < 4) return res;
        int len = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < len - 3; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            for (int j = i + 1; j < len - 2; j++) {
                if (j > i + 1 && nums[j - 1] == nums[j]) continue;
                // 嵌套双指针
                int l = j + 1;
                int r = nums.length - 1;
                int temp = nums[i] + nums[j];
                while (l < r) {
                    int t = nums[l] + nums[r];
                    if (temp + t < target) {
                        l++;
                        while (l < r && nums[l - 1] == nums[l]) {
                            l++;// 这里l已经+1所以是和自己前面的对比
                        }
                    } else if (temp + t > target) {
                        r--;
                        while (l < r && nums[r + 1] == nums[r]) {
                            r--;// 这里r已经-1，所以是和自己后面的对比
                        }
                    } else {
                        res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        r--;
                        l++;
                        while (l < r && nums[l] == nums[l - 1] && nums[r] == nums[r + 1]) {
                            l++;
                            r--;
                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * 时间复杂度O(n^2) 外层循环+内层双指针
     */
    public int threeSumClosest(int[] nums, int target) {
        int len = nums.length;
        int res = 1000000;
        Arrays.sort(nums);
        // 怎么最接近，就是剪去target，看谁绝对值更小
        for (int i = 0; i < len - 1; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) continue;
            int l = i + 1;
            int r = len - 1;
            while (l < r) {
                int temp = nums[l] + nums[r]; // 注意这里需要比较绝对值更小的才是最接近
                res = Math.abs(res - target) > Math.abs(temp + nums[i] - target) ? temp + nums[i] : res;
                if (temp + nums[i] == target) {
                    return target;
                } else if (temp + nums[i] > target) {
                    r--;
                    while (l < r && nums[r + 1] == nums[r]) {
                        r--;
                    }
                } else {
                    l++;
                    while (l < r && nums[l - 1] == nums[l]) {
                        l++;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 暴力法超出时间限制 时间复杂度:O(n^4)
     */
    public int fourSumCount1(int[] A, int[] B, int[] C, int[] D) {
        if (A.length == 0 || B.length == 0
                || C.length == 0 || D.length == 0) {
            return 0;
        }
        Arrays.sort(A);
        Arrays.sort(B);
        Arrays.sort(C);
        Arrays.sort(D);
        int len = A.length;
        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                for (int k = 0; k < len; k++) {
                    for (int l = 0; l < len; l++) {
                        if (A[i] + B[j] + C[k] + D[l] == 0) {
                            res += 1;
                        }
                    }
                }
            }
        }
        return res;
    }

    /**
     * 使用map获取两个，然后通过组合
     * 时间复杂度O(n^2) 空间复杂度(n)
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int res = 0;
        if (A.length == 0 || B.length == 0
                || C.length == 0 || D.length == 0) {
            return res;
        }
        int len = A.length;
        // 时间复杂度按照n^2来走
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int t = A[i] + B[j];
                map.put(t, map.getOrDefault(t, 0) + 1);
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int t = C[i] + D[j];
                map2.put(t, map2.getOrDefault(t, 0) + 1);
            }
        }
        Set<Integer> set = map.keySet();
        for (int a : set) {
            res += map.get(a) * map2.getOrDefault(-a, 0);
        }
        return res;
    }

    /**
     * 遍历内部排序
     * 时间复杂度:O(nklogk) n表示strs长度，k表示字符串长度
     * 空间复杂度:O(n)
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs.length == 0) return res;
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            ;
            String temp = String.valueOf(chars);
            if (!map.containsKey(temp)) {
                map.put(temp, new ArrayList<>());
            }
            map.get(temp).add(str);
        }
        for (String s : map.keySet()) {
            res.add(map.get(s));
        }
        return res;
    }

    public int numberOfBoomerangs(int[][] points) {
        int res = 0;
        if (points == null) return 0;

        int m = points.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                int sum = getSum(i, j, points);
                if (!map.containsKey(sum)) {
                    map.put(sum, 1);
                } else {
                    int n = map.get(sum);
                    res += 2 * n;
                    map.put(sum, n + 1);
                }
            }
            map.clear();
        }
        return res;
    }

    public int getSum(int i, int j, int[][] points) {
        return (points[i][0] - points[j][0]) * (points[i][0] - points[j][0])
                + (points[i][1] - points[j][1]) * (points[i][1] - points[j][1]);
    }

    public int maxPoints(int[][] points) {
        // 计算斜率，正负都要考虑
        int res = 0;
        Map<String, Integer> map = new HashMap<>();
        int l = points.length;
        if (l < 2) return l;
        for (int i = 0; i < l; i++) {
            int duplicate = 0; // 计算重复的元素
            int max = 0;
            for (int j = 0; j < l; j++) {
                if (i == j) continue;
                // 需要计算入一个重复的元素
                // 否则计算斜率
                int x = (points[i][0] - points[j][0]);
                int y = (points[i][1] - points[j][1]);
                if (x == 0 && y == 0) {
                    duplicate++;
                    continue;
                }
                // 辗转相除获取最大公约数
                int a = gcdII(x, y);
                x = x / a;
                y = y / a;
                // 获取最大公约数
                String key = x + "-" + y;
                map.put(key, map.getOrDefault(key, 0) + 1);
                max = Math.max(max, map.get(key));
            }
            res = Math.max(res, max + duplicate + 1);
            map.clear();// 注意清除
        }
        return res;
    }

    private int gcdII(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

    private double getScope(int i, int j, int[][] points) {
        double x = (points[i][0] - points[j][0]);
        double y = (points[i][1] - points[j][1]);
        if (x != 0 && y != 0) {
            return y / x;
        }
        if (x == 0) {
            return 0;
        }
        return Integer.MAX_VALUE;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums.length <= 1 || k < 0) return false;
        int l = 0;
        int r = 0;
        Set<Integer> set = new HashSet<>();
        // 窗口大小维持k大小
        while (r < nums.length) {
            if (!set.add(nums[r])) {
                if (r - l <= k) return true;
                else set.remove(nums[l++]);
            } else {
                // 因为如果等于k的话，此时也要移除
                if (r - l >= k) set.remove(nums[l++]);
            }
            r++;
        }
        return false;
    }

    public boolean containsDuplicate(int[] nums) {
        if (nums.length <= 1) return false;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 利用TreeMap获取自己上半部分的值
     * 利用判断获取自己下半部分的值
     * nums[i]-t  <= temp <= nums[i]+t ，则证明有效
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums.length < 2 || k < 0) return false;
        // 利用查找表结合滑动窗口来实现啊
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            // 给出一个大于当前给的值的值
            Long temp = set.ceiling((long) nums[i] - (long) t);
            if (temp != null && temp <= (long) nums[i] + (long) t) {
                return true;
            }
            set.add((long) nums[i]);
            if (set.size() == k + 1) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

    /**
     * 直接利用走一趟的方式解决
     */
    public ListNode reverseList1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = null;
        while (head.next != null) {
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }

    /**
     * 利用递归的方式解决链表反转
     * 1->2->3->4->5
     * 首先到5，然后pre=5      head
     * head.next.next=head,即  4 -> 5 -> 4 然后head.next切断4->5
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = reverseList(head.next);
        // 此处反转
        head.next.next = head;
        // 释放当前的head
        head.next = null;
        return pre;
    }

    /**
     * 使用一遍遍历，首先找到一个基准位节点，最后每次将第一个节点
     * 的后面的节点插入到当前基准位节点之后就好。一直遍历到n节点即可
     */
    public ListNode reverseBetween1(ListNode head, int m, int n) {
        if (head == null || head.next == null) return head;
        int res = 0; // 用来记录遍历到的位置
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        ListNode cur = dummyNode.next;
        for (int i = 0; i < m - 1; i++) {
            pre = pre.next;
            cur = cur.next;
        }
        // pre保持不变，第一个节点一直向后走，其余遇到的节点向第一个节点后面插就好
        for (int i = 0; i < n - m; i++) {
            ListNode temp = cur.next;
            cur.next = cur.next.next;// 交换当前节点与后面节点的指向
            // 解除之前的连接
            temp.next = pre.next;
            pre.next = temp;
        }
        return dummyNode.next;
    }

    /**
     * 翻转前n个节点
     */
    private ListNode successor = null;

    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        // 成环了
        ListNode res = reverseN(head.next, n - 1);
        head.next.next = head;

        head.next = successor;// 每一个的尾部都指向successor也就是尾端
        return res;
    }

    /**
     * 递归实现
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == 1) {
            return reverseN(head, n);
        }
        // 否则的需要向后走，由于m与n是相对位置，所以需要一起减少
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }

    // 找到不同的那个点，然后改变指针指向
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = head;
        ListNode q = p.next;
        while (q != null) {
            while (q != null && q.val == p.val) {
                q = q.next;
            }
            p.next = q;
            if (q != null) {
                p = q;
                q = p.next;
            }
        }
        return head;
    }

    // 只要相同一直改变
    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head;
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                // 改变指针指向
                ListNode temp = cur.next;
                cur.next = temp.next;
                temp.next = null;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    /**
     * 利用虚拟头节点是一个方法，每次都新建一个节点拼接
     * 最后将两条链表组合即可
     */
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode small = new ListNode(0);
        ListNode s = small;
        ListNode big = new ListNode(0);
        ListNode b = big;
        while (head != null) {
            if (head.val < x) {
                s.next = new ListNode(head.val);
                s = s.next;
            } else {
                b.next = new ListNode(head.val);
                b = b.next;
            }
            head = head.next;
        }
        s.next = big.next;
        return small.next;
    }

    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode even = head.next;
        ListNode o = head;// 奇数节点
        ListNode e = even;// 偶数节点
        while (e != null && e.next != null) {
            o.next = e.next;
            o = e.next;// 奇数节点到偶数节点的下一个节点
            e.next = o.next;
            e = o.next;// 偶数节点到奇数节点的下一个节点
        }
        o.next = even;// 奇数节点的下一个节点连接到偶数节点的头节点
        return head;
    }

    // 2020-05-05 22:08:51
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode sum = new ListNode(0);
        ListNode cur = sum;
        // 是否有进位
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int l1val = l1 == null ? 0 : l1.val;
            int l2val = l2 == null ? 0 : l2.val;
            int temp = l1val + l2val + carry;
            carry = temp / 10; // 获取余数即进位至下一环节,是将余数进位！
            cur.next = new ListNode(temp % 10); // 获取十位上作为节点填充
            cur = cur.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return sum.next;
    }

    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode cursor = root;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sumVal = l1Val + l2Val + carry;
            carry = sumVal / 10;

            ListNode sumNode = new ListNode(sumVal % 10);
            cursor.next = sumNode;
            cursor = sumNode;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return root.next;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);
        int carry = 0;
        ListNode sum = new ListNode(0);
        ListNode cur = sum;
        while (l1 != null || l2 != null || carry != 0) {
            int l1val = l1 == null ? 0 : l1.val;
            int l2val = l2 == null ? 0 : l2.val;
            int temp = l1val + l2val + carry;
            carry = temp / 10;
            cur.next = new ListNode(temp % 10);
            cur = cur.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return reverse(sum.next);
    }

    /**
     * 不采用翻转的话，就使用数据结构来帮助，栈
     */
    public ListNode addTwoNumbersII(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        ListNode sum = null; // 将结果翻转
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int s1 = stack1.isEmpty() ? 0 : stack1.pop();
            int s2 = stack2.isEmpty() ? 0 : stack2.pop();
            int temp = s1 + s2 + carry;
            carry = temp / 10;
            ListNode tmp = new ListNode(temp % 10);
            // 这要一直插在sum与cur之间
            tmp.next = sum;
            sum = tmp;
        }
        return sum;
    }

    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = null;
        while (head != null) {
            ListNode temp = head.next;
            head.next = pre;
            pre = head;
            head = temp;
        }
        return pre;
    }

    /**
     * 移除元素，使用虚拟头结点
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode cur = dummyNode;
        while (head != null) {
            if (head.val == val) {
                ListNode temp = head.next;// 保存当前节点的下一个节点
                head.next = null;// 解除当前节点与下一个节点的联系
                head = temp;// 当前节点向前移动
                cur.next = head;// 改变cur下一个节点指向
            } else {
                cur = head;
                head = head.next;// 一起向后挪一个位置
            }
        }
        return dummyNode.next;
    }

    /**
     * 移除重复出现的元素
     */
    public ListNode deleteDuplicatesII(ListNode head) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode cur = dummyNode;
        // 首先保证当前元素和当前下一个元素补位null
        while (head != null && head.next != null) {
            // 如果不等，一起向前
            if (head.val != head.next.val) {
                cur = head;
                head = head.next;
            } else {
                // 如果相等，则head一个人向前，cur等待连接到不等的节点即可
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                // 到了这里可以表明到了不等的一点或者尾部节点，记住下一个节点
                ListNode temp = head.next;
                head.next = null;// 自己是相等的最后一个节点，断开连接
                head = temp;// 向前挪至自己的下一个节点
                cur.next = head;// 将cur.next连接过来即可
            }
        }
        return dummyNode.next;
    }

    public ListNode mergeTwoListsII(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(0);
        ListNode cur = dummyNode;
        while (l1 != null && l2 != null) {
            if (l1.val >= l2.val) {
                cur.next = new ListNode(l2.val);
                l2 = l2.next;
            } else {
                cur.next = new ListNode(l1.val);
                l1 = l1.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return dummyNode.next;
    }

    public ListNode mergeTwoListsIII(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(0);
        ListNode cur = dummyNode;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return dummyNode.next;
    }

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode res = dummyNode;// 记录整条链表衔接
        ListNode cur = head;// 后节点
        ListNode pre = cur.next;// 前节点
        while (pre != null) { // 前节点不能为null
            ListNode temp = pre.next; // 前节点前面做临时节点保留
            pre.next = cur;// 前节点翻转
            cur.next = temp;// 后节点翻转
            res.next = pre;// 链表节点下一个衔接翻转之后的
            res = pre.next;// 链表节点移动到翻转之后的后一个节点
            cur = cur.next;// 后节点向后走，这里最多为null
            if (cur == null) {// 后节点如果为null，表示没得走了
                break;// 终止
            }
            pre = cur.next;// 否则前节点向前走
        }
        return dummyNode.next;
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        if (k == 1) return head;
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode res = dummyNode;// 每一次的头
        ListNode cur = dummyNode.next;// 头的下一个
        ListNode pre = cur;// 双指针
        while (pre != null && pre.next != null) {
            int cnt = 1;
            while (cnt < k && pre.next != null) {
                pre = pre.next;// 保证pre一定不为null，即pre.next不为null
                cnt++;
            }
            // 否则是因为pre为null，就不需要翻转
            if (cnt == k) {
                ListNode temp = pre.next;// 记录下一次的头节点
                ListNode start = res.next;// 记录下下一次res的位置
                ListNode tmp = null;
                while (cur != temp) { // 翻转链表的代码
                    ListNode t = cur.next;
                    cur.next = tmp;
                    tmp = cur;
                    cur = t;
                }
                res.next = tmp; // 衔接头tmp
                start.next = temp;// 尾巴衔接，开头的第一个，即结束的最后一个
                res = start;// 移动res到start即下一个开始的节点
                // cur记录temp之前的节点
                cur = pre = start.next; // 同时移动到下一次的头节点
            }
        }
        return dummyNode.next;
    }

    /**
     * 利用插入排序给链表排序
     */
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummyNode = new ListNode(Integer.MIN_VALUE);
        dummyNode.next = head;
        ListNode res = dummyNode;
        ListNode cur = dummyNode.next;
        while (cur != null) {
            // 如果一直小表示是有序的
            if (res.val < cur.val) {
                res = res.next;
                cur = cur.next;
                continue;
            }
            // 否则就到了需要交换的地方
            res.next = cur.next; // 先将res的下一个与cur的下一个衔接，因为cur当前节点需要往前插入
            cur.next = null; // 此时将cur独立出来
            // 启动一个新的遍历节点来找到需要插入的位置，对等数组的遍历
            ListNode tmp = dummyNode;
            // 这主要是判断插在中间
            while (tmp.next != null && tmp.next.val < cur.val) {
                tmp = tmp.next;
            }
            // 不成立的话表示找到了插入的位置
            cur.next = tmp.next;
            tmp.next = cur; // 需要衔接与之前节点的状态
            cur = res.next; // cur需要回到原来的地方与res继续往后查找
        }
        return dummyNode.next;
    }

    /**
     * 排序链表，要求时间复杂度为o(nlogn)
     *
     * @param head 头节点
     * @return 排序好的链表
     */
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        // logn的算法就是二分法
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // slow抵达终点，fast抵达终点
        fast = slow.next;// fast作为后半段的头节点
        slow.next = null;// 截断
        slow = head;// slow作为首节点
        ListNode l1 = sortList(slow);// 一直递归往下
        ListNode l2 = sortList(fast);
        return merge(l1, l2);
    }

    // 链表的merge合并过程
    public ListNode merge(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode cur = res;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return res.next;
    }

    public int minNumberInRotateArray(int[] array) {
        if (array == null) return 0;

        int l = 0;
        int r = array.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2; // 找出中点
            // 如果大于前，也大于后
            if (array[m] == array[l] && array[m] == array[r]) {
                // 只能采用遍历取最小
                int min = Integer.MAX_VALUE;
                for (int num : array) {
                    min = Math.min(min, num);
                }
                return min;
            }
            // 否则采用二分法判断向前还是向后
            if (array[m] > array[r]) {
                l = m + 1;// 向后
            } else {
                r = m; // 向前
            }
        }
        return 0;
    }

    /**
     * 全排列
     * aa => aa 不是aa aa
     */
    private ArrayList<String> arrayList = new ArrayList<>();

    public ArrayList<String> Permutation(String str) {
        if (str.length() <= 0) return arrayList;
        executePermutation(str.toCharArray(), 0);
        Collections.sort(arrayList);
        return arrayList;
    }

    // 注意这里保持的是把需要产生重排列的交换到第一个位置上
    private void executePermutation(char[] chars, int index) {
        if (index == chars.length - 1) {
            arrayList.add(String.valueOf(chars));
        }
        // 为避免出现相同的aab 第一次走的话就是aab，如果不去重的话，第二次走还会是aab，所以需要去重
        Set<Character> set = new HashSet<>();
        for (int i = index; i < chars.length; i++) {
            // i==index表示就是当前位，如果不是当前位置的话，看看是否有重复
            if (i == index || !set.contains(chars[i])) {
                set.add(chars[i]);
                // 交换，以此保持全排列
                swap(chars, i, index);
                // 往后走，产生重排列
                executePermutation(chars, index + 1);
                // 记得交换回来
                swap(chars, i, index);
            }
        }
    }

    private void swap(char[] chars, int a, int b) {
        char temp = chars[a];
        chars[a] = chars[b];
        chars[b] = temp;
    }

    /**
     * 节点之间相差n个，但是注意需要使用一个虚拟节点
     * 防止删除的就是当前的p节点，不然会导致错误
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return null;
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode p = dummyNode;
        ListNode q = p;
        while (n != 0) {
            q = q.next;
            n--;// 注意在后面的话会多走一个，这样的话越过dummyNode
        }
        // 两者之间差距为n个节点
        while (q.next != null) {
            p = p.next;
            q = q.next;
        }
        // 一旦为null，表示到了终点
        ListNode temp = p.next.next;
        p.next.next = null;
        p.next = temp;
        return dummyNode.next;
    }

    /**
     * k次，时间复杂度O(nk)
     */
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode p = dummyNode.next;
        ListNode q = p.next;
        ListNode cur = head;
        int cnt = 0;
        while (cur != null) {
            cnt++;
            cur = cur.next;
        }
        k %= cnt;
        while (k != 0) {
            while (q.next != null) {
                p = p.next;
                q = q.next;
            }
            p.next = null;
            q.next = dummyNode.next;
            dummyNode.next = q;
            p = q;
            q = q.next;
            k--;
        }
        return dummyNode.next;
    }

    // 方法II,手动成环，找到移动k%cnt处的位置，切断，然后返回下一个即可。
    // cnt是总的节点个数，k%cnt是需要走的步数，而cnt-k%cnt是剩下的步数，走到了则到了那个首节点
    // 但是为了确保切断，需要少走一步，切断所以-1，即cnt-k%cnt-1
    public ListNode rotateRightII(ListNode head, int k) {
        if (head == null) return null;

        ListNode cur = head;
        int cnt = 1;
        while (cur.next != null) {
            cnt++;
            cur = cur.next;
        }
        k %= cnt;
        if (k == 0) return head;
        cur.next = head;
        ListNode pre = head;
        int i = 0;
        while (i < (cnt - k - 1)) {
            pre = pre.next;
            i++;
        }
        ListNode temp = pre.next;
        pre.next = null;
        return temp;
    }

    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 此时slow到了中点，fast到了末尾节点
        fast = slow.next;
        fast = reverse(fast);
        slow.next = null;
        slow = head;
        // 上面的动作正式切换为两个链表了
        // 1 2 3 | 4
        //
        while (slow != null && fast != null) {
            ListNode temp = slow.next;
            ListNode tmp = fast.next;
            slow.next = fast;
            fast.next = temp;
            slow = temp;
            fast = tmp;
        }
    }

    // 120 有效的括号
    public boolean isValid(String s) {
        if (s.isEmpty()) return true;
        // []{}()
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        stack.push(chars[0]);
        for (int i = 1; i < chars.length; i++) { // 边走边向外面推 pop()
            if (!stack.isEmpty() && pair(stack.peek(), chars[i])) {
                stack.pop();
            } else {
                stack.push(chars[i]);
            }
        }
        return stack.isEmpty();
    }

    private boolean pair(char a, char b) {
//        return ((a == '(' && b == ')') || (a == ')' && b == '('))
//                || ((a == '{' && b == '}') || (a == '}' && b == '{'))
//                || ((a == '[' && b == ']') || (a == ']' && b == '['))
//                || ((a == ' ' && b == ' '));
//        return ((a == '(' && b == ')'))
//                || ((a == '{' && b == '}'))
//                || ((a == '[' && b == ']'))
//                || ((a == ' ' && b == ' '));
        return ((a == '(' && b == ')'));
    }

    // 22 括号的生成  字符串+回溯法
    public List<String> generateParenthesisII(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        // 利用暴力枚举的方式，获取所有的组合方式
        executeProductII(res, "", 0, 0, n);
        return res;
    }

    // 使用暴力解法
    private void executeProductII(List<String> res, String str, int l, int r, int n) {
        if (l > n || r > n) return;
        // 遍历所有的情况，只有符合条件才会被加入
//        if (l == n && r == n && isValid(str)){
        if (l == n && r == n) {
            res.add(str);
        }
        if (l < r) return;// 如果)使用的比(要多的话，一定是不符合的,这个就是剪枝的过程
        // 否则的话就要使用暴力了
        executeProductII(res, str + "(", l + 1, r, n);
        executeProductII(res, str + ")", l, r + 1, n);
    }

    // 使用广度优先遍历
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) return res;
        Queue<Nodes> queue = new LinkedList<>();
        queue.offer(new Nodes("", n, n));
        while (!queue.isEmpty()) {
            Nodes poll = queue.poll();
            int left = poll.left;
            int right = poll.right;
            if (left == 0 && right == 0) {
                res.add(poll.res);
            }
            if (left > 0) {
                queue.offer(new Nodes(poll.res + "(", left - 1, right));
            }
            if (right > 0 && left < right) {// 一定要left<right，要比右边用的多才行否则一定是错误的答案
                queue.offer(new Nodes(poll.res + ")", left, right - 1));
            }
        }
        return res;
    }

    private void executeGenerate(List<String> res, String str, int l, int r, int n) {

    }

    public int evalRPN(String[] tokens) {
        if (tokens == null) return 0;
        // 2 1 + 3 *
        Stack<Integer> stack1 = new Stack<>();
        for (String token : tokens) {
            // 如果不是字母就加入
            if (!isLetter(token)) {
                stack1.push(Integer.parseInt(token));
            } else { // 否则参与计算，注意先后顺序cur表示后面，pre表示前面，每次都是pre(+-*/)cur
                int cur = stack1.pop();
                int pre = stack1.pop();
                int calculate = calculate(pre, cur, token);
                stack1.push(calculate);
            }
        }
        return stack1.pop();
    }

    public boolean isLetter(String c) {
        return c.equals("+") || c.equals("-")
                || c.equals("*") || c.equals("/");
    }

    public int calculate(int a, int b, String c) {
        if (c.equals("+")) {
            return a + b;
        } else if (c.equals("-")) {
            return a - b;
        } else if (c.equals("*")) {
            return a * b;
        } else if (c.equals("/")) {
            return a / b;
        }
        return 0;
    }

    // 71 简化路径
    public String simplifyPath(String path) {
        if (path.isEmpty()) return null;
        String[] split = path.split("/");
        Stack<String> stack = new Stack<>();
        // 利用/分割，遇到..就去除上一个元素
        for (String s : split) {
            if (!stack.isEmpty() && s.equals("..")) {
                stack.pop();
                // 否则的话如果不是""，"."，或者".."都添加即可
            } else if (!s.equals("") && !s.equals(".") && !s.equals("..")) {
                stack.push(s);
            }
        }
        if (stack.isEmpty()) {
            return "/";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stack.size(); i++) {
            sb.append("/").append(stack.get(i));
        }
        return sb.toString();
    }

    // 144. 二叉树的前序遍历

    // 递归实现
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        executePreorder(root, res);
        return res;
    }

    private void executePreorder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        executePreorder(root.left, res);
        executePreorder(root.right, res);
    }

    // 迭代实现 ==> 用stack实现啊
    public List<Integer> preorderTraversalII(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        TreeNode pool;
        while (!stack.isEmpty()) {
            pool = stack.pop();
            res.add(pool.val);
            if (pool.right != null) {
                stack.add(pool.right);
            }
            if (pool.left != null) {
                stack.add(pool.left);
            }
        }
        return res;
    }

    // 94. 二叉树的中序遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode peek;
        while (!stack.isEmpty()) {
            // 怎么把左边的值去掉呢
            peek = stack.peek();
            if (peek.left != null) {
                stack.push(peek.left);
                peek.left = null;// 一定要注意将左边的值去掉
                continue;
            }
            TreeNode node = stack.pop();
            res.add(node.val);
            if (peek.right != null) {
                stack.push(peek.right);
            }
        }
        return res;
    }

    // 145. 二叉树的后序遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode peek = stack.peek();
            if (peek.left != null) {
                stack.push(peek.left);
                peek.left = null;
                continue;
            }
            // 现在到了中间节点-需要看是是否有右节点。
            if (peek.right != null) {
                stack.push(peek.right);
                peek.right = null;
                continue;
            }
            // 否则就到了中间节点，此时必须加入
            TreeNode pop = stack.pop();
            res.add(pop.val);
        }
        return res;
    }

    /**
     * 面对一切需要面对的！战胜一切需要面对的！
     */
    public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode peek = stack.peek();
            stack.pop();
            if (peek != null) {
                stack.push(peek);
                stack.push(null);// 用null值来确保没有处理
                if (peek.right != null) stack.push(peek.right);
                if (peek.left != null) stack.push(peek.left);
            } else {
                res.add(stack.pop().val);// 遇到null，把null下面一个取出即可
            }
        }
        return res;
    }

    public List<Integer> postOrderTraversalII(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = null;
        // 秒啊，利用pre做指针，每次都去判断是否是处理过的点，pre的作用就是是否是处理过的点！
        while (!stack.isEmpty()) {
            TreeNode peek = stack.peek();
            // 这里的判断条件是看当前的peek是否是到了最后一个点，也就是第一个添加的点
            // 或者说是pre不为null，处理过，且此时位置为未处理的，才添加
            if ((peek.left == null && peek.right == null)
                    || (pre != null && (pre == peek.right || pre == peek.left))) {
                res.add(peek.val);
                pre = peek;
                stack.pop();
            } else {
                if (peek.right != null) stack.add(peek.right);
                if (peek.left != null) stack.add(peek.left);
            }
        }
        return res;
    }

    // 层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            // 遍历所有的
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
                list.add(poll.val);
            }
            res.add(list);
        }
        return res;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            // 遍历所有的
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
                list.add(poll.val);
            }
            res.add(0, list);
        }
        return res;
    }

    // Z字形打印
    // 使用flag作为分界，先进先出只改变插入顺序
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (null == root) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = true;// 表名偶数
        TreeNode temp = null;
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                temp = queue.poll();
                // 根据flag判断为正序还是逆序插入
                if (flag) {
                    list.add(temp.val);
                } else {
                    list.add(0, temp.val);
                }
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
            res.add(list);
            flag = !flag;
        }
        return res;
    }

    /**
     * 双栈策略：是指就是模拟了一个队列而已
     */
    public List<List<Integer>> zigzagLevelOrderII(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (null == root) return res;
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        boolean flag = true;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            if (flag) {
                while (!stack1.isEmpty()) {
                    TreeNode pop = stack1.pop();
                    if (pop.left != null) {
                        stack2.push(pop.left);
                    }
                    if (pop.right != null) {
                        stack2.push(pop.right);
                    }
                    list.add(pop.val);
                }
            } else {
                while (!stack2.isEmpty()) {
                    TreeNode pop = stack2.pop();
                    if (pop.right != null) {
                        stack1.push(pop.right);
                    }
                    if (pop.left != null) {
                        stack1.push(pop.left);
                    }
                    list.add(pop.val);
                }
            }
            res.add(list);
            flag = !flag;
        }
        return res;
    }

    // 199 二叉树的右视图
    // 广度优先遍历
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (null == root) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 只存最右的节点即可
        TreeNode poll;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                poll = queue.poll();
                if (i == size - 1) {
                    res.add(poll.val);
                }
                if (poll.left != null) {
                    queue.add(poll.left);
                }
                if (poll.right != null) {
                    queue.add(poll.right);
                }
            }
        }
        return res;
    }

    // 深度优先遍历解决
    public List<Integer> rightSideViewII(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        executeRight(root, 0, res);
        return res;
    }

    private void executeRight(TreeNode root, int depth, List<Integer> res) {
        // 根据list的size来的，一层只能有一个
        if (root == null) return;
        if (depth == res.size()) {
            res.add(root.val);
        }
        executeRight(root.right, depth + 1, res);
        executeRight(root.left, depth + 1, res);
    }

    // 完全平方数
    public int numSquares(int n) {
        Queue<Pair> queue = new LinkedList<>();
        boolean visited[] = new boolean[n + 1];
        queue.add(new Pair(n, 0));
        visited[n] = true;
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();
            int num = pair.first;
            int step = pair.second;
            if (num == 0) return step;

            for (int i = 1; num - i * i >= 0; i++) {
                int temp = num - i * i;
                if (temp < 0) break;
                // 这种等于0的情况只会出现一次
                if (temp == 0) return step + 1;
                // 只剩下大于0的情况
                if (!visited[i]) {
                    // 表示未访问过
                    queue.add(new Pair(temp, step + 1));
                    visited[temp] = true;
                }
            }
        }
        return 0;
    }

    // 使用动态规划的方式来做
    public int numSquaresII(int n) {
        if (n == 1) return 1;
        // 12 = 4 + 4 + 4  =3
        // 13 = 4 + 9 =2
        int[] res = new int[n + 1];
        res[0] = 0;
        res[1] = 1;
        for (int i = 2; i <= n; i++) {
            int r = i;// 所有的都用1表示的个数
            for (int j = 1; j <= (int) Math.sqrt(i); j++) {
                // 4的话，1 1 1 4,这是一轮算下来，当前数最小表示的是多少
                // 利用用1表示的所有，与自己之前j平方大小的数+1(即j)这个数相比较谁小取谁
                r = Math.min(r, res[i - j * j] + 1);
            }
            res[i] = r;
        }
        return res[n];
    }

    // 127 单词接龙
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 不存在的话直接为0
        if (!wordList.contains(endWord)) return 0;
        Set<String> start = new HashSet<>();
        Set<String> end = new HashSet<>();
        Set<String> word = new HashSet<>(wordList);
        String template = "abcdefghijklmnopqrstuvwxyz";
        char[] array = template.toCharArray();
        // 有一个终止条件是什么呢？
        start.add(beginWord);
        end.add(endWord);
        int step = 0;
        while (!start.isEmpty()) {
            step++;
            Set<String> tempSet = new HashSet<>();
            word.removeAll(start);// 移除所有使用过的元素
            for (String s : start) {
                char[] chars = s.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char tmp = chars[i];
                    for (int j = 0; j < array.length; j++) {
                        // 相等直接跳过
                        if (tmp == array[j]) continue;
                        // 否则交换
                        chars[i] = array[j];
                        String tempStr = new String(chars);
                        if (word.contains(tempStr)) {
                            if (end.contains(tempStr)) {
                                return step;
                            } else {
                                // 这里面存放的表示当前可以作为中转的字符
                                tempSet.add(tempStr);
                            }
                        }

                    }
                    chars[i] = tmp;
                }
            }
            // 从少的那边开始计算
            if (tempSet.size() < end.size()) {
                start = tempSet;
            } else {
                start = end;
                end = tempSet;
            }
        }
        return 0;
    }

    /**
     * 使用递归来处理
     */
    public int ladderLengthII(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        Set<String> start = new HashSet<>();
        Set<String> end = new HashSet<>();
        Set<String> word = new HashSet<>(wordList);
        start.add(beginWord);
        end.add(endWord);
        return executeLadder(start, end, word, 2);
    }

    private int executeLadder(Set<String> start, Set<String> end, Set<String> word, int res) {
        // 这个就是终止条件，一旦为0，表示所有替换的都不再word中，即不可转换
        if (start.size() == 0) return 0;
        if (start.size() > end.size()) { // 永远以小端查询大端，减少比对次数
            return executeLadder(end, start, word, res);
        }
        // 如果不小于0的话继续
        word.removeAll(start);// 移除使用过的
        Set<String> tempSet = new HashSet<>();
        for (String s : start) { // 遍历所有的
            char[] chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                char temp = chars[i];
                for (char c = 'a'; c <= 'z'; c++) {
                    if (temp == c) continue;
                    chars[i] = c;
                    String tempStr = new String(chars);
                    // 一定要在word中才有机会往下走
                    if (word.contains(tempStr)) {
                        if (end.contains(tempStr)) {
                            return res;
                        } else {
                            tempSet.add(tempStr);
                        }
                    }
                }
                chars[i] = temp;
            }
        }
        // 默认把tempSet当做start，因为前面已经有做判断
        return executeLadder(tempSet, end, word, res + 1);
    }

    public boolean transform(String a, String b) {
        int cnt = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                cnt += 1;
            }
        }
        return cnt == 1;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {


        return null;
    }

    /**
     * 合并k个有序的链表
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length < 1) return null;
        return partitionNode(lists, 0, lists.length - 1);
    }

    private ListNode partitionNode(ListNode[] lists, int start, int end) {
        // 终止条件
        if (start >= end) return lists[start];
        // 开始二分当前的lists了
        int mid = start + ((end - start) >> 1);
        // 分治
        ListNode pre = partitionNode(lists, start, mid);
        ListNode next = partitionNode(lists, mid + 1, end);
        return mergeNode(pre, next);
    }

    private ListNode mergeNode(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(0);
        ListNode cur = dummyNode;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                cur.next = l2;
                l2 = l2.next;
            } else {
                cur.next = l1;
                l1 = l1.next;
            }
            cur = cur.next;
        }
        while (l1 != null) {
            cur.next = l1;
            l1 = l1.next;
            cur = cur.next;
        }
        while (l2 != null) {
            cur.next = l2;
            l2 = l2.next;
            cur = cur.next;
        }
        return dummyNode.next;
    }

    public int[] mergeSort(int[] nums) {
        if (nums == null || nums.length < 1) return null;
        partitionArray(nums, 0, nums.length - 1);
        return nums;
    }

    private void partitionArray(int[] nums, int start, int end) {
        if (start >= end) return;
        int mid = start + ((end - start) >> 1);
        partitionArray(nums, start, mid);
        partitionArray(nums, mid + 1, end);
        mergeArray(nums, start, mid, end);
    }

    private void mergeArray(int[] nums, int start, int mid, int end) {
        // 长度
        int[] temp = new int[end - start + 1];
        // 开始
        int l = start;
        int r = mid + 1;
        int i = 0;
        while (l <= mid && r <= end) {
            if (nums[l] > nums[r]) {
                temp[i++] = nums[r++];
            } else {
                temp[i++] = nums[l++];
            }
        }
        // 要确保一遍多一边少的情况发生！
        while (l <= mid) {
            temp[i++] = nums[l++];
        }
        while (r <= end) {
            temp[i++] = nums[r++];
        }
        // 结束之后需要赋值
        for (int j = 0; j < i; j++) {
            nums[start + j] = temp[j];
        }
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int depth = 1;
        return executeMaxDepth(root, depth);
    }

    private int executeMaxDepth(TreeNode root, int depth) {
        if (root == null) return 0;
        // 左边一直向下知道最后，依次+1，右边一直向下，依次+1
        return Math.max(executeMaxDepth(root.left, depth), executeMaxDepth(root.right, depth)) + 1;
    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        return executeMinDepth(root, 1);
    }

    private int executeMinDepth(TreeNode root, int depth) {
        if (root == null) return 0;
        // 需要保证一定是叶子节点，如果不是叶子节点也不可行
        // 只管一边即可如果有不为null的,只需要考虑一边即可
        if (root.left == null && root.right != null) {
            return 1 + executeMinDepth(root.right, depth);
        }
        if (root.right == null && root.left != null) {
            return 1 + executeMinDepth(root.left, depth);
        }
        // 两边同时不为null，才有资格往下走
        return 1 + Math.min(executeMinDepth(root.left, depth), executeMinDepth(root.right, depth));
    }


    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        // 否则进行左右交换
        TreeNode temp = root.right;
        root.right = root.left;
        root.left = temp;
        root.left = invertTree(root.left);
        root.right = invertTree(root.right);
        return root;
    }

    /**
     * 判断两棵树是否相同
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        // 开始比较,从头开始，延伸到左右节点
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 判断当前数是否是对称树
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return judgementSymmetric(root.left, root.right);
    }

    private boolean judgementSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        // 判断当前是否相等，然后判断左边的左是否等于右边的右，反之亦然
        return left.val == right.val && judgementSymmetric(left.left, right.right)
                && judgementSymmetric(left.right, right.left);
    }

    /**
     * 完全二叉树的节点个数
     * 第一种方法如下，但是这种做法没有利用到完全二叉树的性质
     */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int count = 0;
        count += 1;
        count += countNodes(root.left);
        count += countNodes(root.right);
        return count;
    }

    /**
     * 第二种方法就是借助左右子树的高度来判断
     * 借助计算公式(2^h-1)来判断当前的节点个数
     */
    public int countNodes_II(TreeNode root) {
        if (root == null) return 0;
        int l = getDepth(root.left);
        int r = getDepth(root.right);
        // 这边表示，如果左边大于右边，表示右边一定是满的，所以去查找左边
        if (l > r) {
            return 1 + (1 << r) - 1 + countNodes(root.left);
        }
        // 否则只能相等了，以为不可能右边大于左边的,相等的情况，左边一定是满的
        return 1 + (1 << l) - 1 + countNodes(root.right);
    }

    private int getDepth(TreeNode root) {
        // 由于是完全二叉树，高度完全取决于左边节点
        int res = 0;
        while (root != null) {
            res++;
            root = root.left;
        }
        return res;
    }

    /**
     * 判断是否是平衡二叉树
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        // 判断左右高度，然后分别判断左右高度就好了
        return Math.abs(getDepthII(root.left) - getDepthII(root.right)) <= 1
                && isBalanced(root.left) && isBalanced(root.right);
    }

    public int getDepthII(TreeNode root) {
        if (root == null) return 0;
        return Math.max(getDepthII(root.left), getDepthII(root.right)) + 1;
    }

    /**
     * 路径总和就是判断是否有当前路径+起来的等于指定和的分支
     */
    public boolean hasPathSum(TreeNode root, int sum) {
        // 如果往后一直都没有为0的话，而root又到了null，此时直接返回false
        if (root == null) return false;
        // 但凡能满足的都会在这里被拦截
        if (root.left == null && root.right == null) {
            return sum - root.val == 0;
        }
        // 向左向右走
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    /**
     * 404. 计算左叶子的和
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        return execute(root, true);
    }

    private int execute(TreeNode root, boolean isLeft) {
        if (root == null) return 0;
        int sum = 0;
        if (isLeft && root.left == null && root.right == null) {
            return root.val;
        }
        // 左边表示是左树即true，如果是右边的话使用false
        sum += execute(root.left, true);
        sum += execute(root.right, false);
        return sum;
    }

    /**
     * 看评论别人的方法
     */
    public int sumOfLeftLeavesII(TreeNode root) {
        if (root == null) return 0;
        int sum = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        }
        sum += sumOfLeftLeaves(root.left);
        sum += sumOfLeftLeaves(root.right);
        return sum;
    }

    /**
     * 二叉树的所有路径
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        executeBinaryTreePaths(root, res, new StringBuilder());
        return res;
    }

    private void executeBinaryTreePaths(TreeNode root, List<String> res, StringBuilder sb) {
        if (root == null) return;
        sb.append(root.val);
        if (root.left == null && root.right == null) {
            res.add(sb.toString());// 这是最终的归宿
        }
        StringBuilder append = sb.append("->");
        // 否则的话，依旧要继续衔接下去，要回到最初始的状态,所以构造新的对象，否则对象sb会被复用
        executeBinaryTreePaths(root.left, res, new StringBuilder(append));
        executeBinaryTreePaths(root.right, res, new StringBuilder(append));
    }

    /**
     * 235、二叉搜索树的最近公共祖先
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        // 如果是两边都是大于，向右走
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        // 如果两边都是小于，向左走
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        }
        // 否则如果两边不是一起大于或者小于，则可能存在等于，或者一边大一边小，则直接返回root即可
        return root;
    }

    /**
     * 98、验证二叉搜索树
     * 利用中旬遍历，只要不小于后面即返回false
     */
    private long last = -Long.MAX_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        // 一直向左，如果是true，表明这个数到了null
        if (isValidBST(root.left)) {
            // 然后判断是否比最后一个数小
            if (last < root.val) {
                last = root.val;
                return isValidBST(root.right);
            }
        }
        return false;
    }

    public boolean isValidBSTII(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean validate(TreeNode node, long min, long max) {
        if (node == null) {
            return true;
        }
        // 不能够小于左边的，不能够大于右边的
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }
    /*public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        boolean r = true;
        if (root.left != null) {
            if (root.left.val >= root.val) {
                return false;
            }
            r &= executeValidBST(root.left, root.val, root.left.val, true, false);
        }
        if (root.right != null) {
            if (root.right.val <= root.val) {
                return false;
            }
            r &= executeValidBST(root.right, root.val, root.val, false, true);
        }
        return r;
    }

    private boolean executeValidBST(TreeNode root, int rootVal, int val, boolean left, boolean right) {
        if (root == null) return true;
        boolean r = true;
        // 终止条件是什么,主要是左右切换
        if (root.left != null) {
            if ((left && (root.left.val >= val || root.left.val >= rootVal))
                    || (right && (root.left.val <= val || root.left.val <= rootVal))) {
                r = false;
            } else {
                r &= executeValidBST(root.left, rootVal, root.val, left, right);
            }
        }
        if (root.right != null) {
            if ((right && (root.right.val <= val || root.right.val <= rootVal))
                    || (left && (root.right.val >= val || root.right.val >= rootVal))) {
                r = false;
            } else {
                r &= executeValidBST(root.right, rootVal, root.val, left, right);
            }
        }
        // 否则需要判断当前的值是否与左节点
        // 判断左右都要是二叉树
        return r;
    }*/

    /**
     * 450、删除二叉搜索树中的节点
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        /*
            利用二叉树的性质解题
         */
        // 如果相等的话
        if (root.val == key) {
            // 左边为null返回右边，反之亦然
            if (root.right == null) return root.left;
            if (root.left == null) return root.right;
            // 否则一定是两边都不为null
            TreeNode preNode = root.right;
            // 找到右边最小的节点
            while (preNode.left != null) {
                preNode = preNode.left;
            }
            // 把root的整个左边都拿过来
            preNode.left = root.left;
            // 最后只要返回root的右边就好了，因为节点已经被找到了
            return root.right;
        } else {
            // 如果不等的话
            if (key > root.val) {
                root.right = deleteNode(root.right, key);
            } else {
                root.left = deleteNode(root.left, key);
            }
        }
        return root;
    }

    /**
     * 108、将有序数组转换为二叉搜索树
     * 注意题目给出的是升序的数组，所以一旦找到中点，左边的一定小于自己，右边的一定大于自己
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length < 1) return null;
        return buildBST(nums, 0, nums.length);
    }

    private TreeNode buildBST(int[] nums, int start, int end) {
        // 找到最后一个，只要大于等于自己，直接返回null
        if (start >= end) return null;
        // 否则获取中点
        int mid = start + (end - start) / 2;
        // 此时以中点为root节点
        TreeNode root = new TreeNode(nums[mid]);
        // 左边的一定小于自己，注意mid已经用了，所以只能是到mid，因为>=就返回null
        root.left = buildBST(nums, start, mid);
        // 右边的一定大于自己
        root.right = buildBST(nums, mid + 1, end);
        return root;
    }

    /**
     * 230、二叉搜索树中第K小的元素
     * 第一种暴力法，直接中序遍历存储从小到大的值即可
     */
    private List<Integer> list = new ArrayList<>();

    public int kthSmallestI(TreeNode root, int k) {
        if (k == 0) return root.val;
        executeFindKthSmallest(root);
        return list.get(k - 1);
    }

    private void executeFindKthSmallest(TreeNode root) {
        if (root == null) return;
        executeFindKthSmallest(root.left);
        list.add(root.val);
        executeFindKthSmallest(root.right);
    }

    /**
     * 70、爬楼梯
     */
    public static int climbStairs(int n) {
        int[] dp = new int[n + 1];

        dp[1] = 1;
        if (n == 1) { // 只有一个元素就直接返回
            return dp[n];
        }
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }

    /**
     * 120、三角形最小路径和
     * 使用O(n)的空间复杂度
     */
    public int minimumTotalII(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[] dp = new int[size];
        // 自底向上,从最后一层来是对的
        for (int i = 0; i < size; i++) {
            dp[i] = triangle.get(size - 1).get(i);
        }
        for (int i = size - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                // 从最后一层来的话,第一个值被保留住了,所以可复用
                dp[j] = Math.min(dp[j - 1], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }

    /**
     * 这里是第一种，多用一重空间存储上面的值
     * 或者使用二维数组，每一层作为一个空间
     * 那种时间复杂度是O(n^2)
     */
    public int minimumTotalIII(List<List<Integer>> triangle) {
        int size = triangle.size();
        int[] dp = new int[size];
        int[] temp = new int[size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                if(j == 0) {
                    dp[j] = temp[j] + triangle.get(i).get(j);
                }else if(j == i){
                    dp[j] = temp[j-1] + triangle.get(i).get(j);
                }else{
                    dp[j] = Math.min(temp[j],temp[j-1])+triangle.get(i).get(j);
                }
            }
            // 赋值操作
            for (int k = 0; k < size; k++) {
                temp[k] = dp[k];
            }
        }
        // 最后遍历获取值的操作
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            min = Math.min(min,dp[i]);
        }
        return min;
    }



    public static void main(String[] args) {
//        for (String arg : args) {
//            System.out.println(arg);
//        }
        SolutionII solutionII = new SolutionII();
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(20);
        boolean validBST = solutionII.isValidBSTII(root);
        System.out.println(validBST);
//        int[] nums = new int[]{1, 2, 432, 3, 5, 6, 2, 3, 40, 26, 20};
//        solutionII.mergeSort(nums);
//        for (int num : nums) {
//            System.out.print(num + " ");
//        }
//        ListNode l1 = new ListNode(1);
//        l1.next = new ListNode(4);
//        ListNode l1 = null;
//        ListNode l2 = new ListNode(2);
//        l2.next = new ListNode(3);
//        l2.next.next = new ListNode(5);
//        ListNode l3 = new ListNode(2);
//        l3.next = new ListNode(3);
//        l3.next.next = new ListNode(5);
//        ListNode l4 = null;
//        ListNode[] lists = new ListNode[]{l1, l2, l4, l3};
//        ListNode listNode = solutionII.mergeKLists(lists);
//        ListNode listNode = solutionII.mergeNode(l1, l2);
//        System.out.println(listNode);
//        TreeNode root = new TreeNode(1);
//        root.right = new TreeNode(3);
//        root.right.right = new TreeNode(5);
//        root.left = new TreeNode(2);
//        root.left.left = new TreeNode(4);
//
//        List<List<Integer>> list = solutionII.zigzagLevelOrder(root);
//        int i = solutionII.numSquaresII(13);
//        System.out.println(i);
//        String s = solutionII.simplifyPath("/home/");
//        String s1 = solutionII.simplifyPath("/../");
//        String s2 = solutionII.simplifyPath("/a/./b/../../c/");
//        String s3 = solutionII.simplifyPath("/a//b////c/d//././/..");
//        System.out.println(s);
//        System.out.println(s1);
//        System.out.println(s2);
//        System.out.println(s3);
//        int i = solutionII.evalRPN(new String[]{"2", "1", "+", "3", "*"});
//        List<String> strings = solutionII.generateParenthesis(3);
//        System.out.println(strings);
//        boolean valid = solutionII.isValid("())()(");
//        System.out.println(valid);
//        boolean b = solutionII.containsNearbyAlmostDuplicate(new int[]{7, 1, 2}, 2, 3);
//        int[] num = new int[]{1, 2, 3, 4, 5};
//        int[] num = new int[]{1,2,3,4,5};
//        System.out.println(solutionII.Permutation("aab"));
//        ListNode head = new ListNode(0);
//        ListNode cur = head;
//        for (int n : num) {
//            cur.next = new ListNode(n);
//            cur = cur.next;
//        }
//        ListNode head1 = new ListNode(0);
//        int[] num1 = new int[]{1, 2, 3};
//        ListNode cur1 = head1;
//        for (int n : num1) {
//            cur1.next = new ListNode(n);
//            cur1 = cur1.next;
//        }
//        ListNode listNode = solutionII.rotateRightII(head.next, 1);
//        solutionII.reorderList(head.next);
//        System.out.println(head.next);
//        ListNode listNode = solutionII.sortList(head.next);
//        System.out.println(listNode);
//        int i = solutionII.fourSumCount(new int[]{1, 2}, new int[]{-2, -1}, new int[]{-1, 2}, new int[]{0, 2});
//        System.out.println(i);
//        System.out.println(solutionII.gcdII(5, 10));
//        int i = solutionII.threeSumClosest(new int[]{-1, 2, 1, -4}, 2);
//        System.out.println(i);
//        int[] intersect = solutionII.intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2});
//        boolean b = solutionII.wordPattern("beef", "");
//        System.out.println(b);
//        List<List<Integer>> lists = solutionII.threeSum_1(new int[]{-1, 0, 1, 2, -1, -4});
//        List<List<Integer>> lists = solutionII.threeSum_1(new int[]{0, 0, 0, 0});
//        System.out.println(lists);
//        String s = solutionII.minWindow("DOBECAODEBANC", "ABC");
//        String s = solutionII.minWindow("aa", "aa");
//        System.out.println(s);
//        int i = solutionII.lengthOfLongestSubstring(" ");
//        System.out.println(i);
//        List<Integer> anagrams = solutionII.findAnagrams("cbaebabacd", "abc");
//        System.out.println(anagrams);
//        List<List<String>> lists = solutionII.solveNQueens(1);
//        System.out.println(lists);
//        List<List<Integer>> list = new ArrayList<>();
//        list.add(Arrays.asList(2));
//        list.add(Arrays.asList(3, 4));
//        list.add(Arrays.asList(6, 5, 7));
//        list.add(Arrays.asList(4, 1, 8, 3));
//
//        int i = solutionII.minimumTotal1(list);
//        System.out.println(i);
//        double gcd = solutionII.gcd(10, 25);
//        System.out.println(gcd);
//        int i = solutionII.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3});
//        System.out.println(i);
//        String s = "-102";
//        int i = Integer.parseInt(s, 10);
//        System.out.println(i);
    }


}

class Pair {
    int first;
    int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val) {
        this.val = val;
    }
}

class Nodes {
    String res;
    int left;
    int right;

    public Nodes(String res, int left, int right) {
        this.res = res;
        this.left = left;
        this.right = right;
    }
}
