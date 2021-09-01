# 已完成

[[165. 比较版本号 - 力扣（LeetCode） (leetcode-cn.com)](https://leetcode-cn.com/problems/compare-version-numbers/)](https://leetcode-cn.com/problems/compare-version-numbers/)

[![hwDRJO.png](https://z3.ax1x.com/2021/09/01/hwDRJO.png)](https://imgtu.com/i/hwDRJO)

**分割字符串**

```java
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        for (int i = 0; i < v1.length || i < v2.length; i++) {
            int a = 0, b = 0;
            if (i < v1.length) {
                a = Integer.parseInt(v1[i]);
            }
            if (i < v2.length) {
                b = Integer.parseInt(v2[i]);
            }
            if (a > b) {
                return 1;
            }
            if (a < b) {
                return -1;
            }
        }
        return 0;
    }
}
```

**双指针法**

```java
class Solution {
    public int compareVersion(String version1, String version2) {
        int n = version1.length(), m = version2.length();
        for (int i = 0, j = 0; i < m || j < n; i++, j++) {
            int a = 0, b = 0;
            while (i < n && version1.charAt(i) != '.') {
                a = 10 * a + (version1.charAt(i++) - '0');
            }
            while (j < m && version2.charAt(j) != '.') {
                b = 10 * b + (version2.charAt(j++) - '0');
            }
            if (a != b) {
                return a > b ? 1 : -1;
            }
        }
        return 0;
    }
}
```

