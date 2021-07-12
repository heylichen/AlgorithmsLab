package heylichen.leetcode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.Assert;
import org.junit.Test;

public class LRUCacheTest {

    @Test
    public void name() {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        Assert.assertEquals("", 1, lRUCache.get(1));   // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        Assert.assertEquals("", -1, lRUCache.get(2));  // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        Assert.assertEquals("", -1, lRUCache.get(1));    // 返回 -1 (未找到)
        Assert.assertEquals("", 3, lRUCache.get(3));
        Assert.assertEquals("", 4, lRUCache.get(4));
    }

    @Test
    public void name2() {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(2, 6); // 缓存是 {1=1}
        lRUCache.put(1, 5); // 缓存是 {1=1, 2=2}
        Assert.assertEquals("", 5, lRUCache.get(1));   // 返回 1
        lRUCache.put(1, 2); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        Assert.assertEquals("", 2, lRUCache.get(1));  // 返回 -1 (未找到)
        Assert.assertEquals("", 6, lRUCache.get(2));    // 返回 -1 (未找到)
    }

    @Test
    public void name3() {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(2, 1); // 缓存是 {1=1}
        lRUCache.put(1, 1); // 缓存是 {1=1, 2=2}
        lRUCache.put(2, 3);
        lRUCache.put(4, 1);
        Assert.assertEquals("", -1, lRUCache.get(1));   // 返回 1
        Assert.assertEquals("", 3, lRUCache.get(2));    // 返回 -1 (未找到)
    }

    @Test
    public void name4() {
        String con = "[[10,13],[3,17],[6,11],[10,5],[9,10],[13],[2,19],[2],[3],[5,25],[8],[9,22],[5,5],[1,30],[11],[9,12],[7],[5],[8],[9],[4,30],[9,3],[9],[10],[10],[6,14],[3,1],[3],[10,11],[8],[2,14],[1],[5],[4],[11,4],[12,24],[5,18],[13],[7,23],[8],[12],[3,27],[2,12],[5],[2,9],[13,4],[8,18],[1,7],[6],[9,29],[8,21],[5],[6,30],[1,12],[10],[4,15],[7,22],[11,26],[8,17],[9,29],[5],[3,4],[11,30],[12],[4,29],[3],[9],[6],[3,4],[1],[10],[3,29],[10,28],[1,20],[11,13],[3],[3,12],[3,8],[10,9],[3,26],[8],[7],[5],[13,17],[2,27],[11,15],[12],[9,19],[2,15],[3,16],[1],[12,17],[9,1],[6,19],[4],[5],[5],[8,1],[11,7],[5,2],[9,28],[1],[2,2],[7,4],[4,22],[7,24],[9,26],[13,28],[11,26]]";
        LRUCache lRUCache = new LRUCache(10);
        JSONArray ja = JSON.parseArray(con);
        for (int i = 0; i < ja.size(); i++) {
            JSONArray ja2 = ja.getJSONArray(i);
            if (ja2.size() == 1) {
                int a = ja2.getIntValue(0);
                int v = lRUCache.get(a);
                System.out.print(v + ",");
//                if (v == 14) {
//                    System.out.println("-------error value=14");
//                }
//                System.out.println(lRUCache);
            } else {
                int a = ja2.getIntValue(0);
                int b = ja2.getIntValue(1);
                lRUCache.put(a, b);
                System.out.print("null,");
                // System.out.println("\tput " + a + " " + b + "\t" + lRUCache);
            }
        }
    }
}