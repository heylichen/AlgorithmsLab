package heylichen.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public final class FileHelper {
    private FileHelper() {
    }

    public static String readString(String path) throws IOException {
        ClassPathResource cpr = new ClassPathResource(path);
        return FileUtils.readFileToString(cpr.getFile(), StandardCharsets.UTF_8);
    }

    public static JSONObject readJSONObject(String path) throws IOException {
        String json = readString(path);
        return JSON.parseObject(json);
    }

    public static JSONArray readJSONArray(String path) throws IOException {
        String json = readString(path);
        return JSON.parseArray(json);
    }

    public static <T> T readBean(String path, Class<T> clazz) throws IOException {
        String json = readString(path);
        return JSON.parseObject(json, clazz);
    }

    public static <T> List<T> readBeanList(String path, Class<T> clazz) throws IOException {
        JSONArray arr = readJSONArray(path);
        return arr.toJavaList(clazz);
    }

    public static final List<String> readLines(String path) throws IOException {
        ClassPathResource cpr = new ClassPathResource(path);
        return FileUtils.readLines(cpr.getFile(), StandardCharsets.UTF_8);
    }

//    public static  <B, K> Map<K, B> readBeanMap(String path, Class<B> clazz, Function<B, K> keyGetter) throws IOException {
//        List<B> beans = readBeanList(path, clazz);
//        return MqbCollections.toMap(beans, keyGetter);
//    }
}
