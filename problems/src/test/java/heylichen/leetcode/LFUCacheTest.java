package heylichen.leetcode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.junit.Test;

public class LFUCacheTest {
    @Test
    public void name() {
        String con = "[[2,1],[2],[3,2],[2],[3]]";
        LFUCache cache = new LFUCache(1);

        JSONArray ja = JSON.parseArray(con);
        for (int i = 0; i < ja.size(); i++) {
            JSONArray ja2 = ja.getJSONArray(i);
            if (ja2.size() == 1) {
                int a = ja2.getIntValue(0);
                int v = cache.get(a);
                System.out.println(cache);
            } else {
                int a = ja2.getIntValue(0);
                int b = ja2.getIntValue(1);
                cache.put(a, b);
                System.out.println("\tput " + a + " " + b + "\t" + cache);
            }
        }
    }
}